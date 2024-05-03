package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class WorkOutTimeLabel extends JLabel {
    public WorkOutTimeLabel(String s) {
        super(s);
        initalize();
    }

    private void initalize() {
        setHorizontalAlignment(SwingConstants.RIGHT);
        setForeground(new Color(255,255,255));
        setFont(new Font("D2coding", Font.PLAIN, 21));
        setBounds(367, 41, 150, 20);
    }
}
