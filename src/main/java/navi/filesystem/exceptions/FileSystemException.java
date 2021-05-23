package navi.filesystem.exceptions;

public class FileSystemException extends RuntimeException {
    private ErrorCodes errorCode;

    public FileSystemException(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }

    public FileSystemException() {
    }

    @Override
    public String toString() {
        return errorCode.getErrorCode() + " : " + errorCode.getErrorMessage();
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }
}
