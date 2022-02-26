package Project.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Speciality")
public class Speciality implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="speciality", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // LAZY = fetch when needed, EAGER = fetch immediately
    private List<Student> students;

    @ManyToMany(mappedBy="specialities", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private final List<Module> modules = new ArrayList<>();

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.setSpeciality(this);
    }

    public List<Module> getModules() {
        return modules;
    }

    public void addModule(Module module) {
        this.modules.add(module);
        module.getSpecialities().add(this);
    }

    public void removeModule(Module module) {
        this.modules.remove(module);
        module.getSpecialities().remove(this);
    }
//
    public void removeStudent(Student student) {
        this.students.remove(student);
        student.setSpeciality(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Speciality)) return false;
        return id != null && id.equals(((Speciality) o).id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
