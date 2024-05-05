package main.java.kosa.myapp.ui.views.signUp;

import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.panels.TopPanelWithBackBtn;
import main.java.kosa.myapp.ui.components.placeholder.PlaceHolder;
import main.java.kosa.myapp.ui.components.placeholder.PwdPlaceHolder;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;

/**
 * packageName    : main.java.kosa.myapp.ui.views.signUp
 * fileName       : InputPanel
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class SignUpView extends JPanel {
    public SignUpView() {
        setBounds(0, 0, 586, 863);
        setLayout(null);
        initialize();
        MainCard.getInstance().add(this, View.SIGNUP); // 카드 레이아웃
    }
    private void initialize() {
        add(new PwdPlaceHolder("비밀번호를 입력하세요").setYPosition(375));
        add(new PlaceHolder("아이디를 입력하세요").setYPosition(288));
        add(new PlaceHolder("휴대폰 번호를 입력하세요").setYPosition(458));

        add(new CommonButton("회원가입", ButtonType.X_LARGE).changeViewTo(View.COMMUTE).setPosition(161, 555));
        add(new TopPanelWithBackBtn("회원가입").setAbsoluteLayout());
    }
}
