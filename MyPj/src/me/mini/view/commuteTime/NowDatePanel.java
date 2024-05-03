package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class NowDatePanel extends JPanel {
    public NowDatePanel() {
        setBounds(20, 110, 543, 60);
        setBackground(new Color(105, 105, 105));
        setLayout(null);
        initalize();
    }

    private void initalize() {
        add(new NowDateLabel());
    }

}
