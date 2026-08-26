package Models;

import CustomException.CourseNotFoundException;
import CustomException.DuplicateIdException;
import GradeCalculator.GradeCalculator;

import java.util.ArrayList;

public class Student {


    private String name;
    private String id;
    private static final String emailDomain = "@gmail.com";

    public String getEmail() {
        return email;
    }

    private String email;
    private static int idCount = 0;
    private ArrayList<Course> enrolledCourses = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }


    public Student(String name, String email) {
        idCount++;
        id = String.valueOf(idCount);
        this.name = name;
        this.email = email + emailDomain;
    }

    public void enroll(Course c) throws CourseNotFoundException, DuplicateIdException {
        if (c == null) throw new NullPointerException();

        for (Course c1 : enrolledCourses) {
            if (c1.getCourseCode().equalsIgnoreCase(c.getCourseCode())) {
                throw new DuplicateIdException("Models.Course already enrolled");
            }
        }
        enrolledCourses.add(c);
    }

    public void recordGrade(String courseCode_, Grade g) throws CourseNotFoundException {
        for (Course c : enrolledCourses) {
            String code = c.getCourseCode();
            if (code.equalsIgnoreCase(courseCode_)) c.addGrade(g);
            return;
        }
        throw new CourseNotFoundException("Models.Course code not found");
    }

    public double getGBA() {
        double xx = GradeCalculator.calculateGPA(enrolledCourses);

        return xx;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) {
            return false;
        }

        Student s = (Student) obj;
        return s.id == this.id;
    }

    @Override
    public String toString() {
        return "Models.Student [name=" + name + ", id=" + id + "]";
    }

}
