package navi.filesystem.exceptions;

public enum ErrorCodes {
    INVALID_VFS_PARAMS(1001,"Invalid VFS parameters"),
    INVALID_FILE(1002,"Invalid File Name"),
    INVALID_INPUT(1003,"Invalid Inputs"),
    INVALID_BLOCK_SIZE(1005,"Invalid Block Size"),
    INVALID_BLOCK_ID(1006,"Invalid Block Id"),
    OPERATION_NOT_PERMITTED(1004,"You are not allowed to take this action"),
    FILE_EXISTS(1007,"File Already Exists"),
    NO_SPACE_LEFT(1008,"NO space left"),
    FILE_DOESNT_EXIST(1009,"File doesn't Exist"),
    ;

    private int errorCode;
    private String errorMessage;

    ErrorCodes(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


}
