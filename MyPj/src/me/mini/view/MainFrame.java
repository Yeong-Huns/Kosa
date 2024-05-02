package me.mini.view;

import javax.swing.*;

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
public class MainFrame {
    private JFrame frame;
    private static MainFrame instance;
    private static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            return instance;
        }
        return MainFrame.getInstance();
    }
    private MainFrame() {
        initialize();
    }
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }
    public void setVisible(){
        frame.setVisible(true);
    }

    public void inVisible(){
        frame.setVisible(false);
    }


}
