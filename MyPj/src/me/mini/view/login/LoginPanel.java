package me.mini.view.login;

import me.mini.view.main.MainCard;

import javax.swing.*;

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
        MainCard.getInstance().add(this, "Login");
        initialize();
    }
    private void initialize() {
        add(new IdField("아이디를 입력하세요"));
        add(new PasswordField("비밀번호를 입력하세요"));
        add(new SignInBtn("로그인"));
        add(new SingUpPageBtn());
        //LoginBtn loginBtn = new LoginBtn("로그인");
        //loginPanel.add(loginBtn);
    }
}
