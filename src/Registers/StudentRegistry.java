package Registers;


import CustomException.DuplicateIdException;
import CustomException.StudentNotFoundException;
import Models.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StudentRegistry {
    private static HashMap<String, Student> students = new HashMap<>();


    public static void addStudent(Student s) {

        if (students.containsKey(s.getId())) throw new DuplicateIdException("student is already in registry");
        for (Student S : students.values())
            if (S.getEmail().equalsIgnoreCase(s.getEmail()))
                throw new DuplicateIdException("student is already in registry");
        students.put(s.getId(), s);
        System.out.println("Student " + s.getId() + " has been added");
        System.out.println("Student " + s.getName() + " has been added");

    }

    public static void removeStudent(String id) {
        if (!students.containsKey(id)) throw new StudentNotFoundException("Student not found");
        students.remove(id);
    }

    public static Student findById(String id) throws StudentNotFoundException {

        for (Student student : students.values()) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        throw new StudentNotFoundException("Student not found");
    }

    public static ArrayList<Student> getStudents() {
        return new ArrayList<>(students.values());
    }

}
