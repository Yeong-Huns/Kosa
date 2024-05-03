package me.mini.view.attendance;

import me.mini.view.main.MainCard;
import me.mini.view.main.MainLayOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendenceImgBtn extends JButton {
    public AttendenceImgBtn() {
        initialize();
    }

    private void initialize() {
        String attendenceImg = "src/me/mini/image/attendance.jpg";
        ImageIcon imageIcon = new ImageIcon(attendenceImg);
        setIcon(new ImageIcon(attendenceImg));
        setBounds(0, 0, 586, 125);
        addActionListener(e -> MainLayOut.getInstance().show(MainCard.getInstance(), "Records"));
    }
}
