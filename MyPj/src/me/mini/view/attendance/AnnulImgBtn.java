package me.mini.view.attendance;

import me.mini.view.main.MainCard;
import me.mini.view.main.MainLayOut;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnulImgBtn extends JButton {
    public AnnulImgBtn() {
        initialize();
    }

    private void initialize() {
        String annulImg = "src/me/mini/image/annual.jpg";
        ImageIcon imageIcon = new ImageIcon(annulImg);
        setIcon(new ImageIcon(annulImg));
        setBounds(0, 125, 586, 125);
        //addActionListener(e -> MainLayOut.getInstance().show(MainCard.getInstance(), "Records"));
    }
}