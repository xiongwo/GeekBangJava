package witcher.cirilla.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class School implements ISchool {

    private int id;
    private String name;

    @Override
    public void ding(){
        System.out.println("ding -- id: " + id + " " + "name: " + name);
    }

    @Override
    public Student getStudent() {
        return new Student(id, name);
    }

}
