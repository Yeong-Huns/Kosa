package me.mini.view.attendanceDetail;

import me.mini.component.button.BackButton;
import me.mini.component.label.TitleLabel;
import me.mini.component.panel.TopPanel;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.commuteLIst
 * fileName       : Header
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class Header extends TopPanel {
    public Header(String str) {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(new BackButton(), c);
        add(buttonPanel, BorderLayout.WEST);
        add(new TitleLabel(" "+str), BorderLayout.CENTER);
    }
}
