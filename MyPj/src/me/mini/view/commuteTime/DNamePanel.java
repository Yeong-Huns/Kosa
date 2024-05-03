package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class DNamePanel extends JPanel {
    public DNamePanel() {
        setBackground(new Color(105, 105, 105));
        setBounds(20, 20, 543, 70);
        setLayout(null);
        initalize();
    }

    private void initalize() {
        add(new DNameLable1("현재 부서"));
        add(new DNameLable2("HR부서"));
    }
}
