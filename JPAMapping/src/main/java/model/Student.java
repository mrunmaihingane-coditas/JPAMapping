package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_id;

    private String student_name;

    private Date enrollment_date;

    @ManyToMany
    private List<Subject>subjectList;

    @ManyToOne
    private Teacher teacher;

    public Student(String student_name, Date enrollment_date, List<Subject> subjectList) {
        this.student_name = student_name;
        this.enrollment_date = enrollment_date;
        this.subjectList = subjectList;
    }

    public Student() {
    }



    public Student(String student_name, Date enrollment_date, Teacher teacher) {
        this.student_name = student_name;
        this.enrollment_date = enrollment_date;
        this.teacher = teacher;
    }
}
