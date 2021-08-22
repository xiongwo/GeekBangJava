package witcher.cirilla.dollar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
@MapperScan("witcher.cirilla.dollar.dao")
public class DollarApplication {

	public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DollarApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
	}

}
