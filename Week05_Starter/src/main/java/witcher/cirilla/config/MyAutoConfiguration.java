package witcher.cirilla.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import witcher.cirilla.bean.School;
import witcher.cirilla.bean.StudentProperties;

@Configuration
@EnableConfigurationProperties(value = StudentProperties.class)
public class MyAutoConfiguration {

    private final StudentProperties properties;

    public MyAutoConfiguration(StudentProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public School school(){
        return new School(properties.getId(), properties.getName());
    }

}
