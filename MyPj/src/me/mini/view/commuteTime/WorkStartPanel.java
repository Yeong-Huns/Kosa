package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class WorkStartPanel extends JPanel {
    public WorkStartPanel() {
        setBounds(20, 190, 543, 100);
        setBackground(new Color(105, 105, 105));
        setLayout(null);
        initalize();


    }

    private void initalize() {
        add(new WorkStartLabel("출근시간"));
        add(new WorkStartTimeLabel("08:56:39"));
    }
}
