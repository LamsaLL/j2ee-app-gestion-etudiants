package Project.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Absence")
public class Absence implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime start;

    @Column(nullable = true, columnDefinition = "TIMESTAMP")
    private LocalDateTime end;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean justified;

    @ManyToOne
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isJustified() {
        return justified;
    }

    public void setJustified(boolean justified) {
        this.justified = justified;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime fin) {
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
