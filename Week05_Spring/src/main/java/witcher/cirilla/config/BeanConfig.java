package witcher.cirilla.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import witcher.cirilla.bean.InjectBean;

@Configuration
public class BeanConfig {

    @Bean(name = "injectBean")
    public InjectBean injectBean() {
        return new InjectBean();
    }

}
