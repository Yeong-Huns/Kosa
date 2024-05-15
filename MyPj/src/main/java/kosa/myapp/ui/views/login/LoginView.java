package main.java.kosa.myapp.ui.views.login;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.dto.member.Member;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.member.MemberRepository;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.panels.BottomPanel;
import main.java.kosa.myapp.ui.components.panels.TopPanel;
import main.java.kosa.myapp.ui.components.placeholder.PlaceHolder;
import main.java.kosa.myapp.ui.components.placeholder.PwdPlaceHolder;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.controller.UIController;
import main.java.kosa.myapp.ui.frames.MainLayOut;
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
    private PlaceHolder idField;
    private PwdPlaceHolder pwdPlaceHolder;

    public LoginView() {
        setBounds(0, 0, 586, 863);
        setLayout(null);
        initialize();
        MainCard.getInstance().add(this, View.LOGIN);
    }
    public void initialize() {
        removeAll(); // 기존 컴포넌트를 모두 제거

        idField = new PlaceHolder("아이디를 입력하세요").setYPosition(288);
        pwdPlaceHolder = new PwdPlaceHolder("비밀번호를 입력하세요").setYPosition(395);

        CommonButton loginBtn = new CommonButton("로그인", ButtonType.X_LARGE).setPosition(161,495);
        loginBtn.addActionListener(e-> redirectCommuteTime(Member.builder().id(idField.getText()).password(pwdPlaceHolder.getText()).build()));
        add(idField);
        add(pwdPlaceHolder);
        add(loginBtn);
        CommonButton SignUpPageBtn = new CommonButton("회원가입", ButtonType.SMALL).changeViewTo(View.SIGNUP).setPosition(460, 455);
        SignUpPageBtn.setButtonAppearance(false);
        add(SignUpPageBtn);
        add(new TopPanel("로그인").setAbsoluteLayout());
        revalidate();
        repaint();
    }
    private void redirectCommuteTime(Member member){
        ResponseEntity<Integer> response = MemberRepository.getInstance().login(member);
        response.showDialogs();
        response.runIfSuccess(Main::setSessionKey);
        UIController.getInstance().getCommuteTimeView().innerPanelUpdate();
        response.ifPresent(()->MainLayOut.getInstance().show(MainCard.getInstance(), View.COMMUTE));
        response.ifPresent(()->UIController.getInstance().getApprovalView().getApprovalDetail().initUIComponents());
    }

}
