package main.java.kosa.myapp.ui.components.panels;

import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.common
 * fileName       : ButtonPanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class BottomPanel extends JPanel {
    public BottomPanel() {
        super(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(580, 100));
        add(new CommonButton("근태관리", ButtonType.DEFAULT).changeViewTo(View.ATTENDANCE));
        add(new CommonButton("출퇴근체크", ButtonType.DEFAULT).changeViewTo(View.COMMUTE));
        add(new CommonButton("결재함", ButtonType.DEFAULT).changeViewTo(View.APPROVAL));
        add(new CommonButton("구현예정 ", ButtonType.DEFAULT).changeViewTo(View.LOGIN));
    }
}
