package com.manu.hibernate.demo;

import com.manu.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {

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
			Student tempStudent = new Student("Daffy", "Duck", "daffy@test.com");

			// start a transaction
			session.beginTransaction();

			//save the student object
			System.out.println("Saving student...");
			System.out.println(tempStudent);
			session.save(tempStudent);

			//commit transaction
			session.getTransaction().commit();

			// my new code

			// find out student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());

			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on the id: primary key
			System.out.println("Getting student with id: " + tempStudent.getId());
			Student mysStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get complete: " + mysStudent);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!!");

		}
		finally{
			factory.close();
		}
	}

}
