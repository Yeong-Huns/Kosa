package main.java.kosa.myapp.ui.views.commuteTime;

import main.java.kosa.myapp.ui.components.label.PlainLabel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class WorkEndPanel extends JPanel {
    public WorkEndPanel(LocalTime endOfWork) {
        setBounds(20, 310, 543, 100);
        setBackground(new Color(157, 157, 157));
        setLayout(null);
        initalize(endOfWork);
    }

    private void initalize(LocalTime endOfWork) {
        PlainLabel workOutLabel = new PlainLabel("퇴근시간", 21);
        workOutLabel.setForeground(new Color(255, 255, 255));
        workOutLabel.setBounds(28, 41, 100, 20);
        add(workOutLabel);
        String time = endOfWork != null ? endOfWork.toString() : "--:--:--";
        PlainLabel workOutTimeLabel = new PlainLabel(time,21);
        workOutTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        workOutTimeLabel.setForeground(new Color(255,255,255));
        workOutTimeLabel.setBounds(367, 41, 150, 20);
        add(workOutTimeLabel);
    }
}
