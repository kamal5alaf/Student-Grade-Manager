package Models;

import GradeCalculator.GradeCalculator;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private static int codeCount = 0;
    private String courseCode;
    private String name;
    private int creditHours;
    private ArrayList<Grade> grades = new ArrayList<>();

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public Course(String name, int creditHours) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        if (creditHours <= 0) {
            throw new IllegalArgumentException("Credit hours must be greater than zero.");
        }

        codeCount++;

        this.courseCode = String.valueOf(codeCount);
        this.name = name;
        this.creditHours = creditHours;
    }
    public Course(String name, int creditHours, String courseCode) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        if (creditHours <= 0) {
            throw new IllegalArgumentException("Credit hours must be greater than zero.");
        }

        codeCount++;

        this.courseCode = String.valueOf(codeCount);
        this.name = name;
        this.creditHours = creditHours;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void addGrade(Grade grade) {
        String examName = grade.getExamName();
        if (examName.equalsIgnoreCase(" ") || examName.isBlank()) {
            throw new NullPointerException("examName == null");
        }
        grades.add(grade);
    }

    public double getAverageScore() {
        return GradeCalculator.average(grades);
    }

    @Override
    public String toString() {
        return "Models.Course Code: " + courseCode + ", Name: " + name + ", CreditHours: " + creditHours;
    }

    public String getLetterGrade(double averageGrade) {

        return GradeCalculator.letterGrade(averageGrade);

    }

}
