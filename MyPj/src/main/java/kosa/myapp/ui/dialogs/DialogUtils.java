package main.java.kosa.myapp.ui.dialogs;

import main.java.kosa.myapp.dto.response.ResponseEntity;

import javax.swing.*;

/**
 * packageName    : main.java.kosa.myapp.ui.dialogs
 * fileName       : DialogUtils
 * author         : Yeong-Huns
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        Yeong-Huns       최초 생성
 */
public class DialogUtils {
    public static void showDialogs(ResponseEntity<?> response) {
        if (response.isSuccess()) {
            showDialogs(response.getErrorMessage());
        } else {
            showFailureMessage(response.getErrorMessage());
        }
    }

    private static void showDialogs(String message) {
        JOptionPane.showMessageDialog(null, message, "성공", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showFailureMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "경고", JOptionPane.ERROR_MESSAGE);
        //throw new IllegalArgumentException("Failure message 출력됨");
    }
}
