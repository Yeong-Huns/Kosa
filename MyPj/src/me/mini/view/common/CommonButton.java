package me.mini.view.common;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.common
 * fileName       : CommonButton
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class CommonButton extends JButton {
    public CommonButton(String text) {
        super(text);
        setPreferredSize(new Dimension(120, 40));
    }
}
