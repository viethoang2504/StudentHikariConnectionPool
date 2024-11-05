package org.example.repository;

import org.example.enity.Student;

import java.util.List;

public interface StudentDaoInterface {
    boolean add(Student student);
    List<Student> findAll();
    boolean delete(int id);
    boolean update(Student student);
    Student findById(int id);
    List<Student> findByName(String name);
}
