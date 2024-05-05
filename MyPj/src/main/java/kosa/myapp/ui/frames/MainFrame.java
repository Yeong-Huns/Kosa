package main.java.kosa.myapp.ui.frames;

import main.java.kosa.myapp.ui.views.View;
import main.java.kosa.myapp.ui.views.AnnualLeaves.AnnualLeavesView;
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
public class MainFrame extends JFrame {
    private final MainLayOut cardLayout;
    private final MainCard cards;

    public MainFrame() {
        super("타임 인 아웃 V2");
        cardLayout = MainLayOut.getInstance();
        cards = MainCard.getInstance();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 900);
        setLayout(new BorderLayout());
        initializeUI();
        setVisible(true);
    }

    private void initializeUI() {
        LoginView loginView = new LoginView();
        SignUpView signUpView = new SignUpView();
        CommuteTimeView commuteTimeView = new CommuteTimeView();
        AttendanceManagementView attendanceManagementView = new AttendanceManagementView();
        AttendanceDetailView attendanceDetailView = new AttendanceDetailView("근태 현황");
        AnnualLeavesView annualLeavesView = new AnnualLeavesView();

        add(cards);
        cardLayout.show(cards, View.LOGIN);//처음에 로그인 패널을 보여줍니다.
    }
}
