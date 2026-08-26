package CustomException;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(String massage) {
        super(massage);
    }
}
