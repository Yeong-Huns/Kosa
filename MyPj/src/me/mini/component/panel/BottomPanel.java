package me.mini.component.panel;

import me.mini.component.button.M_Button;
import me.mini.view.main.MainLayOut;
import me.mini.view.main.MainCard;

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
        M_Button attendanceManageMent = new M_Button("근태관리");
        add(attendanceManageMent);
        attendanceManageMent.addActionListener(e -> MainLayOut.getInstance().show(MainCard.getInstance(),"attendance"));
        for (int i = 1; i <= 3; i++) {
            M_Button button = new M_Button("버튼 " + i);
            add(button);
            button.addActionListener(e -> MainLayOut.getInstance().show(MainCard.getInstance(),"Login"));
        }
    }
}
