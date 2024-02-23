package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);
    List<Instructor> getAll();
    Instructor findById(int id);
    void change(int id);
    void deleteById(int id);
    InstructorDetail findInstructorDetailById(int id);
}
