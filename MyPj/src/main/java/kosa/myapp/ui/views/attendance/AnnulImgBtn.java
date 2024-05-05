package main.java.kosa.myapp.ui.views.attendance;

import main.java.kosa.myapp.config.Image;
import main.java.kosa.myapp.ui.views.View;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.frames.MainLayOut;

import javax.swing.*;

public class AnnulImgBtn extends JButton {
    public AnnulImgBtn() {
        initialize();
    }

    private void initialize() {
        setIcon(new ImageIcon(Image.ANNUAL.getPath()));
        setBounds(0, 125, 586, 125);
        addActionListener(e -> MainLayOut.getInstance().show(MainCard.getInstance(), View.ANNUAL_LEAVES));
    }
}