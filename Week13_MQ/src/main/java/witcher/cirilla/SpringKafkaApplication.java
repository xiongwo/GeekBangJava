package witcher.cirilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import witcher.cirilla.kafka.MPKafkaProducer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringKafkaApplication {

    public static void main( String[] args ) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringKafkaApplication.class, args);
        MPKafkaProducer producer = context.getBean(MPKafkaProducer.class);
        for (int i = 0; i < 10; i++) {
            producer.send(i);
            TimeUnit.SECONDS.sleep(2);
        }
    }

}
