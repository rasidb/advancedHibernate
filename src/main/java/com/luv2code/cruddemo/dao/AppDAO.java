package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    List<Instructor> getAll();

    Instructor findInstructorById(int id);

    void change(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void update(List<Course> courses);

    void update(Course course);

    Course findCourseById(int id);

    void deleteCourseById(int id);
    void save(Course course);
    Course findCourseAndReviewsByCourseId(int id);

    Course findCoursesForStudentById(int id);

    Student findStudentCoursesByStudentId(int id);
    Student findStudentById(int id);

    void saveStudent(Student student);

    void deleteStudentById(int id);

}
