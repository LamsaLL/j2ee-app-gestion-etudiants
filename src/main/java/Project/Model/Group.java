package Project.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = false, nullable = false)
    private String name;

    @OneToMany(mappedBy="group", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // LAZY = fetch when needed, EAGER = fetch immediately
    private List<Student> students;

//    @ManyToMany(mappedBy = "groups", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private final List<Module> modules = new ArrayList<>();

    public Group() {
        super();
    }

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

//
//    public List<Module> getModules() {
//        return modules;
//    }
//
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.setGroup(this);
    }
//
//    public void addModule(Module module) {
//        this.modules.add(module);
//        module.getGroups().add(this);
//    }
//
//    public void removeModule(Module module) {
//        this.modules.remove(module);
//        module.getGroups().remove(this);
//    }
//
    public void removeStudent(Student student) {
        this.students.remove(student);
        student.setGroup(null);
    }


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Group)) return false;
//        return id != null && id.equals(((Group) o).id);
//    }
//
//    @Override
//    public int hashCode() {
//        return id;
//    }
}
