package main.java.kosa.myapp.ui.components.button;

import main.java.kosa.myapp.ui.views.View;
import main.java.kosa.myapp.ui.frames.MainCard;
import main.java.kosa.myapp.ui.frames.MainLayOut;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.commuteLIst
 * fileName       : BackButton
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class BackButton extends JButton {
    public BackButton() {
        super("<<");
        initialize();
        addActionListener(e -> MainLayOut.getInstance().previous (MainCard.getInstance()));
    }
    public BackButton(View cardName){
        super("<<");
        initialize();
        addActionListener(e -> MainLayOut.getInstance().show(MainCard.getInstance(), cardName));
    }

    private void initialize() {
        setFont(new Font("D2Coding", Font.BOLD, 14));
        setPreferredSize(new Dimension(50, 50));
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
    }
}
