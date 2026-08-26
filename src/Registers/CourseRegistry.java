package Registers;

import CustomException.CourseNotFoundException;
import CustomException.DuplicateIdException;
import Models.Course;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CourseRegistry {
    private static HashMap<String, Course> courses = new HashMap<String, Course>();


    public static void addCourse(Course course) {

        if (course == null) throw new IllegalArgumentException("Course cannot be null.");

        for (Course c : courses.values()) {
            if (c.getName().equalsIgnoreCase(course.getName()))
                throw new DuplicateIdException(" The course Already exists. ");
        }

        if (course.getName() == null || course.getName().equalsIgnoreCase(""))
            throw new NullPointerException(" The course is null ");

        courses.put(course.getCourseCode(), course);

    }

    public static Course findByCode(String courseCode) {

        if (courses == null) {
            throw new NullPointerException("courses == null");
        }
        Course course = courses.get(courseCode);
        if (course == null) {
            throw new CourseNotFoundException("Course not found: " + courseCode);
        }
        return course;
    }

    public static ArrayList<Course> getAllCourses() {
        if (courses == null) throw new NullPointerException("courses == null");
        return new ArrayList<>(courses.values());
    }
}
