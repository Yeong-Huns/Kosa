package me.mini.viewManager;

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
    private static MainFrame instance;
    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            return instance;
        }
        return instance;
    }
    private MainFrame() {
        super("타임 인 아웃 V2");
        initialize();
    }
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 900);
        setLayout(new BorderLayout());
    }



}
