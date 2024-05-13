package main.java.kosa.myapp.dto.response;

import lombok.Getter;
import main.java.kosa.myapp.ui.dialogs.DialogUtils;

import javax.swing.*;
import java.util.function.Consumer;

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
        verifyErrorCode();
    }

    public ResponseEntity(int errorCode, String errorMessage) {
        this.data = null;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        verifyErrorCode();
    }
    public boolean isSuccess() {
        return errorCode == 0;
    }

    public void printLog(){
        System.out.println("{ 오류코드 : " + getErrorCode() + " , 오류 메세지 : " + getErrorMessage() + "}");
    }

    public void runIfSuccess(Consumer<T> action) {
        if (isSuccess()) {
            action.accept(data);
        }
    }

    public void showDialogs(){
        DialogUtils.showDialogs(this);
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

    public T orElse(T alternative){
        return isSuccess() ? data : alternative;
    }

    public T ifPresent(Runnable action){
        if(errorCode == 0 || errorCode == 1) {
            action.run();
            return data;
        }
        throw new IllegalArgumentException("{ 오류코드 : " + getErrorCode() + " , 오류 메세지 : " + getErrorMessage() + "}");
    }

    private void verifyErrorCode(){
        if(errorCode != 0 && errorCode != 1){
            JOptionPane.showMessageDialog(null,
                    "에러 메세지 : " + errorMessage,
                    "에러! ",
                    JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("{ 오류코드 : " + getErrorCode() + " , 오류 메세지 : " + getErrorMessage() + "}");
        }
    }
}

