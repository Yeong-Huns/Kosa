package main.java.kosa.myapp.ui.views.attendance;

import main.java.kosa.myapp.config.Image;
import main.java.kosa.myapp.controller.UIController;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;

public class AttendanceAndManagementDetailPanel extends JPanel {
    public AttendanceAndManagementDetailPanel() {
        setBounds(0, 80, 586, 250);
        setLayout(null);
        initialize();

    }

    private void initialize() {
        CommonButton annualLeavesBtn = new CommonButton(ButtonType.IMAGE_BUTTON)
                .setImage(Image.ANNUAL)
                .setPosition(0, 125);
        annualLeavesBtn.addActionListener(i->{
            UIController.getInstance().getAnnualLeavesView().getAnnualLeavesDetail().initUIComponents();
        });
        annualLeavesBtn.changeViewTo(View.ANNUAL_LEAVES);
        add(annualLeavesBtn);
        CommonButton myAttendanceBtn = new CommonButton(ButtonType.IMAGE_BUTTON)
                .setImage(Image.ATTENDANCE)
                .setPosition(0, 0);
        myAttendanceBtn.addActionListener(i->{
            UIController.getInstance().getAttendanceDetailView().getAttendanceDetail().initUIComponents();
        });
        myAttendanceBtn.changeViewTo(View.MY_ATTENDANCE);
        add(myAttendanceBtn);
    }

}