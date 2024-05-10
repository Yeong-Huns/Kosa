package main.java.kosa.myapp.ui.dialogs;

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
    public static void showSuccessMessage(String message) {
        System.out.println("Success: " + message);
        JOptionPane.showMessageDialog(null, message, "회원 등록 성공", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showFailureMessage(String message) {
        System.out.println("Failure: " + message);
        JOptionPane.showMessageDialog(null, message, "회원 등록 실패", JOptionPane.ERROR_MESSAGE);
    }
}
