package me.mini.component.button;

import me.mini.view.main.MainCard;
import me.mini.view.main.MainLayOut;

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
        setFont(new Font("D2Coding", Font.BOLD, 14));
        setPreferredSize(new Dimension(50, 50));
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
        addActionListener(e -> MainLayOut.getInstance().previous (MainCard.getInstance()));
    }
}
