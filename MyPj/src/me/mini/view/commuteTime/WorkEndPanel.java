package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class WorkEndPanel extends JPanel {
    public WorkEndPanel() {
        setBounds(20, 310, 543, 100);
        setBackground(new Color(105, 105, 105));
        setLayout(null);
        initalize();
    }

    private void initalize() {
        add(new WorkOutLabel("퇴근시간"));
        add(new WorkOutTimeLabel("18:00:12"));
    }
}
