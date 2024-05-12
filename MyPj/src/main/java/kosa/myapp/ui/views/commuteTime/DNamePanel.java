package main.java.kosa.myapp.ui.views.commuteTime;

import main.java.kosa.myapp.ui.components.label.PlainLabel;

import javax.swing.*;
import java.awt.*;

public class DNamePanel extends JPanel {
    public DNamePanel(String deptName){
        setBackground(new Color(157, 157, 157));
        setBounds(20, 20, 543, 70);
        setLayout(null);
        initialize(deptName);
    }

    private void initialize(String deptName) {
        PlainLabel workState = new PlainLabel("현재 근무지", 18);
        workState.setForeground(new Color(255, 255, 255));
        workState.setBounds(28, 25, 100, 20);
        add(workState);

        PlainLabel hrDept = new PlainLabel(deptName, 21);
        hrDept.setForeground(new Color(255, 255, 255));
        hrDept.setHorizontalAlignment(SwingConstants.RIGHT);
        hrDept.setBounds(377, 25, 140, 20);
        add(hrDept);
    }
}
