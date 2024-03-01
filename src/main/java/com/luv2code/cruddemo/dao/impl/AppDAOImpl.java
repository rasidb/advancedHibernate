package com.luv2code.cruddemo.dao.impl;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    @Autowired
    EntityManager entityManager;

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public List<Instructor> getAll() {
        return entityManager.createQuery("from Instructor", Instructor.class).getResultList();
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void change(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        instructor.setFirstName("patates");
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        List<Course> courses = instructor.getCourse();
        for (Course course : courses) {
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id= :data ", Course.class);
        query.setParameter("data", id);
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " +
                "JOIN FETCH i.course " +
                "JOIN FETCH i.instructorDetail " +
                "where i.id = :data ", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(List<Course> courses) {
        entityManager.merge(courses);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " +
                "JOIN FETCH c.review " +
                "where c.id = :data", Course.class);
        query.setParameter("data", id);

        return query.getSingleResult();
    }

    @Override
    public Course findCoursesForStudentById(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " +
                "JOIN FETCH c.students " +
                "where c.id = :data", Course.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentCoursesByStudentId(int id) {

        TypedQuery<Student> query = entityManager.createQuery("select s from Student s " +
                "JOIN FETCH s.courses " +
                "where s.id= :data", Student.class);
        query.setParameter("data",id);
        return query.getSingleResult();

    }

    @Override
    public Student findStudentById(int id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

}
