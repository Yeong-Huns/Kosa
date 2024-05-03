package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class NowDatePanel extends JPanel {
    public NowDatePanel() {
        setBounds(20, 110, 543, 60);
        setBackground(new Color(157, 157, 157));
        setLayout(null);
        add(new NowDateLabel());
    }
}
