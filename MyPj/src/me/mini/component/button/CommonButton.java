package me.mini.component.button;

import me.mini.view.main.MainCard;
import me.mini.view.main.MainLayOut;

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
        // 이 버튼을 누르면 어떤 페이지로 이동할지 저장합니다.
    }
}
