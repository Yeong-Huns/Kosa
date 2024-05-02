package me.mini.view.login;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * packageName    : me.mini.view.login
 * fileName       : LoginPanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class LoginPanel extends JPanel {

    public LoginPanel() {
        setBounds(0, 0, 586, 863);
        setLayout(null);
        initialize();
    }
    private void initialize() {
        add(new IdPlaceHolder("아이디를 입력하세요"));
        add(new PwdPlaceHolder("비밀번호를 입력하세요"));
    }

    public void Visible(){
        setVisible(true);
    }
    public void Invisible(){
        setVisible(false);
    }
    public JButton getLoginBtn() {
        return getLoginBtn();
    }
}
