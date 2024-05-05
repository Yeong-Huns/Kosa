package main.java.kosa.myapp.ui.frames;

import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;
import java.awt.*;

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
    //add 메서드 오버로드
    public void add(Component comp, View viewName){
        add(comp, viewName.toString());
    };
}
