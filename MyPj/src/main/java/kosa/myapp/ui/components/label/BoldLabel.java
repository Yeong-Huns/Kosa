package main.java.kosa.myapp.ui.components.label;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.common
 * fileName       : TitleLabel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class BoldLabel extends JLabel{
    public BoldLabel(String text, int size) {
        super(text);
        super.setFont(new Font("D2coding", Font.BOLD, size));
    }
}
