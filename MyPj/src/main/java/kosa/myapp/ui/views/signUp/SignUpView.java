package main.java.kosa.myapp.ui.views.signUp;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.dto.member.Member;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.member.MemberRepository;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.panels.TopPanelWithBackBtn;
import main.java.kosa.myapp.ui.components.placeholder.PlaceHolder;
import main.java.kosa.myapp.ui.components.placeholder.PwdPlaceHolder;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.controller.UIController;
import main.java.kosa.myapp.ui.frames.MainLayOut;
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
    public void initialize() {
        removeAll();
        PlaceHolder idField = new PlaceHolder("아이디를 입력하세요").setYPosition(208);
        PwdPlaceHolder pwdField = new PwdPlaceHolder("비밀번호를 입력하세요").setYPosition(295);
        PlaceHolder nameField = new PlaceHolder("이름을 입력하세요.").setYPosition(378);
        PlaceHolder numberField = new PlaceHolder("휴대폰 번호를 입력하세요").setYPosition(465);
        add(idField);
        add(pwdField);
        add(nameField);
        add(numberField);

        CommonButton submit = new CommonButton("회원가입", ButtonType.X_LARGE).setPosition(161, 552);
        submit.addActionListener(e-> {
            System.out.println(idField.getText());
            System.out.println(pwdField.getText());
            System.out.println(nameField.getText());
            System.out.println(numberField.getText());

            Member newMember =
                    Member.builder()
                            .id(idField.getText())
                            .password(pwdField.getText())
                            .name(nameField.getText())
                            .phoneNumber(numberField.getText())
                            .build();
            redirectCommuteTime(newMember);
        });
        add(submit);
        add(new TopPanelWithBackBtn("회원가입").setAbsoluteLayout());
        revalidate(); // 레이아웃을 다시 계산하여 새로 고침
        repaint();
    }
    private void redirectCommuteTime(Member member){
        ResponseEntity<Void> response = MemberRepository.getInstance().insertMember(member);
        if(response.isSuccess()){
            response.showDialogs();
            MemberRepository.getInstance().login(member).runIfSuccess(Main::setSessionKey);
            UIController.getInstance().getCommuteTimeView().innerPanelUpdate();
            MainLayOut.getInstance().show(MainCard.getInstance(), View.COMMUTE);
            UIController.getInstance().getApprovalView().getApprovalDetail().initUIComponents();
        }
    }
}
