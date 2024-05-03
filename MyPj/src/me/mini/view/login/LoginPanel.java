package me.mini.view.login;

import me.mini.component.panel.TopPanel;
import me.mini.component.placeholder.PlaceHolder;
import me.mini.component.placeholder.PwdPlaceHolder;
import me.mini.viewManager.MainCard;
import me.mini.viewManager.View;

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
        initialize();
        MainCard.getInstance().add(this, View.LOGIN.toString());
    }
    private void initialize() {
        PlaceHolder idField = new PlaceHolder("아이디를 입력하세요");
        idField.setBounds(12, 288, 562, 55);
        add(idField);

        PwdPlaceHolder pwdField = new PwdPlaceHolder("비밀번호를 입력하세요");
        pwdField.setBounds(12, 395, 562, 55);
        add(pwdField);

        add(new SignInBtn("로그인"));
        add(new SingUpPageBtn());
        TopPanel loginHeader = new TopPanel("로그인");
        loginHeader.setBounds(10, 10, 580, 100);
        add(loginHeader);
        //LoginBtn loginBtn = new LoginBtn("로그인");
        //loginPanel.add(loginBtn);
    }
}
