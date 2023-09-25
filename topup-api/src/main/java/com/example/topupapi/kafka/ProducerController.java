package com.example.topupapi.kafka;

import com.example.topupapi.model.KafkaSendReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Service
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class ProducerController {

    @Value("${topic.name.producer}")
    private String topicName;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){
        log.info("BELOW_PAYLOAD:",  message);
        kafkaTemplate.send(topicName, message);
    }

    @PostMapping(value = "/send")
    public void send(@RequestBody KafkaSendReq kafkaSendReq) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(kafkaSendReq);
        this.send(data);
    }
}
