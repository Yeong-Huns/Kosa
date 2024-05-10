package main.java.kosa.myapp.ui.views.attendance;

import main.java.kosa.myapp.config.Image;
import main.java.kosa.myapp.ui.components.button.ButtonType;
import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.JPanel;

public class AttendanceAndManagementDetailPanel extends JPanel {
    public AttendanceAndManagementDetailPanel() {
        setBounds(0, 80, 586, 250);
        setLayout(null);
        initialize();

    }
    private void initialize() {
        add(new CommonButton(ButtonType.IMAGE_BUTTON)
                .setImage(Image.ANNUAL)
                .changeViewTo(View.ANNUAL_LEAVES)
                .setPosition(0, 125));

        add(new CommonButton(ButtonType.IMAGE_BUTTON)
                .setImage(Image.ATTENDANCE)
                .changeViewTo(View.MY_ATTENDANCE)
                .setPosition(0, 0));
    }

}
