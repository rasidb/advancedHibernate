package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);
    List<Instructor> getAll();
}
