package com.manu.hibernate.demo;

import com.manu.hibernate.demo.entity.Course;
import com.manu.hibernate.demo.entity.Instructor;
import com.manu.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class EagerLazyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		try{

			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			System.out.println("Wild code: Instructor: "+ tempInstructor);

			// get course for the instructor
			System.out.println("Wild code: Courses: " + tempInstructor.getCourses());

			//commit transaction
			session.getTransaction().commit();

			// Close the session
			session.close();

			// Since courses are lazy loaded ... this should fail

			System.out.println("\nWild code: The session is now closed!\n");

			// get course for the instructor
			System.out.println("Wild code: Courses: " + tempInstructor.getCourses());

			System.out.println("Wild code: Done!!");

		}
		finally{
			// add clean up code
			session.close();

			factory.close();
		}
	}

}
