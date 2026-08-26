package CustomException;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String massage)
    {
        super(massage);
    }
}
