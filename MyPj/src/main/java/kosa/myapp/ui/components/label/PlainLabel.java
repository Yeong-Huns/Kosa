package main.java.kosa.myapp.ui.components.label;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.components.label
 * fileName       : PlainLabel
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class PlainLabel extends JLabel {
    public PlainLabel(String text, int size) {
        super(text);
        super.setFont(new Font("D2coding", Font.PLAIN, size));
    }
}
