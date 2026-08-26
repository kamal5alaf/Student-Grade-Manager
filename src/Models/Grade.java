package Models;

public class Grade {

    private String examName;
    private double score;

    public Grade(String examName, double score) {
        this.examName = examName;
        if (score < 0 || score > 100) {
            throw new IndexOutOfBoundsException("Score must be between 0 and 100");
        }
        this.score = score;
    }


    public String getExamName() {
        return examName;
    }

    public double getScore() {
        return score;
    }


    @Override
    public String toString() {
        return "Models.Grade{" +
                "examName='" + examName + '\'' +
                ", score=" + score +
                '}';
    }
}
