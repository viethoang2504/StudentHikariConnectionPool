package org.example.manager;

import org.example.enity.Student;
import org.example.repository.StudentDaoInterface;

import java.util.List;

public class StudentManager {
    private StudentDaoInterface studentDao;

    public StudentManager(StudentDaoInterface studentDao) {
        this.studentDao = studentDao;
    }

    public boolean insertStudent(Student student) {
        return studentDao.add(student);
    }

    public List<Student> getAllStudent() {
        return studentDao.findAll();
    }

    public boolean updateStudent(Student student) {
        return studentDao.update(student);
    }

    public boolean deleteStudent(int id) {
        return studentDao.delete(id);
    }

    public Student getStudentById(int id) {
        return studentDao.findById(id);
    }

    public List<Student> getStudentByName(String name) {
        return studentDao.findByName(name);
    }

    public void showMenu() {

    }
}
