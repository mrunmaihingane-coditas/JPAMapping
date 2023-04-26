package dao;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.text.ParseException;

public interface Dao {

    void insertStudent() throws IOException, ParseException;


     void updateStudent(int id) throws IOException ;



    void deleteStudent(int id) throws IOException;

   void ShowStudent(int id) throws IOException;


}
