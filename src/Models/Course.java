package Models;

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
        if (grade.getScore() == 0 || grade.getExamName().equalsIgnoreCase(" ") || grade.getExamName() == null) {
            throw new NullPointerException("grade == null");
        }


        String examName = grade.getExamName();
        if (examName.equalsIgnoreCase(" ") || examName.isEmpty()) {
            throw new NullPointerException("examName == null");
        }

        if (grade.getScore() != 0) grades.add(grade);
        else throw new NullPointerException("grade == null");
    }

    public double getAverageScore() {

        if (grades.isEmpty()) {
            return 0.0;
        }

        double total = 0;

        for (Grade grade : grades) {
            total += grade.getScore();

        }

        return total / grades.size();
    }

    @Override
    public String toString() {
        return "Models.Course Code: " + courseCode + ", Name: " + name + ", CreditHours: " + creditHours;
    }

    public String getLetterGrade(double averageGrade) {

        if (averageGrade >= 100)
            return "A+";
        else if (averageGrade >= 90)
            return "A";
        else if (averageGrade >= 80)
            return "B";
        else if (averageGrade >= 70)
            return "C";
        else if (averageGrade >= 60)
            return "D";
        else if (averageGrade >= 50)
            return "E";
        else
            return "F";

    }
}
