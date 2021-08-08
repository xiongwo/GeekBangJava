package witcher.cirilla.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import witcher.cirilla.jdbc.config.ApplicationContextGetBeanHelper;
import witcher.cirilla.jdbc.dao.OrderCartMapper;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        OrderCartMapper om = (OrderCartMapper) ApplicationContextGetBeanHelper.getBean("orderCartMapper");
        List<Map<String, Object>> mList = om.getOrderCartFromMaster();
        List<Map<String, Object>> sList = om.getOrderCartFromSlave();
    }

}
