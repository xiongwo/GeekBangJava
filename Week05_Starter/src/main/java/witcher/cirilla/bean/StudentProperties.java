package witcher.cirilla.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "starter")
public class StudentProperties {

    private int id;

    private String name;

}
