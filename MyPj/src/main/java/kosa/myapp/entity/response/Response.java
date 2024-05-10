package main.java.kosa.myapp.entity.response;

/**
 * packageName    : main.java.kosa.myapp.dto
 * fileName       : Response
 * author         : Yeong-Huns
 * date           : 2024-05-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-05        Yeong-Huns       최초 생성
 */
public class Response<T> {
    private final T data;
    private final boolean success;
    private final int statusCode;
    private final String errorMessage;

    public Response(T data, boolean success, int statusCode, String errorMessage) {
        this.data = data;
        this.success = success;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
/*
    public static <T> Response<T> success(T data) {
        return new Response<>(data, true, null);
    }

    public static <T> Response<T> failure(String errorMessage) {
        return new Response<>(null, false, errorMessage);
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }*/
}

