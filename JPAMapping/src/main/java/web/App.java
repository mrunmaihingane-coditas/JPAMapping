package web;


import dao.DaoImpl;
import model.Student;
import model.Subject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, SQLException, ParseException {
        System.out.println("CRUD JPA SYSTEM");


        DaoImpl daoimpl = new DaoImpl();
        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean flag = true;
        while (flag) {
            System.out.println("1.Insert  ");
            System.out.println("2.Insert Student");
            System.out.println("3.All Student who enroll ");
            System.out.println("4.All Students who subject is");
            System.out.println("5.Featch Student who teacher aarti ");
            System.out.println("6.Insert Subject ");
            System.out.println("7.  ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    daoimpl.insert();
                    break;
                case 2:
                    daoimpl.insertStudent();
                    break;
                case 3:
                    daoimpl.printAnimalsByEnteredDateRange();
                    break;
                case 4:
                    System.out.println("Enter Subject Name to see list of student ");
                    String subjectname= br.readLine();
                    daoimpl.printStudentsBySubject(subjectname);
                    break;
                case 5:
                    System.out.println("Enter Teacher Name ");
                    daoimpl.getStudentsByTeacher(br.readLine());
                    break;
                case 6:
                    daoimpl.insertSubject();
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    flag = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}

