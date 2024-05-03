package me.mini.component.button;

import me.mini.viewManager.MainCard;
import me.mini.viewManager.MainLayOut;

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
    public CommonButton(String text, String disPlayName) {
        super(text);
        setFont(new Font("D2Coding", Font.BOLD, 14));
        setPreferredSize(new Dimension(120, 40));
        addActionListener(e -> MainLayOut.getInstance().show(MainCard.getInstance(),disPlayName));
        // disPlayName -> card 페이지 이름
    }
}
