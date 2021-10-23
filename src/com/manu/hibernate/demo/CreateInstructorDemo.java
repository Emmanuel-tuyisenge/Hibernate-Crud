package com.manu.hibernate.demo;

import com.manu.hibernate.demo.entity.Course;
import com.manu.hibernate.demo.entity.Instructor;
import com.manu.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructorDemo {

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
			//create the objects
			Instructor tempInstructor =
				new Instructor("Kelly", "Public", "cool@test.com");

			InstructorDetail tempInstructorDetail =
				new InstructorDetail(
					"https://www.youtube.com",
					"All in one!"
				);

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			// Note: this will also save the details object bcz of CascadeType.ALL
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!");

		}
		finally{
			// add clean up code
			session.close();

			factory.close();
		}
	}

}
