package me.mini.view;

import me.mini.view.attendance.AttendanceManagementPanel;
import me.mini.view.attendanceDetail.CommutePanel;
import me.mini.view.commuteTime.CommuteTimePanel;
import me.mini.view.login.LoginPanel;
import me.mini.view.main.MainFrame;
import me.mini.view.main.MainCard;
import me.mini.view.main.MainLayOut;
import me.mini.view.signUp.SignUpPanel;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.work
 * fileName       : WorkRecordSystem
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class ViewConstructor {
    private final JFrame frame;
    private final CardLayout cardLayout;
    private final JPanel cards;

    public ViewConstructor() {
        frame = MainFrame.getInstance();
        cardLayout = MainLayOut.getInstance();
        cards = MainCard.getInstance();
        initializeUI();
        frame.setVisible(true);
    }
    private void initializeUI() {
        LoginPanel loginPanel = new LoginPanel();
        SignUpPanel signUpPanel = new SignUpPanel();
        CommuteTimePanel commuteTimePanel = new CommuteTimePanel();
        AttendanceManagementPanel attendanceManagementPanel = new AttendanceManagementPanel();
        CommutePanel commutePanel = new CommutePanel("근태 현황");
        frame.add(cards);
        cardLayout.show(cards, "Login"); // 처음에 로그인 패널을 보여줍니다.
    }

    public void setCardLayout(JPanel panel, String str) {
        this.cardLayout.show(panel, str);
    }
    public JPanel getCards() {
        return this.cards;
    }
}
