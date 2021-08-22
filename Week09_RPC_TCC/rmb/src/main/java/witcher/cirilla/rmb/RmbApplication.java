package witcher.cirilla.rmb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
@MapperScan("witcher.cirilla.rmb.dao")
public class RmbApplication {

	public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(RmbApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
	}

}
