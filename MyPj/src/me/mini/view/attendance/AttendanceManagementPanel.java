package me.mini.view.attendance;

import me.mini.component.panel.BottomPanel;
import me.mini.component.panel.TopPanel;
import me.mini.view.main.MainCard;

import javax.swing.*;
import java.awt.*;

public class AttendanceManagementPanel extends JPanel  {
    public AttendanceManagementPanel() {

        setLayout(new BorderLayout());
        initialize();
        MainCard.getInstance().add(this, "attendance");
    }

    private void initialize() {
        add(new TopPanel(), BorderLayout.NORTH);
        add(new AttendenceAndManagementDetailPanel(),BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }

}
