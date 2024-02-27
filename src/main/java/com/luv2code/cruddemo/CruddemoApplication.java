package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
      deleteCourseById(appDAO);
        };
    }

    private void deleteCourseById(AppDAO appDAO) {
        appDAO.deleteCourseById(11);
    }

    private void deleteInstructor(AppDAO appDAO) {
        appDAO.deleteInstructorById(1);
    }

    private void updateCourse(AppDAO appDAO) {
        Course course = appDAO.findCourseById(10);
        course.setTitle("patatesKizartmasi");
        appDAO.update(course);
    }

    private void updateInstructor(AppDAO appDAO) {
        int id = 1;
        Instructor instructor = appDAO.findInstructorById(id);
        instructor.setLastName("test");
        appDAO.update(instructor);
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        Instructor instructorByIdJoinFetch = appDAO.findInstructorByIdJoinFetch(1);
        System.out.println(instructorByIdJoinFetch);
        System.out.println(instructorByIdJoinFetch.getCourse());
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        System.out.println(appDAO.findCoursesByInstructorId(1));
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        Instructor byId = appDAO.findInstructorById(1);
        System.out.println(byId);
        System.out.println(byId.getCourse());
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor instructor = new Instructor("patates", "kizartmasi", "biseyler@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("youtube", "rawr");
        Course course = new Course("5");
        Course course2 = new Course("6");
        instructor.setInstructorDetail(instructorDetail);
        instructor.add(course);
        instructor.add(course2);

        appDAO.save(instructor);
        System.out.println(instructor.getCourse());
    }

    private void findInstructorDetail(AppDAO appDAO) {
        InstructorDetail byId = appDAO.findInstructorDetailById(2);
        System.out.println(byId.getId());
        System.out.println(byId.getInstructor());
        System.out.println(byId.getInstructor().getFirstName());
    }

    private void deleteId(AppDAO appDAO) {
        appDAO.deleteInstructorById(1);
    }

    private void findInstructorById(AppDAO appDAO) {
        Instructor byId = appDAO.findInstructorById(1);
        System.out.println(byId);
    }

    private void createInstructor(AppDAO appDAO) {
        Instructor instructor = new Instructor("Rasid", "babamgul", "biseyler@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("youtube", "patates");
        appDAO.save(instructor);
        instructor.setInstructorDetail(instructorDetail);
        appDAO.save(instructor);
        appDAO.change(2);
    }
}
