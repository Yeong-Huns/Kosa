package main.java.kosa.myapp.ui.frames;

import main.java.kosa.myapp.ui.views.View;

import java.awt.*;

/**
 * packageName    : me.mini.view
 * fileName       : MyLayOut
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class MainLayOut extends CardLayout {
    private static MainLayOut instance ;
    private MainLayOut(){}
    public static MainLayOut getInstance() {
        if (instance == null) {
            instance = new MainLayOut();
        }
        return instance;
    }
    //show 메서드 오버로드
    public void show(Container parent, View cardName){
        show(parent, cardName.toString());
    }
}