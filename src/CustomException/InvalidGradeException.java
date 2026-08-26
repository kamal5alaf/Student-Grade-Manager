package CustomException;

public class InvalidGradeException extends  RuntimeException {
    public InvalidGradeException(String massage)
    {
        super(massage);
    }
}
