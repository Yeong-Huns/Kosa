package me.mini.view;

import me.mini.view.common.ButtonPanel;
import me.mini.view.common.CommonButton;
import me.mini.view.commuteLIst.CommutePanel;
import me.mini.view.login.LoginBtn;
import me.mini.view.login.LoginPanel;

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
public class WorkRecordSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cards;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WorkRecordSystem system = new WorkRecordSystem();
                    system.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public WorkRecordSystem() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("근무 기록 시스템");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 900);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel();
        LoginBtn loginBtn = new LoginBtn("로그인");
        loginBtn.addActionListener(e -> cardLayout.show(cards, "Records"));
        loginPanel.add(loginBtn);

        CommutePanel commutePanel = new CommutePanel("근태 현황");
        ButtonPanel buttonPanel = new ButtonPanel();
        for (int i = 1; i <= 4; i++) {
            CommonButton button = new CommonButton("버튼 " + i);
            buttonPanel.add(button);
            button.addActionListener(e -> cardLayout.show(cards, "Login"));
        }
        commutePanel.add(buttonPanel,  BorderLayout.SOUTH);


        cards.add(loginPanel, "Login");
        cards.add(commutePanel, "Records");

        frame.add(cards);
        cardLayout.show(cards, "Login"); // 처음에 로그인 패널을 보여줍니다.
    }

}
