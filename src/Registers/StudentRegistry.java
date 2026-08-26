package Registers;


import CustomException.DuplicateIdException;
import CustomException.StudentNotFoundException;
import Models.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentRegistry {
    private static HashMap<String, Student> students = new HashMap<>();


    public static void addStudent(Student s) {

        if (s == null) throw new NullPointerException("student is null");
        else if (students.containsKey(s.getId())) throw new DuplicateIdException("student is already in registry");
        for (String value : students.keySet()) System.out.println("the student already added");
        for (Student S : students.values()) {
            String email = s.getEmail();
            if (email.equals(s.getEmail())) throw new DuplicateIdException("student is already in registry");
        }
        students.put(s.getId(), s);
        System.out.println("Student " + s.getId() + " has been added");
        System.out.println("Student " + s.getName() + " has been added");

    }

    public static void removeStudent(String id) {
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

    public static String findByName(String name) throws StudentNotFoundException {
        for (Student s : students.values()) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s.getName();
            }
        }
        throw new StudentNotFoundException("student not found");
    }

    public static ArrayList<Student> getStudents() {
        if (students.isEmpty()) throw new NullPointerException("student is empty");
        return new ArrayList<>(students.values());
    }

}
