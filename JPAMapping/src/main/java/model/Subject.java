package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subject_id;

    private String subject_name;

    @ManyToMany(mappedBy = "subjectList")
    private List<Student> studentList;

    @OneToOne(mappedBy = "subject",cascade = CascadeType.ALL)
    private Teacher teacher;

    public Subject(String subject_name, Teacher teacher) {
        this.subject_name = subject_name;
        this.teacher = teacher;
    }

    public Subject() {
    }


}
