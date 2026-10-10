package AppMenu;

import CustomException.CourseNotFoundException;
import CustomException.DuplicateIdException;
import CustomException.InvalidGradeException;
import CustomException.StudentNotFoundException;
import Models.Course;
import Models.Grade;
import Models.Student;

import java.util.*;


import static GradeCalculator.GradeCalculator.calculateGPA;
import static Registers.CourseRegistry.*;
import static Registers.StudentRegistry.*;

public class AppMenu {


    static Scanner sc = new Scanner(System.in);


    public static boolean run() {

        boolean running = true;

        while (running) {

            try {
                System.out.println("===== Student Grade Manager =====");
                System.out.println("1. Add Student");
                System.out.println("2. Add Course");
                System.out.println("3. Enroll Student in Course");
                System.out.println("4. Record Grade");
                System.out.println("5. View Student Transcript");
                System.out.println("6. View Top Students by GPA");
                System.out.println("7. View All Students in a Course");
                System.out.println("8. Remove Student");
                System.out.println("9. Exit");
                System.out.println("=================================");

                System.out.print("Enter your choice: ");

                String input = sc.nextLine();
                intCheckNullOrEmpty(input);
                int input2 = Integer.parseInt(input);

                if (input2 < 1 || input2 > 9) {
                    throw new IndexOutOfBoundsException("the input should be number between 1 and 9");
                }

                switch (input2) {

                    case 1:
                        addStudentMenu();
                        break;

                    case 2:
                        addCourseMenu();
                        break;

                    case 3:
                        enrollStudentInCourseMenu();
                        break;

                    case 4:
                        recordGradeMenu();
                        break;

                    case 5:
                        viewStudentTranscriptMenu();
                        break;

                    case 6:
                        viewTopStudentsByGPA();
                        break;

                    case 7:
                        viewAllStudentsInCourseMenu();
                        break;

                    case 8:
                        removeStudentMenu();
                        break;

                    case 9:
                        System.out.println("Goodbye!");
                        sc.close();
                        return false;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("Error :" + e.getMessage());
            }
        }
        return true;
    }

    public static void addStudentMenu() {
        System.out.print("Enter Student Name:");
        String name = AppMenu.sc.nextLine();
        nullCheckNullOrEmpty(name);
        stringCheck(name);

        System.out.println("Enter Student Email:");
        System.out.print("Please enter the Local Part (@gmail.com will be added automatically): ");
        String email = AppMenu.sc.nextLine();
        nullCheckNullOrEmpty(email);


        Student student = new Student(name, email);
        addStudent(student);
        System.out.println("Student Name : "+student.getName()+" ,StudentId : "+student.getId()+" Added Successfully");

    }

    public static void addCourseMenu() {
        System.out.print("Enter Course Name:");
        String name = AppMenu.sc.nextLine();
        nullCheckNullOrEmpty(name);
        stringCheck(name);

        System.out.println("Enter Credit Hours :");

        String input = AppMenu.sc.nextLine();
        nullCheckNullOrEmpty(input);


        int creditHour = Integer.parseInt(input);
        Course course = new Course(name, creditHour);


        addCourse(course);
        System.out.println("Course Name" + course.getName()+ " ,Course Code" + course.getCourseCode()+ " Added Successfully");
    }

    public static void enrollStudentInCourseMenu() {
        System.out.print("Enter Student Name Id ");
        String studentId = AppMenu.sc.nextLine();

        System.out.print("Enter Course Id :");
        String courseId = AppMenu.sc.nextLine();

        try {
            Student student = findById(studentId);
            Course course = findByCode(courseId);

            student.enroll(course);
            System.out.println("Student Enrolled Successfully");

        } catch (DuplicateIdException e) {
            System.out.println("Student with this course already exists");
        } catch (StudentNotFoundException | CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void recordGradeMenu() {

        try {
            System.out.print("Enter Student Id :");
            String studentId = AppMenu.sc.nextLine();
            nullCheckNullOrEmpty(studentId);

            System.out.print("Enter Course code :");
            String courseCode = AppMenu.sc.nextLine();
            nullCheckNullOrEmpty(courseCode);


            System.out.print("Enter Exam name :");
            String examName = AppMenu.sc.nextLine();
            nullCheckNullOrEmpty(examName);
            stringCheck(examName);

            System.out.print("Enter score :");
            String input = AppMenu.sc.nextLine();
            nullCheckNullOrEmpty(input);
            double score = Double.parseDouble(input);

            Student student = findById(studentId);
            Grade grade = new Grade(examName, score);

            student.recordGrade(courseCode, grade);
            System.out.println("Grade recorded successfully.");
        } catch (CourseNotFoundException | StudentNotFoundException exception) {
            System.out.println("Student with this course does not exist");
        } catch (InvalidGradeException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewStudentTranscriptMenu() {
        try {
            System.out.print("Enter Student Id :");
            String studentId = AppMenu.sc.nextLine();
            nullCheckNullOrEmpty(studentId);
            Student student = findById(studentId);

            System.out.println("Student Transcript:");
            System.out.println("Student Id " + student.getId());
            System.out.println("Student Name " + student.getName());

            for (Course c : student.getEnrolledCourses()) {
                System.out.println("Course Name : " + c.getName());
                System.out.println("Credit Hours : " + c.getCreditHours());
                double av = c.getAverageScore();
                System.out.println("Score : " + av);
                System.out.println("Letter Grade : " + c.getLetterGrade(av));
            }
            System.out.println("Total Average : " + calculateGPA(student.getEnrolledCourses()));
            System.out.println("Student Enrolled Successfully");

        } catch (StudentNotFoundException exception) {
            System.out.println("Student with this course does not exist");
        }
    }

    public static void viewTopStudentsByGPA() {
        ArrayList<Student> students = new ArrayList<>(getStudents());

        for (int x = 0; x < students.size() - 1; x++) {

            for (int y = x + 1; y < students.size(); y++)
            {

                Student student = students.get(x);
                if (students.get(y).getGBA()>students.get(x).getGBA()) {
                    students.set(x, students.get(y));
                    students.set(y, student);
                }
            }

        }

        System.out.println("Student Top Students By GPA:");


        for (Student student : students) {
            System.out.println("Student Name " + student.getName());
            System.out.println("Student GBA " + student.getGBA());
        }

    }

    public static void viewAllStudentsInCourseMenu() {

        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter Course Id:");
        String courseCode = AppMenu.sc.nextLine();
        nullCheckNullOrEmpty(courseCode);

        for (Student s : getStudents()) {

            for (Course c : s.getEnrolledCourses()) {

                if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                    students.add(s);
                    break;
                }
            }
        }

        if (students.isEmpty()) {
            throw new StudentNotFoundException("No students are enrolled in this course");
        }

        System.out.println("Students:");

        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Name: " + s.getName());
        }
    }

    public static void removeStudentMenu() {

        System.out.print("Enter Student Id:");
        String studentId = AppMenu.sc.nextLine();
        nullCheckNullOrEmpty(studentId);

        try {
            System.out.print("Are you Sure?? Yes/No ");
            System.out.print("Enter Here : ");
            String answer = AppMenu.sc.nextLine();
            if (answer.equalsIgnoreCase("Yes")) {
                removeStudent(studentId);
                System.out.println("Student removed successfully.");
            } else if (answer.equalsIgnoreCase("No")) {
                System.out.println("you will goback to the main menu");

            } else {
                System.out.println("Please Enter Yes or No" +
                        "you will go Back to the main menu");

            }

        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void stringCheck(String input) {
        if (input.matches(".*\\d.*")) {
            throw new RuntimeException("Please Enter Text not other type");
        }
    }

    static void intCheckNullOrEmpty(String input) {
        if (!input.matches("\\d+")) {
            throw new RuntimeException("Please Enter number not text");
        } else if (input.isEmpty() || input.isBlank()) {
            throw new NullPointerException("the value is null or empty");
        }
    }

    static void nullCheckNullOrEmpty(String input) {
        if (input.isEmpty() || input == " " || input.isBlank()) {
            throw new NullPointerException("the value is null or empty");
        }
    }


}