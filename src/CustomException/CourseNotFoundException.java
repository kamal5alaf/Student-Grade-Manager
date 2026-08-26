package CustomException;

public class CourseNotFoundException extends  RuntimeException {
    public CourseNotFoundException(String massage)
    {
        super(massage);
    }
}
