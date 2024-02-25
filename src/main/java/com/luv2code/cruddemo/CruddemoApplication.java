package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
           createInstructorWithCourses(appDAO);
        };
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor instructor = new Instructor("Fatih", "kizartmaso", "biseyler@gmail.com");
        InstructorDetail instructorDetail = new InstructorDetail("youtube", "rawr");
        Course course =new Course("ilk kurs");
        Course course2 =new Course("ikinci");
        instructor.add(course);
        instructor.add(course2);
        instructor.setInstructorDetail(instructorDetail);
        appDAO.save(instructor);
    }

    private void findInstructorDetail(AppDAO appDAO) {
        InstructorDetail byId = appDAO.findInstructorDetailById(2);
        System.out.println(byId.getId());
        System.out.println(byId.getInstructor());
        System.out.println(byId.getInstructor().getFirstName());
    }

    private void deleteId(AppDAO appDAO) {
        appDAO.deleteById(1);
    }

    private void findInstructorById(AppDAO appDAO) {
        Instructor byId = appDAO.findById(1);
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
