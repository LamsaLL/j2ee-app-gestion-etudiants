package Project.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "integer", name = "group_id", nullable = true)
    private Group group;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Absence> absences;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mark> marks;

    private static final long serialVersionUID = 1L;

    public Student() {
        super();
    }

//    public Student(Integer id, String firstName, String name) {
//        super();
//        this.id = id;
//        this.firstName = firstName;
//        this.name = name;
//    }

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

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
        if (!group.getStudents().contains(this)) {
            group.getStudents().add(this);
        }
    }

    public List<Mark> getMarks() {
        return this.marks;
    }

    public float getAverage() {
        List<Mark> marks = this.getMarks();

        float average = 0;
        int i = 0;

        for (Mark mark : marks) {
            average = average + mark.getValue();
            i++;
        }
        return average / i;
    }
}
