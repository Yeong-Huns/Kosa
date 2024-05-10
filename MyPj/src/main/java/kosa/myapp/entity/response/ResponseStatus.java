package main.java.kosa.myapp.entity.response;

/**
 * packageName    : main.java.kosa.myapp.entity.response
 * fileName       : ResponseStatus
 * author         : Yeong-Huns
 * date           : 2024-05-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-05        Yeong-Huns       최초 생성
 */
public enum ResponseStatus {
    SUCCESS(true, 200, "성공"),
    ;
    private final boolean success;
    private final int statusCode;
    private final String errorMessage;

    ResponseStatus(boolean success, int statusCode, String errorMessage) {
        this.success = success;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}
