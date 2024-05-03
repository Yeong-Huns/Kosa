package me.mini.component.button;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.component.button
 * fileName       : L_Button
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class L_Button extends JButton {
    public L_Button(String text) {
        super(text);
        setPreferredSize(new Dimension(180, 60));
    }
}
