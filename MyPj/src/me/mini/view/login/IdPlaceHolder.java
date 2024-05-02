package me.mini.view.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * packageName    : me.mini
 * fileName       : PlaceHolder
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class IdPlaceHolder extends JTextField{
    public IdPlaceHolder(String text) {
        super(text);
        initialize();
    }


    private void initialize(){
        setForeground(Color.GRAY);
        setFont(new Font("D2Coding", Font.PLAIN, 18));
        setBounds(12, 288, 562, 55);
        addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals("아이디를 입력하세요")) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.GRAY);
                    setText("아이디를 입력하세요");
                }
            }
        });
    }
}
