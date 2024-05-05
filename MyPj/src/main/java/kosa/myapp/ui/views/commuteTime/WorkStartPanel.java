package main.java.kosa.myapp.ui.views.commuteTime;

import javax.swing.*;
import java.awt.*;

public class WorkStartPanel extends JPanel {
    public WorkStartPanel() {
        setBounds(20, 190, 543, 100);
        setBackground(new Color(157, 157, 157));
        setLayout(null);
        initalize();
    }

    private void initalize() {
        JLabel workStartLabel = new JLabel("출근시간");
        workStartLabel.setForeground(new Color(255, 255, 255));
        workStartLabel.setFont(new Font("D2coding", Font.PLAIN, 21));
        workStartLabel.setBounds(28, 41, 100, 20);
        add(workStartLabel);

        JLabel workStartTimeLabel = new JLabel("08:56:39");
        workStartTimeLabel.setForeground(new Color(255, 255, 255));
        workStartTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        workStartTimeLabel.setFont(new Font("D2coding", Font.PLAIN, 21));
        workStartTimeLabel.setBounds(367, 41, 150, 20);
        add(workStartTimeLabel);
    }
}
