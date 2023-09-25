package com.example.topupschedule.scheduled;

import com.example.topupschedule.TopupScheduleApplication;
import com.example.topupschedule.entity.OperatorEntity;
import com.example.topupschedule.entity.PacketEntity;
import com.example.topupschedule.entity.SourceApiEntity;
import com.example.topupschedule.entity.UserEntity;
import com.example.topupschedule.model.reloadly.FixedAmountsDescriptions;
import com.example.topupschedule.model.reloadly.OperatorID;
import com.example.topupschedule.repository.*;
import com.example.topupschedule.util.HeaderRequestInterceptor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class Schedule {
    final Logger logger = LoggerFactory.getLogger(Schedule.class);

    @Autowired
    private Environment environment;

    @Autowired
    private TopupScheduleApplication mappingApplication;

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private PacketRepository packetRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final Set<OperatorEntity> operatorEntitySet = new HashSet<>();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SourceApiRepository sourceApiRepository;

    @Scheduled(fixedRate = 604800000)//once a week
    public void getAllOperatorByCountryId() throws IOException {

        UserEntity user = new UserEntity();
        user.setEmail("admin@admin.com");
        user.setUsername("admin");
        user.setPassword("admin");
        user.setCreatedAt(new Date());
        userRepository.save(user);
        SourceApiEntity sourceApi = new SourceApiEntity();
        sourceApi.setId(1L);
        sourceApi.setName("reloadly");
        sourceApi.setCreatedAt(new Date());
        sourceApiRepository.save(sourceApi);

        //save operators
        String url = environment.getProperty("url_operator_ID");
        RestTemplate restTemplate = new RestTemplate();
        assert url != null;
        String authHeader = "Bearer " + mappingApplication.getVariabelToken();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Accept", environment.getProperty("accept")));
        interceptors.add(new HeaderRequestInterceptor("Authorization", authHeader));
        restTemplate.setInterceptors(interceptors);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<OperatorID>> typeReference = new TypeReference<List<OperatorID>>() {};
        List<OperatorID> operatorIDBase = objectMapper.readValue(responseEntity.getBody(), typeReference);
        operatorIDBase.forEach(object -> {
            OperatorEntity operatorEntity = new OperatorEntity();
            SourceApiEntity sourceApiEntity = new SourceApiEntity();
            sourceApiEntity.setId(Long.parseLong(Objects.requireNonNull(environment.getProperty("source_id_reloadly"))));
            operatorEntity.setSourceApiEntity(sourceApiEntity);
            operatorEntity.setOperatorSourceId(String.valueOf(object.getOperatorId()));
            operatorEntity.setName(object.getName());
            operatorEntitySet.add(operatorEntity);
        });
        ModelMapper modelMapper = new ModelMapper();
        List<?> objects = entityManager.createNativeQuery("SELECT p.name,p.source_api_entity_id,p.operator_source_id FROM operator as p","partialOperatorBulkUpdate").getResultList();
        List<OperatorEntity> operatorEntities = objects.stream().map(operator -> modelMapper.map(operator, OperatorEntity.class)).collect(Collectors.toList());
        Set<OperatorEntity> operatorEntitySetNew = operatorEntitySet.stream().filter(object -> {
            return !operatorEntities.contains(object);
        }).collect(Collectors.toSet());
        operatorRepository.saveAll(operatorEntitySetNew);

        for (OperatorID object : operatorIDBase) {
            OperatorEntity operatorEntity = operatorRepository.findByOperatorSourceIdAndSourceApiEntityId(String.valueOf(object.operatorId), Long.valueOf(Objects.requireNonNull(environment.getProperty("source_id_reloadly"))));
            FixedAmountsDescriptions fixedAmountsDescriptions = object.getFixedAmountsDescriptions();
            TypeReference<HashMap<String,Object>> fixedAmountDescriptionTypeReference = new TypeReference<HashMap<String,Object>>() {};
            String test = new Gson().toJson(fixedAmountsDescriptions);
            HashMap<String,Object> map = objectMapper.readValue(test,fixedAmountDescriptionTypeReference);
            Set<HashMap<String,Object>> hashMapHashSet = new HashSet<>();
            map.forEach( (key,value) -> {
                HashMap<String,Object> newMap = new HashMap<>();
                HashMap<String,Object> operatorEntityHashMap = new HashMap<>();
                newMap.put("name",value);
                newMap.put("status",true);
                newMap.put("price",Integer.valueOf(key.replace("_","")));
                operatorEntityHashMap.put("id",operatorEntity.getId());
                newMap.put("operatorEntity",operatorEntityHashMap);
                hashMapHashSet.add(newMap);
            });
            List<PacketEntity> packetEntities = hashMapHashSet.stream().map(operator -> modelMapper.map(operator, PacketEntity.class)).collect(Collectors.toList());
            for (PacketEntity packet : packetEntities) {
                PacketEntity packetEntity = packetRepository.findByName(packet.getName());
                if (packetEntity==null) {
                    packetRepository.save(packet);
                }
            }
        }
    };
}
