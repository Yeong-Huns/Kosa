package me.mini.view.common;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.common
 * fileName       : TopPanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class TopPanel extends JPanel {
    public TopPanel() {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(580, 100));
    }
    public TopPanel(TitleLabel titleLabel) {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(580, 100));
        add(titleLabel);
    }
}
