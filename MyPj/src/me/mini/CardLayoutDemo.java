package me.mini;

import me.mini.view.login.LoginPanel;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini
 * fileName       : CardLayoutDemo
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class CardLayoutDemo extends JPanel {
    JPanel cards = new JPanel(new CardLayout());
    public CardLayoutDemo() {
        LoginPanel loginPanel = new LoginPanel();
        cards.add(loginPanel, "login");
        add(cards);

        CardLayout cardLayout = (CardLayout) cards.getLayout();

        loginPanel.getLoginBtn().addActionListener(e -> {
            cardLayout.next(cards);
        });
    }
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Image Processing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayoutDemo cardDemo = new CardLayoutDemo();
        cardDemo.setOpaque(true);
        frame.setContentPane(cardDemo);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CardLayout window = new CardLayout();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
