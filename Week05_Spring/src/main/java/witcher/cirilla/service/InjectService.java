package witcher.cirilla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import witcher.cirilla.dao.InjectDao;

@Service
public class InjectService {

    @Autowired
    private InjectDao dao;

    public void speak() {
        System.out.println("this is InjectService");
        dao.speak();
    }

}
