package me.mini.view.attendance;

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
