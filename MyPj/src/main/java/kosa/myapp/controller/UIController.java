package main.java.kosa.myapp.controller;

import lombok.Getter;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.frames.MainLayOut;
import main.java.kosa.myapp.ui.views.View;
import main.java.kosa.myapp.ui.views.annualLeaves.AnnualLeavesView;
import main.java.kosa.myapp.ui.views.approval.ApprovalView;
import main.java.kosa.myapp.ui.views.attendance.AttendanceManagementView;
import main.java.kosa.myapp.ui.views.attendanceDetail.AttendanceDetailView;
import main.java.kosa.myapp.ui.views.commuteTime.CommuteTimeView;
import main.java.kosa.myapp.ui.views.login.LoginView;
import main.java.kosa.myapp.ui.views.signUp.SignUpView;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view
 * fileName       : MainFrame
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
@Getter
public class UIController extends JFrame {
    private static UIController instance;
    private final MainLayOut cardLayout;
    private final MainCard cards;
    private LoginView loginView;
    private SignUpView signUpView;
    private CommuteTimeView commuteTimeView;
    private AttendanceManagementView attendanceManagementView;
    private AttendanceDetailView attendanceDetailView;
    private AnnualLeavesView annualLeavesView;
    private ApprovalView approvalView;
    private UIController() {
        super("타임 인 아웃 V2");
        cardLayout = MainLayOut.getInstance();
        cards = MainCard.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 900);
        setLayout(new BorderLayout());
        initializeUI();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static UIController getInstance() {
        if (instance == null) {
            instance = new UIController();
        }
        return instance;
    }



    private void initializeUI() {
        loginView = new LoginView();
        signUpView = new SignUpView();
        commuteTimeView = new CommuteTimeView();
        attendanceManagementView = new AttendanceManagementView();
        attendanceDetailView = new AttendanceDetailView("근태 현황");
        annualLeavesView = new AnnualLeavesView();
        approvalView = new ApprovalView();
        add(cards);
        cardLayout.show(cards, View.LOGIN);//처음에 로그인 패널을 보여줍니다.
    }
}
