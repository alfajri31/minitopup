package com.example.topupadmin.controlller;

import com.example.topupadmin.entity.UserGroupEntity;
import com.example.topupadmin.model.Message;
import com.example.topupadmin.model.OutputMessage;
import com.example.topupadmin.model.User;
import com.example.topupadmin.repository.UserGroupRepo;
import com.example.topupadmin.service.UserRedisDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class TopupController {

    @Autowired
    private UserRedisDaoImpl userRedisDao;

    @Autowired
    private UserGroupRepo userGroupRepo;



    @GetMapping("/")
    public String get(Model model) {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Optional<UserGroupEntity> userGroupEntity = userGroupRepo.findBySessionId(sessionId);
        if(!userGroupEntity.isPresent()) {
            User user = new User();
            user.setUserId(sessionId);
            userRedisDao.saveUser(user);
        }
        return "index";
    }

//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public OutputMessage send(Message message) throws Exception {
//        String time = new SimpleDateFormat("HH:mm").format(new Date());
//        return new OutputMessage(message.getFrom(), message.getText(), time);
//    }
}
