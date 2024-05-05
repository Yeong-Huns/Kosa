package main.java.kosa.myapp.ui.views.login;

import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.panels.TopPanel;
import main.java.kosa.myapp.ui.components.placeholder.PlaceHolder;
import main.java.kosa.myapp.ui.components.placeholder.PwdPlaceHolder;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;

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
        add(new PlaceHolder("아이디를 입력하세요").setYPosition(288));
        add(new PwdPlaceHolder("비밀번호를 입력하세요").setYPosition(395));
        add(new CommonButton("로그인", ButtonType.X_LARGE).setPosition(161,495).changeViewTo(View.COMMUTE));
        CommonButton SignUpPageBtn = new CommonButton("회원가입", ButtonType.SMALL).changeViewTo(View.SIGNUP).setPosition(460, 455);
        SignUpPageBtn.setButtonAppearance(false);
        add(SignUpPageBtn);
        add(new TopPanel("로그인").setAbsoluteLayout());
    }
}
