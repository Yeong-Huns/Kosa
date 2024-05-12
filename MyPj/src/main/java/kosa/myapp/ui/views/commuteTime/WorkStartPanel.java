package main.java.kosa.myapp.ui.views.commuteTime;

import main.java.kosa.myapp.ui.components.label.PlainLabel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class WorkStartPanel extends JPanel {
    public WorkStartPanel(LocalTime startOfWork) {
        setBounds(20, 190, 543, 100);
        setBackground(new Color(157, 157, 157));
        setLayout(null);
        initalize(startOfWork);
    }

    private void initalize(LocalTime startOfWork) {
        PlainLabel workStartLabel = new PlainLabel("출근시간",21);
        workStartLabel.setForeground(new Color(255, 255, 255));
        workStartLabel.setBounds(28, 41, 100, 20);
        add(workStartLabel);

        PlainLabel workStartTimeLabel = new PlainLabel(startOfWork.toString(),21);
        workStartTimeLabel.setForeground(new Color(255, 255, 255));
        workStartTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        workStartTimeLabel.setBounds(367, 41, 150, 20);
        add(workStartTimeLabel);
    }
}
