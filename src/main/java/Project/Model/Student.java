package Project.Model;

import java.io.Serializable;

public class Student implements Serializable {
    private Integer id;
    private String firstName;
    private String name;

    public Student() {
        super();
    }

    public Student(Integer id, String firstName, String name) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setNom(String nom) {
        this.name = name;
    }
}
