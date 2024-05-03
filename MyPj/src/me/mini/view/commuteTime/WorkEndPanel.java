package me.mini.view.commuteTime;

import javax.swing.*;
import java.awt.*;

public class WorkEndPanel extends JPanel {
    public WorkEndPanel() {
        setBounds(20, 310, 543, 100);
        setBackground(new Color(157, 157, 157));
        setLayout(null);
        initalize();
    }

    private void initalize() {
        JLabel workOutLabel = new JLabel("퇴근시간");
        workOutLabel.setForeground(new Color(255, 255, 255));
        workOutLabel.setFont(new Font("D2coding", Font.PLAIN, 21));
        workOutLabel.setBounds(28, 41, 100, 20);
        add(workOutLabel);

        JLabel workOutTimeLabel = new JLabel("18:00:12");
        workOutTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        workOutTimeLabel.setForeground(new Color(255,255,255));
        workOutTimeLabel.setFont(new Font("D2coding", Font.PLAIN, 21));
        workOutTimeLabel.setBounds(367, 41, 150, 20);
        add(workOutTimeLabel);
    }
}
