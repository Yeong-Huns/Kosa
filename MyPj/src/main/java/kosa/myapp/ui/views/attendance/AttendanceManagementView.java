package main.java.kosa.myapp.ui.views.attendance;

import main.java.kosa.myapp.ui.components.panels.BottomPanel;
import main.java.kosa.myapp.ui.components.panels.TopPanel;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;
import java.awt.*;

public class AttendanceManagementView extends JPanel  {
    public AttendanceManagementView() {
        setLayout(new BorderLayout());
        initialize();
        MainCard.getInstance().add(this, View.ATTENDANCE);
    }

    private void initialize() {
        add(new TopPanel("근태관리"), BorderLayout.NORTH);
        add(new AttendanceAndManagementDetailPanel(),BorderLayout.CENTER);
        add(new BottomPanel(), BorderLayout.SOUTH);
    }

}