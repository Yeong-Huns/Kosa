package me.mini.view.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * packageName    : me.mini.view.login
 * fileName       : LoginBtn
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class LoginBtn extends JButton {
    public LoginBtn(String text) {
        super(text);
        initialize();
    }
    private void initialize() {
        setFont(new Font("D2Coding", Font.PLAIN, 18));
        setBounds(161, 495, 250, 55);
    }

}
