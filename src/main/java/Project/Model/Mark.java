package Project.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Mark implements Serializable {
    @Column(nullable = true)
    private float value;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

//    @Id
//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Module module;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

//    public Module getModule() {
//        return module;
//    }

//    public void setModule(Module module) {
//        this.module = module;
//    }
}
