/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app;

import com.paralun.app.model.Student;
import com.paralun.app.model.University;
import com.paralun.app.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class MainApp {

    public static void main(String[] args) {

        Student student1 = new Student("Sam","Disilva","Maths");
        Student student2 = new Student("Joshua", "Brill", "Science");
        Student student3 = new Student("Peter", "Pan", "Physics");

        University university = new University("CAMBRIDGE", "ENGLAND");
        
        student1.setUniversity(university);
        student2.setUniversity(university);
        student3.setUniversity(university);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(university);
        session.persist(student1);
        session.persist(student2);
        session.persist(student3);
        
        List<Student> students = (List<Student>)session.createQuery("from Student ").list();
        for(Student s: students){
            System.out.println("Details : "+s);
            System.out.println("Student University Details: "+s.getUniversity());
        }
        
        session.getTransaction().commit();
        session.close();
    }

}
