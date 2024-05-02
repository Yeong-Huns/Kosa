package me.mini.view.common;

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
public class ButtonPanel extends JPanel {
    public ButtonPanel() {
        super(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(580, 100));
        /*
        for (int i = 1; i <= 4; i++) {
            JButton button = new JButton("버튼 " + i);
            add(button);
        }
         */
    }
}
