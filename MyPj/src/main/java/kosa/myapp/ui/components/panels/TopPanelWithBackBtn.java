package main.java.kosa.myapp.ui.components.panels;

import main.java.kosa.myapp.ui.views.View;
import main.java.kosa.myapp.ui.components.button.BackButton;
import main.java.kosa.myapp.ui.components.label.BoldLabel;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.components.panel
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
    public TopPanelWithBackBtn(String titleName) {
        super();
        initialize(titleName);
    }
    public TopPanelWithBackBtn(String titleName, View cardName) {
        super();
        setPreferredSize(new Dimension(580, 100));
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        setBackground(Color.LIGHT_GRAY);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(new BackButton(cardName), c);
        add(buttonPanel, BorderLayout.WEST);
        add(new BoldLabel(" "+titleName,24), BorderLayout.CENTER);
    }

    public void initialize(String titleName){
        setPreferredSize(new Dimension(580, 100));
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
        add(new BoldLabel(" "+titleName,24), BorderLayout.CENTER);
    }

    public TopPanelWithBackBtn setAbsoluteLayout(){
        Dimension size = getPreferredSize();
        setBounds(10,10, size.width, size.height);
        return this;
    }
}
