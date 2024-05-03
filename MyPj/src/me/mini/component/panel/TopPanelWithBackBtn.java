package me.mini.component.panel;

import me.mini.component.button.BackButton;
import me.mini.component.label.TitleLabel;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.component.panel
 * fileName       : TopPanelWithBackBtn
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class TopPanelWithBackBtn extends TopPanel{
    public TopPanelWithBackBtn(String str) {
        super();
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        setBackground(Color.LIGHT_GRAY);
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
