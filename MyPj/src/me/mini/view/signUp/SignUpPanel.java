package me.mini.view.signUp;

import me.mini.component.panel.TopPanelWithBackBtn;
import me.mini.component.placeholder.PwdPlaceHolder;
import me.mini.viewManager.MainCard;
import me.mini.viewManager.View;

import javax.swing.*;

/**
 * packageName    : me.mini.view.signUp
 * fileName       : InputPanel
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class SignUpPanel extends JPanel {
    public SignUpPanel() {
        setBounds(0, 0, 586, 863);
        setLayout(null);
        initialize();
        MainCard.getInstance().add(this, View.SIGNUP.toString());// "Login"
    }
    private void initialize() {
        PwdPlaceHolder pwdField = new PwdPlaceHolder("비밀번호를 입력하세요");
        pwdField.setBounds(10, 375, 562, 55);
        add(pwdField);

        add(new TextPlaceHolder("아이디를 입력하세요",288));
        add(new TextPlaceHolder("휴대폰 번호를 입력하세요",458));
        add(new SignUpBtn());

        TopPanelWithBackBtn signUpHeader = new TopPanelWithBackBtn("회원가입");
        signUpHeader.setBounds(10, 10, 580, 100);
        add(signUpHeader);
    }
}
