package me.mini.view.main;

import javax.swing.*;

/**
 * packageName    : me.mini.view
 * fileName       : MainPanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class MainCard extends JPanel {
    private static MainCard instance;
    private MainCard() {
        super(MainLayOut.getInstance());
    }
    public static MainCard getInstance() {
        if (instance == null) {
            instance = new MainCard();
        }
        return instance;
    }

}
