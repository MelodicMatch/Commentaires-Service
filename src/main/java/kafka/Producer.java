package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(int user, Integer replyTo, String commented, int commentedID) {
        kafkaTemplate.send("userComments", "userID=\"" + user + "\" "
                + (replyTo != null ? "replyto=\"" + replyTo + "\" " : "")
                + "commented=\"" + commented + "\" commentedID=\"" + commentedID + "\"");
    }

}
