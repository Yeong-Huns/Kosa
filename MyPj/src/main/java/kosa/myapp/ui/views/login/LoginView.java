package main.java.kosa.myapp.ui.views.login;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.controller.member.MemberController;
import main.java.kosa.myapp.dto.member.request.LoginRequest;
import main.java.kosa.myapp.entity.member.Member;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.panels.TopPanel;
import main.java.kosa.myapp.ui.components.placeholder.PlaceHolder;
import main.java.kosa.myapp.ui.components.placeholder.PwdPlaceHolder;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.frames.MainLayOut;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;
import java.util.OptionalInt;

/**
 * packageName    : main.java.kosa.myapp.ui.views.login
 * fileName       : LoginPanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class LoginView extends JPanel {

    public LoginView() {
        setBounds(0, 0, 586, 863);
        setLayout(null);
        initialize();
        MainCard.getInstance().add(this, View.LOGIN);
    }
    private void initialize() {
        PlaceHolder idField = new PlaceHolder("아이디를 입력하세요").setYPosition(288);
        PwdPlaceHolder pwdPlaceHolder = new PwdPlaceHolder("비밀번호를 입력하세요").setYPosition(395);
        CommonButton loginBtn = new CommonButton("로그인", ButtonType.X_LARGE).setPosition(161,495);
        loginBtn.addActionListener(e->{
            MemberController.getInstance().login(new LoginRequest(idField.getText(), pwdPlaceHolder.getText()));
            MainLayOut.getInstance().show(MainCard.getInstance(), View.COMMUTE);
        });
        add(idField);
        add(pwdPlaceHolder);
        add(loginBtn);
        CommonButton SignUpPageBtn = new CommonButton("회원가입", ButtonType.SMALL).changeViewTo(View.SIGNUP).setPosition(460, 455);
        SignUpPageBtn.setButtonAppearance(false);
        add(SignUpPageBtn);
        add(new TopPanel("로그인").setAbsoluteLayout());
    }
}
