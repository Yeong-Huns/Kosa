package me.mini.view.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * packageName    : me.mini
 * fileName       : PlaceHolderPWD
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class PwdPlaceHolder extends JPasswordField{
    public PwdPlaceHolder(String text) {
        super(text);
        initialize();
    }

    private void initialize() {
        setForeground(Color.GRAY);
        setFont(new Font("D2Coding", Font.PLAIN, 18));
        setEchoChar((char) 0);  // 초기에 텍스트 보이게
        setBounds(12, 395, 562, 55);
        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                String passText = String.valueOf(getPassword());
                if (passText.equals("비밀번호를 입력하세요")) {
                    setText("");
                    setForeground(Color.BLACK);
                    setEchoChar('●');
                }
            }

            public void focusLost(FocusEvent e) {
                String passText = String.valueOf(getPassword());
                if (passText.isEmpty()) {
                    setForeground(Color.GRAY);
                    setEchoChar((char) 0);
                    setText("비밀번호를 입력하세요");
                }
            }
        });
    }
}
