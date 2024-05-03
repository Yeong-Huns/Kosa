package me.mini.component.panel;

import me.mini.component.button.BackButton;
import me.mini.component.label.TitleLabel;

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
        super(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(580, 100));
    }
    public TopPanel(String str){
        super(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(580, 100));
        add(new TitleLabel(" "+str), BorderLayout.WEST);
    }
}
