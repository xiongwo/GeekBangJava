package witcher.cirilla.dao;

import org.springframework.stereotype.Repository;

@Repository
public class InjectDao {

    public void speak() {
        System.out.println("this is InjectDao");
    }

}
