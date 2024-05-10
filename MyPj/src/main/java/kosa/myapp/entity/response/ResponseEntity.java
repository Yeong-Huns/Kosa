package main.java.kosa.myapp.entity.response;

import lombok.Getter;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
@Getter
public class ResponseEntity<T> {
    private T data;
    private int errorCode;
    private String errorMessage;

    public ResponseEntity(T data, int errorCode, String errorMessage) {
        this.data = data;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ResponseEntity(int errorCode, String errorMessage) {
        this.data = null;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    private boolean isSuccess() {
        return errorCode == 0;
    }

    public T executeIfSuccessOrElseThrow(Runnable onSuccess, Runnable onFailure) {
        if (isSuccess()) {
            onSuccess.run();
            return data;
        } else {
            onFailure.run();
            throw new IllegalArgumentException("{ 오류코드 : " + getErrorCode() + " , 오류 메세지 : " + getErrorMessage() + "}");
        }
    }

    public T orElseThrow(Runnable action){
        if(!isSuccess()){
            action.run();
            throw new IllegalArgumentException("{ 오류코드 : " + getErrorCode() + " , 오류 메세지 : " + getErrorMessage() + "}");
        }
        return data;
    }

    public T getOrThrow(){
        if(isSuccess()){return data;}
        throw new IllegalArgumentException("{ 오류코드 : " + getErrorCode() + " , 오류 메세지 : " + getErrorMessage() + "}");
    }

    public T ifPresent(Runnable action){
        if(isSuccess()){
            action.run();
            return data;
        }
        throw new IllegalArgumentException("{ 오류코드 : " + getErrorCode() + " , 오류 메세지 : " + getErrorMessage() + "}");
    }

    public ResponseEntity<T> response(int errorCode, String errorMessage) {
        return new ResponseEntity<>(null, errorCode, errorMessage);
    }

    public ResponseEntity<T> response(T data, int errorCode, String errorMessage) {
        return new ResponseEntity<>(data, errorCode, errorMessage);
    }

    public ResponseEntity<T> exception(int errorCode ,String errorMessage) {
        return new ResponseEntity<>(null, errorCode, errorMessage);
    }

    public ResponseEntity<T> body(T data) {
        return new ResponseEntity<T>(data, this.errorCode, this.errorMessage);
    }
}

