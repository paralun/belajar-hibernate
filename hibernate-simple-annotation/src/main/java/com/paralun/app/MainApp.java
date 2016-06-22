/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app;

import com.paralun.app.model.Student;
import com.paralun.app.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class MainApp {

    public static void main(String[] args) {
        MainApp app = new MainApp();
        
        int studentId1 = app.save("Sam", "Disilva", "Maths");
        int studentId2 = app.save("Joshua", "Brill", "Science");
        int studentId3 = app.save("Peter", "Pan", "Physics");
        int studentId4 = app.save("Bill", "Laurent", "Maths");
        
        System.out.println("List Student");
        System.out.println("ID\tFirst Name\tLast Name\tSection");
        System.out.println("========================");
        List<Student> students = app.getStudents();
        for(Student s : students){
            System.out.println(String.format("%d\t%s\t%s\t%s", 
                    s.getId(), s.getFirstName(), s.getFirstName(), s.getSection()));
        }
        
         app.update(studentId4, "ARTS");
         app.delete(studentId2);
        
        System.out.println("List Student setelah Update dan Delete");
        List<Student> students1 = app.getStudents();
        for(Student s : students1){
            System.out.println(String.format("%d\t%s\t%s\t%s", 
                    s.getId(), s.getFirstName(), s.getFirstName(), s.getSection()));
        }
    }
    
    public int save(String firstName, String lastName, String section){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setSection(section);
        
        int id = (int) session.save(student);
        session.getTransaction().commit();
        return id;
    }
    
    public void update(int id, String section){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Student student = (Student) session.get(Student.class, id);
        student.setSection(section);
        session.update(student);
        session.getTransaction().commit();
    }
    
    public void delete(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Student student = (Student) session.get(Student.class, id);
        session.delete(student);
        session.getTransaction().commit();
    }
    
    public List<Student> getStudents(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<Student> students = session.createQuery(
                "FROM Student s ORDER BY s.firstName ASC").list();
        session.getTransaction().commit();
        return students;
    }
    
}
