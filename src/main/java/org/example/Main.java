package org.example;

import org.example.dao.StudentDao;
import org.example.enity.Student;
import org.example.manager.StudentManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //StudentDao dao = new StudentDao();
        StudentManager studentManager = new StudentManager(new StudentDao());

        Scanner sc = new Scanner(System.in);
        int choose;
        while (true) {
            System.out.println("1. Find all students");
            System.out.println("2. Find student by ID");
            System.out.println("3. Find student by name");
            System.out.println("4. Add new student");
            System.out.println("5. Update student");
            System.out.println("6. Delete student");
            System.out.println("7. Exit \n");

            choose = Integer.parseInt(sc.nextLine());

            if (choose == 7) {
                System.out.println("Thank you for using JDBC");
                break;
            } else {
                if (choose == 1) {
                    System.out.println();
                    List<Student> students = studentManager.getAllStudent();
                    if (students.isEmpty()) {
                        System.out.println("No students found");
                    } else {
                        System.out.println("List of all students");
                        System.out.println();
                        for (Student student : students) {
                            System.out.println(student);
                        }
                        System.out.println();
                    }
                } else if (choose == 2) {
                    System.out.println();
                    System.out.println("Enter student ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println();
                    if (studentManager.getStudentById(id) == null) {
                        System.out.println("Student with id " + id + " was not found");
                    } else {
                        System.out.println("Student found with ID " + id + "\n");
                        System.out.println(studentManager.getStudentById(id));
                    }
                    System.out.println();
                } else if (choose == 3) {
                    System.out.println();
                    System.out.println("Enter student name: ");
                    String name = sc.nextLine();
                    System.out.println();
                    if (studentManager.getStudentByName(name).isEmpty()) {
                        System.out.println("Student with name " + name + " does not exist");
                    } else {
                        System.out.println("List of all students by name: " + name + "\n");
                        for (Student student : studentManager.getStudentByName(name)) {
                            System.out.println(student);
                        }
                    }
                    System.out.println();
                } else if (choose == 4) {
                    System.out.println("Enter student name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter student age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    Student student = new Student(name, age);
                    System.out.println();
                    if (studentManager.insertStudent(student)) {
                        System.out.println("Student with name " + name + " added");
                    } else {
                        System.out.println("Something went wrong");
                    }
                    System.out.println();
                } else if (choose == 5) {
                    System.out.println("Enter student ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter the new student name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter the new student age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    Student student = new Student(id, name, age);
                    System.out.println();
                    if (studentManager.updateStudent(student)) {
                        System.out.println("Student with id " + id + " updated");
                    } else {
                        System.out.println("Student with id " + id + " not found");
                    }
                    System.out.println();
                } else if (choose == 6) {
                    System.out.println("Enter student ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println();
                    if (studentManager.deleteStudent(id)) {
                        System.out.println("Student with id " + id + " deleted successfully");
                    } else {
                        System.out.println("Student with id " + id + " does not exist");
                    }
                    System.out.println();
                }
            }
        }
    }
}