package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacher_id;

    private String taacher_name;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<Student>studentList;

    @OneToOne
    private Subject subject;


}
