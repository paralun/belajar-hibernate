/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app;

import com.paralun.app.model.Address;
import com.paralun.app.model.Student;
import com.paralun.app.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class MainApp {

    public static void main(String[] args) {
        Student student = new Student("Sam","Disilva","Maths");
        Address address = new Address("10 Silver street","NYC","USA");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.persist(address);
        student.setAddress(address);
        session.persist(student);
        
        List<Student> students = session.createQuery("from Student").list();
        for(Student s : students){
            System.out.println("Details : " + s);
        }
        session.getTransaction().commit();
        session.close();
    }
}
