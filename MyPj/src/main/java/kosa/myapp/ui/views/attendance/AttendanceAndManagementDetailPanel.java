package main.java.kosa.myapp.ui.views.attendance;

import javax.swing.JPanel;

public class AttendanceAndManagementDetailPanel extends JPanel {
    public AttendanceAndManagementDetailPanel() {
        setBounds(0, 80, 586, 250);
        setLayout(null);
        initialize();

    }
    private void initialize() {
        add(new AnnulImgBtn());
        add(new AttendanceImgBtn());
    }

}
