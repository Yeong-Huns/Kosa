package me.mini.view.common;

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
public class TitleLabel extends JLabel{
    public TitleLabel(String text) {
        super(text);
        super.setFont(new Font("D2coding", Font.BOLD, 24));
    }
}
