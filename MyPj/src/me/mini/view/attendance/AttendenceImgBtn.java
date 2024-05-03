package me.mini.view.attendance;

import me.mini.viewManager.MainCard;
import me.mini.viewManager.MainLayOut;

import javax.swing.*;

public class AttendenceImgBtn extends JButton {
    public AttendenceImgBtn() {
        initialize();
    }

    private void initialize() {
        String attendenceImg = "img/attendance.jpg";
        ImageIcon imageIcon = new ImageIcon(attendenceImg);
        setIcon(new ImageIcon(attendenceImg));
        setBounds(0, 0, 586, 125);
        addActionListener(e -> MainLayOut.getInstance().show(MainCard.getInstance(), "Records"));
    }
}
