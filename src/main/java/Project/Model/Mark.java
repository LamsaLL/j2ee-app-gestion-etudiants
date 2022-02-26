package Project.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Mark")
public class Mark implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = true)
    private float value;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Module module;

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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
