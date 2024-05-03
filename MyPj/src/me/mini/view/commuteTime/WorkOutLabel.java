package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class WorkOutLabel extends JLabel {
    public WorkOutLabel(String s) {
        super(s);
        initalize();
    }

    private void initalize() {
        setForeground(new Color(255, 255, 255));
        setFont(new Font("D2coding", Font.PLAIN, 21));
        setBounds(28, 41, 100, 20);
    }
}
