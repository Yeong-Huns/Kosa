package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class DNameLable1 extends JLabel {
    public DNameLable1(String s) {
        super(s);
        initalize();
    }

    private void initalize() {
        setForeground(new Color(255, 255, 255));
        setFont(new Font("D2coding", Font.PLAIN, 21));
        setBounds(28, 25, 100, 20);
    }
}
