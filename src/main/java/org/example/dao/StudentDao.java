package org.example.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.enity.Student;
import org.example.repository.StudentDaoInterface;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements StudentDaoInterface {

    private static HikariDataSource DATA_SOURCE;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/studentdb?user=root");
        config.setUsername("root");
        config.setPassword("123456");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);

        DATA_SOURCE = new HikariDataSource(config);
    }

    public StudentDao() {
    }

    ;

    /**
     * Select List Student from studentdb
     *
     * @return
     */
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DATA_SOURCE.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from student");

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));

                students.add(student);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("Failed to close resources: " + e.getMessage());
            }
        }

        return students;
    }

    /**
     * Add Student
     *
     * @param student
     * @return status
     */
    @Override
    public boolean add(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DATA_SOURCE.getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO student (id, name, age) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());

            preparedStatement.execute();
            preparedStatement.close();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Delete Student by ID
     *
     * @param id
     * @return status
     */
    @Override
    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DATA_SOURCE.getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM student WHERE id = ?");
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsAffected > 0; // Returns true if a student was deleted, false if no such id existed
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Update Student
     *
     * @param student
     * @return status
     */
    @Override
    public boolean update(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DATA_SOURCE.getConnection();

            preparedStatement = connection.prepareStatement("UPDATE student SET `age` = ?, `name` = ? WHERE (`id` = ?)");
            preparedStatement.setInt(1, student.getAge());
            preparedStatement.setInt(3, student.getId());
            preparedStatement.setString(2, student.getName());

            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Find student by ID
     *
     * @param id
     * @return student
     */
    @Override
    public Student findById(int id) {
        Student student = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DATA_SOURCE.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
            }
            return student;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return student;
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> students = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DATA_SOURCE.getConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                students.add(student);
            }
            resultSet.close();
            preparedStatement.close();
            return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return students;
    }

//    public static void main(String[] args) {
//        // Check connection
//        if(CONNECTION!=null){
//            System.out.println("Connection Successful\n");
//        } else {
//            System.out.println("Connection Failed\n");
//        }
//
//        // Find all students before add
//        StudentDao studentDao = new StudentDao();
//        for(Student student : studentDao.findAll()){
//            System.out.println(student);
//        }
//
//        // Delete student
//        Student deleteStudent = new Student();
//        deleteStudent.setId(6);
//
//        if (studentDao.delete(deleteStudent.getId())) {
//            System.out.println("Student Deleted Successfully");
//            System.out.println("List students after delete");
//
//            for(Student student : studentDao.findAll()){
//                System.out.println(student);
//            }
//        } else {
//            System.out.println("Student Deleted Failed");
//        }
//
//        // Add student
//        Student addStudent = new Student();
//        addStudent.setId(6);
//        addStudent.setName("Nguyen Truc Linh");
//        addStudent.setAge(18);
//
//        if (studentDao.add(addStudent)) {
//            System.out.println("\nAdd new student successful\n");
//            System.out.println("List students after add\n");
//            for(Student oneStudent : studentDao.findAll()){
//                System.out.println(oneStudent);
//            }
//        } else {
//            System.out.println("Add new student failed");
//        }
//
//        // Update student
//        Student updateStudent = new Student();
//        updateStudent.setId(6);
//        updateStudent.setName("Nguyen Truc Linh");
//        updateStudent.setAge(20);
//        if (studentDao.update(updateStudent)) {
//            System.out.println("\nUpdate student successful\n");
//            System.out.println("List students after update\n");
//            for(Student oneStudent : studentDao.findAll()){
//                System.out.println(oneStudent);
//            }
//        } else {
//            System.out.println("Update student failed");
//        }
//
//        // Find student by ID
//        Student findStudentByID = new Student();
//        findStudentByID.setId(6);
//        if (studentDao.findById(findStudentByID.getId()) != null) {
//            System.out.println("\nFind student successful\n");
//            System.out.println(studentDao.findById(findStudentByID.getId()));
//        } else {
//            System.out.println("Find student failed");
//        }
//
//        // Find student by Name
//        List<Student> findStudentByName = studentDao.findByName("Nguyen Tru");
//        if(!findStudentByName.isEmpty()){
//            System.out.println("\nFind student by name successful\n");
//            for (Student student : findStudentByName) {
//                System.out.println(student);
//            }
//        } else {
//            System.out.println("\nThere is no student with that name");
//        }
//    }
}
