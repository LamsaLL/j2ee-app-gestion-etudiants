package Project.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Student")
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Speciality speciality;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Absence> absences;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mark> marks;


    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
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

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return this.speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
        if (!speciality.getStudents().contains(this)) {
            speciality.getStudents().add(this);
        }
    }

    public List<Mark> getMarks() {
        return this.marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public float getAverageMark() {
        List<Mark> marks = this.getMarks();

        float sum = 0;
        int i = 0;

        for (Mark mark : marks) {
            sum = sum + mark.getValue();
            i++;
        }
        return sum / i;
    }

    public Mark getMarkByModule(Module module) {
        List<Mark> marks = this.getMarks();

        for (Mark mark : marks) {
            if (mark.getModule() == module) {
                return mark;
            }
        }
        return null;
    }
}
