package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
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
            findCoursesForStudent(appDAO);
        };
    }

    private void findCoursesForStudent(AppDAO appDAO) {
        Course courses = appDAO.findCoursesForStudentById(10);
        System.out.println(courses.getStudents());
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        Course course = new Course("patates nasil soyulur");
        Student student1 = new Student("rasid", "patatesci", "aptates@gmail.com");
        Student student2 = new Student("kizartma", "salata", "aptates@gmail.com");
        course.add(student1);
        course.add(student2);
        appDAO.save(course);
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        appDAO.deleteCourseById(10);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        Course c = appDAO.findCourseAndReviewsByCourseId(10);
        System.out.println(c);
        System.out.println(c.getReview());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("createCourse");
        course.add(new Review("yorum"));
        course.add(new Review("basima gelecek seylerden google play store sorumludur"));
        appDAO.save(course);
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
        System.out.println(instructorByIdJoinFetch.getCourse().get(0).getReview());
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
        instructor.setInstructorDetail(instructorDetail);

        Course course = new Course("5");
        Course course2 = new Course("6");

        course.add(new Review("yorum1"));
        course.add(new Review("yorum2"));

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
        System.out.println(byId.getCourse().get(0).getReview());
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
