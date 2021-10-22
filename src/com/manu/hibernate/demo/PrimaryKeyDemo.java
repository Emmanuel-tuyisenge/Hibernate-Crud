package com.manu.hibernate.demo;

import com.manu.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

    public static void main(String[] args) {


		// create session factory
		SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Student.class)
        .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();
        try{
        //create 3 students objects
        System.out.println("Creating 3 student objects...");
        Student tempStudent = new Student("Paul", "Wilder", "Wilder@test.com");
        Student tempStudent2 = new Student("Cool", "Nice", "cool@test.com");
        Student tempStudent3 = new Student("Didine", "Kelly", "didine@test.com");

        // start a transaction
        session.beginTransaction();

        //save the student object
        System.out.println("Saving student...");
        session.save(tempStudent);
        session.save(tempStudent2);
        session.save(tempStudent3);

        //commit transaction
        session.getTransaction().commit();
        System.out.println("Done!!");

        }
        finally{
        factory.close();
        }
    }
}
