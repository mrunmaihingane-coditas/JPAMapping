package dao;



import model.Student;
import model.Subject;
import model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoImpl implements Dao {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rt");

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void insertStudent() throws IOException, ParseException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("enter StudentId");
        int studentId= Integer.parseInt(br.readLine());

        Student student = entityManager.find(Student.class,studentId);

        entityManager.persist(student);

        System.out.println("Enter Number subjectto add");
        int choies= Integer.parseInt(br.readLine());

        List<Subject>subjectList=new ArrayList<>();
        for (int i=0;i<choies;i++) {

            System.out.println("Enter Subject Id ");
            int subject_Id = Integer.parseInt(br.readLine());
            Subject subject = entityManager.find(Subject.class,subject_Id );

            subjectList.add(subject);
            entityManager.persist(subject);

        }
        System.out.println("Add in list");
        student.setSubjectList(subjectList);

        entityManager.persist(student);

        entityManager.getTransaction().commit();
        entityManager.close();



    }
    public void insertSubject() throws IOException, ParseException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("enter SubjectId");
        int subjecttId= Integer.parseInt(br.readLine());

        Subject subject = entityManager.find(Subject.class,subjecttId);

        entityManager.persist(subject);

        System.out.println("Enter Number student to add");
        int choies= Integer.parseInt(br.readLine());

        List<Student>sutudentList=new ArrayList<>();
        for (int i=0;i<choies;i++) {

            System.out.println("Enter Student Name ");
            int student_Id = Integer.parseInt(br.readLine());
            Student student = entityManager.find(Student.class,student_Id );

            sutudentList.add(student);
            entityManager.persist(student);

        }
        System.out.println("Add in list");
        subject.setStudentList(sutudentList);

        entityManager.persist(subject);

        entityManager.getTransaction().commit();
        entityManager.close();



    }
    @Override
    public void updateStudent(int id) throws IOException {

    }

    @Override
    public void deleteStudent(int id) throws IOException {

    }

    @Override
    public void ShowStudent(int id) throws IOException {

    }
    public void insert() throws IOException, ParseException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("enter name of Teacher");
        String teacherName = br.readLine();
        Teacher teacher = new Teacher();
        teacher.setTaacher_name(teacherName);
        entityManager.persist(teacher);
        int choice;
        do {
            System.out.println("enter 1 to addStudent\nenter 2 to subject\n0 to exit");
            choice = Integer.parseInt(br.readLine());
            if (choice == 1) {
                System.out.println("enter Student name");
                String studentname=br.readLine();

                System.out.println("Enter Student_date of Enrollment (yyyy-MM-dd):");
                String dateString = br.readLine();
                Date datepulish = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                Student animal=new Student(studentname,datepulish,teacher);
                entityManager.persist(animal);

            }
            if (choice == 2) {
                System.out.println("enter subject name");
                String subjectname=br.readLine();

                Subject subject=new Subject(subjectname,teacher);
                teacher.setSubject(subject);
                entityManager.persist(subject);

            }


        }while(choice!=0);
        entityManager.getTransaction().commit();
        entityManager.close();

    }public void printAnimalsByEnteredDateRange() throws IOException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Enter Range for Starting date (yyyy-mm-dd)");
        java.sql.Date StartingDate = java.sql.Date.valueOf(br.readLine());

        System.out.println("Enter Range for Ending date (yyyy-mm-dd)");
        java.sql.Date EndRange = java.sql.Date.valueOf(br.readLine());

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Student> animalRoot = criteriaQuery.from(Student.class);

        criteriaQuery.multiselect(animalRoot.get("student_name"), animalRoot.get("enrollment_date"));
        criteriaQuery.where(criteriaBuilder.between(animalRoot.<Comparable>get("enrollment_date"),StartingDate,EndRange));


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> results = typedQuery.getResultList();

        for (Object[] result : results) {
            System.out.println("Student Name: " + result[0] + ", Enrollment date: " + result[1]);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void printStudentsBySubject(String subjectName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Create CriteriaQuery and Root objects
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> studentRoot = cq.from(Student.class);
        Join<Student, Subject> subjectJoin = studentRoot.join("subjectList");

        // Set WHERE clause to filter by subject name
        cq.where(cb.equal(subjectJoin.get("subject_name"), subjectName));

        // Execute query and print student names
        List<Student> students = entityManager.createQuery(cq).getResultList();
        for (Student student : students) {
            System.out.println(student.getStudent_name());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void getStudentsByTeacher(String teacherName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);
        Join<Student, Teacher> teacherJoin = studentRoot.join("teacher");
        query.select(studentRoot)
                .where(cb.equal(teacherJoin.get("taacher_name"), teacherName));
        List<Student> students = entityManager.createQuery(query).getResultList();
        for (Student student : students) {
            System.out.println(student.getStudent_name());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }






}