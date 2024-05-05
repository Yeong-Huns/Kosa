package main.java.kosa.myapp.ui.views.attendance;

import main.java.kosa.myapp.config.Image;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.frames.MainLayOut;
import main.java.kosa.myapp.ui.views.View;

import javax.swing.*;

public class AttendanceImgBtn extends JButton {
    public AttendanceImgBtn() {
        initialize();
    }

    private void initialize() {
        setIcon(new ImageIcon(Image.ATTENDANCE.getPath()));
        setBounds(0, 0, 586, 125);
        addActionListener(e -> MainLayOut.getInstance().show(MainCard.getInstance(), View.MY_ATTENDANCE));
    }
}
