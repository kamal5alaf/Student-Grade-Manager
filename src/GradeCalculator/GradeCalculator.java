package GradeCalculator;

import Models.Course;
import Models.Grade;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;


public class GradeCalculator {



    public static double average(ArrayList<Grade> grades) {
        if (grades.isEmpty()) return 0;
        double total = 0;

        for (int x = 0; x < grades.size(); x++) {
            total += grades.get(x).getScore();
        }
        return total / grades.size();


    }

    public static String letterGrade(double Score) {
        if (Score < 0 || Score > 100) {
            return "The input value must be between 0 and 100";
        }

        int grandTotal = (int) Score / 10;

        return switch (grandTotal) {
            case 9, 10 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };

    }

    public static double calculateGPA(ArrayList<Course> enrolledCourses) {
        if (enrolledCourses == null || enrolledCourses.isEmpty()) throw new NullPointerException("No enrolled courses found");

        double totalGradePoints = 0;
        double totalCreditHours = 0;

        for (Course c : enrolledCourses) {
            String courseCode = c.getLetterGrade(c.getAverageScore());

            double ResultGPA = switch (courseCode) {
                case "A" -> 4.0;
                case "B" -> 3.0;
                case "C" -> 2.0;
                case "D" -> 1.0;
                default -> 0.0;
            };

            totalCreditHours += c.getCreditHours();
            totalGradePoints += ResultGPA * c.getCreditHours();
        }

        return totalCreditHours == 0 ? 0.0 : totalGradePoints / totalCreditHours;
    }
}

