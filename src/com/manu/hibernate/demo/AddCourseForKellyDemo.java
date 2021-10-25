package com.manu.hibernate.demo;

import com.manu.hibernate.demo.entity.Course;
import com.manu.hibernate.demo.entity.Instructor;
import com.manu.hibernate.demo.entity.InstructorDetail;
import com.manu.hibernate.demo.entity.Review;
import com.manu.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AddCourseForKellyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		try{

			// start a transaction
			session.beginTransaction();

			// get the student Kelly from database
			int studentId = 2;
			Student tempStudent = session.get(Student.class, studentId);

			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("Courses: " + tempStudent.getCourses());

			// create more courses
			Course tempCourse1 = new Course("Lorem ipsum -  Dolor sit amet, consectetur adipiscing elit.");
			Course tempCourse2 = new Course("Sed non risus - Suspendisse lectus tortor.");

			// add students to courses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);

			// save the courses
			System.out.println("\nSaving the course ...");

			session.save(tempCourse1);
			session.save(tempCourse2);

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
