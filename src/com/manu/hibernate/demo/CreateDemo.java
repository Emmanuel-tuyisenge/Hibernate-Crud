package com.manu.hibernate.demo;

import com.manu.hibernate.demo.entity.Instructor;
import com.manu.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		try{
/*
			// create the objects
			Instructor tempInstructor =
				new Instructor("Tintin", "Wilder", "wild@test.com");

			InstructorDetail tempInstructorDetail =
				new InstructorDetail(
					"https://www.youtube.com/watch?v=TmCZ2Qz2TbI",
					"LUV this BD!!"
				);*/

			Instructor tempInstructor =
				new Instructor("Cool", "Nice", "cool@test.com");

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
			factory.close();
		}
	}

}
