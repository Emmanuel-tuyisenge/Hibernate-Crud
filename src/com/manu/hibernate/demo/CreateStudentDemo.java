package com.manu.hibernate.demo;

import com.manu.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		try{
			//create a student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Paul2", "Wilder2", "paul2@test.com");

			// start a transaction
			session.beginTransaction();

			//save the student object
			System.out.println("Saving student...");
			session.save(tempStudent);

			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");

		}
		finally{
			factory.close();
		}
	}

}
