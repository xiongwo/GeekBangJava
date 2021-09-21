package witcher.cirilla.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MPKafkaConsumer {

    @KafkaListeners({@KafkaListener(topics = "test")})
    public void listener(ConsumerRecord record){
        Optional<Object> msg = Optional.ofNullable(record.value());
        if(msg.isPresent()){
            System.out.println(msg.get());
        }
    }

}
