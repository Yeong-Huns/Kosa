package main.java.kosa.myapp.ui.components.placeholder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * packageName    : main.java.kosa.myapp.ui.components.placeholder.password
 * fileName       : PwdPlaceHolder
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class PwdPlaceHolder extends JPasswordField {
    public PwdPlaceHolder(String text) {
        super(text);
        setPreferredSize(new Dimension(562, 55));
        initialize();
    }//pwdField.setBounds(10, 375, 562, 55)
    private void initialize() {
        setForeground(Color.GRAY);
        setFont(new Font("D2Coding", Font.PLAIN, 18));
        setEchoChar((char) 0);  // 초기에 텍스트 보이게
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
    public PwdPlaceHolder setYPosition(int yPosition){
        Dimension size = getPreferredSize();
        setBounds(10, yPosition, size.width, size.height);
        return this;
    }
}
