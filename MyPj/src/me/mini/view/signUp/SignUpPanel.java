package me.mini.view.signUp;

import me.mini.component.button.BackButton;
import me.mini.component.label.TitleLabel;
import me.mini.component.placeholder.PwdPlaceHolder;
import me.mini.view.login.PasswordField;
import me.mini.view.main.MainCard;

import javax.swing.*;
import java.awt.*;

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
        MainCard.getInstance().add(this, "SignUp");
    }
    private void initialize() {
        PwdPlaceHolder pwdField = new PasswordField("비밀번호를 입력하세요");
        add(new TextPlaceHolder("아이디를 입력하세요",288));
        pwdField.setBounds(10, 375, 562, 55);
        add(pwdField);
        add(new TextPlaceHolder("휴대폰 번호를 입력하세요",458));
        add(new SignUpBtn());
        SignUpHeader signUpHeader = new SignUpHeader("회원가입");
        signUpHeader.setBounds(10, 10, 562, 55);
        add(signUpHeader);
        /*
        BackButton backButton = new BackButton();
        backButton.setBounds(10,10,50,50);
        add(backButton);
        TitleLabel titleLabel = new TitleLabel("회원가입");
        titleLabel.setBounds(10, 10, 120, 40);
        add(titleLabel);
        */
    }
}
