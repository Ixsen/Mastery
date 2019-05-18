package mastery.common.exceptions;

public class UnexpectedUiIdException extends RuntimeException {

    public UnexpectedUiIdException(int unexpectedUiId) {
        super(String.format("ID for UserInterface [%d] is not registered!", unexpectedUiId));
    }
}
