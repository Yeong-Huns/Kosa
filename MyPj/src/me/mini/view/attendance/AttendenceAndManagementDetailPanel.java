package me.mini.view.attendance;

import me.mini.view.main.MainCard;

import javax.swing.JPanel;

public class AttendenceAndManagementDetailPanel extends JPanel {
    public AttendenceAndManagementDetailPanel() {
        setBounds(0, 80, 586, 250);
        setLayout(null);
        initialize();

    }
    private void initialize() {
        add(new AnnulImgBtn());
        add(new AttendenceImgBtn());
    }

}
