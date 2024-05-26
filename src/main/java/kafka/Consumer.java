package kafka;

import controllers.DatabaseController;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = "userDeletion", groupId = "comments")
    public void listen(String message) {
        DatabaseController controller = new DatabaseController();

        controller.deleteUserComments(Integer.parseInt(message));
    }

}
