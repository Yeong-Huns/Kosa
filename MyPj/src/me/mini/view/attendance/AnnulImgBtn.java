package me.mini.view.attendance;

import javax.swing.*;

public class AnnulImgBtn extends JButton {
    public AnnulImgBtn() {
        initialize();
    }

    private void initialize() {
        String annulImg = "img/annual.jpg";
        ImageIcon imageIcon = new ImageIcon(annulImg);
        setIcon(new ImageIcon(annulImg));
        setBounds(0, 125, 586, 125);
    }
}