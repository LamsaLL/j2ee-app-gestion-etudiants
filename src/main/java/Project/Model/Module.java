package Project.Model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Module")
public class Module implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    // Onwner side => module.getSpecialitys.add(speciality)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "module_speciality",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private final List<Speciality> specialities = new ArrayList<Speciality>();

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Mark> marks = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void addSpeciality(Speciality speciality) {
        this.specialities.add(speciality);
        speciality.getModules().add(this);
    }

    public void removeSpeciality(Speciality speciality) {
        this.specialities.remove(speciality);
        speciality.getModules().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        return id != null && id.equals(((Module) o).id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    public List<Mark> getMarks() {
        return this.marks;
    }
}