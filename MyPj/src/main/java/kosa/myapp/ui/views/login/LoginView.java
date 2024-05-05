package main.java.kosa.myapp.ui.views.login;

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

        CommonButton SignInBtn = new CommonButton("로그인", View.COMMUTE, 18);
        SignInBtn.setBounds(161, 495, 250, 60);
        add(SignInBtn);

        add(new SingUpPageBtn());
        add(new TopPanel("로그인").setAbsoluteLayout());
    }
}
