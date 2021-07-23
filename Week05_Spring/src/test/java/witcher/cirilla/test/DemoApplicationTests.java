package witcher.cirilla.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import witcher.cirilla.bean.InjectBean;
import witcher.cirilla.bean.School;
import witcher.cirilla.jdbc.JdbcRepository;
import witcher.cirilla.service.InjectService;

import javax.annotation.Resource;

@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private School school;

    @Test
    public void testStarter(){
        // 测试 starter
        System.out.println(school);
        school.ding();
        System.out.println(school.getStudent());
    }

    @Autowired
    private InjectService service;

    @Resource(name = "injectBean")
    private InjectBean injectBean;

    @Resource(name = "xmlInjectBean")
    private InjectBean xmlInjectBean;

    @Autowired
    private JdbcRepository jdbcRepository;

    @Test
    public void testAutoBean() {
        // 测试 bean 的装配
        service.speak();
        System.out.println("===============");
        injectBean.speak();
        System.out.println("===============");
        xmlInjectBean.speak();
    }

    @Test
    public void testJdbc() throws Exception {
        // 测试 JDBC
        jdbcRepository.test();
    }

}
