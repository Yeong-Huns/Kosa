package main.java.kosa.myapp.ui.views.signUp;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.controller.member.MemberController;
import main.java.kosa.myapp.entity.member.Member;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.components.panels.TopPanelWithBackBtn;
import main.java.kosa.myapp.ui.components.placeholder.PlaceHolder;
import main.java.kosa.myapp.ui.components.placeholder.PwdPlaceHolder;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.frames.MainLayOut;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;
import java.util.OptionalInt;

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
            Member newMember = new Member();
            newMember.setId(idField.getText());
            newMember.setPassword(pwdField.getText());
            newMember.setName(nameField.getText());
            newMember.setPhoneNumber(numberField.getText());

            System.out.println(idField.getText());
            System.out.println(pwdField.getText());
            System.out.println(nameField.getText());
            System.out.println(numberField.getText());

            showDialog(newMember);
        });
        add(submit);
        add(new TopPanelWithBackBtn("회원가입").setAbsoluteLayout());
    }
    private void showDialog(Member member){
        MemberController.getInstance().insertMember(member);
        MainLayOut.getInstance().show(MainCard.getInstance(), View.COMMUTE);
    }
}
