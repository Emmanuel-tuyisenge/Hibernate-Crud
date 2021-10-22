package com.manu.hibernate.demo;

import java.util.List;

import com.manu.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		try{

			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			//display the students
			displayStudents(theStudents);

			// query students: lastName='Kelly'
			theStudents = session.createQuery("from Student s where s.lastName='Kelly'").getResultList();

			// display the students
			System.out.println("\n\nStudent who have last name of Kelly");
			displayStudents(theStudents);

			// query students where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%gmail.com'").getResultList();
			System.out.println("\n\nStudents whose have email ends gmail.com");
			displayStudents(theStudents);

			// query student: lastName='Kelly' or firstName='Daffy'
			theStudents =
				session.createQuery("from Student s where"
					+ " s.lastName='Kelly' OR s.firstName='Daffy'").getResultList();
			System.out.println("\n\nStudents who have last name of Kelly Or first name  Daffy");
			displayStudents(theStudents);

			//commit transaction
			session.getTransaction().commit();

			System.out.println("Done!!");

		}
		finally{
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
