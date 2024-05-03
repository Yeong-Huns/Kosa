package me.mini.view.attendance;

import me.mini.component.panel.BottomPanel;
import me.mini.component.panel.TopPanel;
import me.mini.viewManager.MainCard;
import me.mini.viewManager.View;

import javax.swing.*;
import java.awt.*;

public class AttendanceManagementPanel extends JPanel  {
    public AttendanceManagementPanel() {
        setLayout(new BorderLayout());
        initialize();
        MainCard.getInstance().add(this, View.ATTENDANCE.toString());
    }

    private void initialize() {
        add(new TopPanel("근태관리"), BorderLayout.NORTH);
        add(new AttendenceAndManagementDetailPanel(),BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }

}
