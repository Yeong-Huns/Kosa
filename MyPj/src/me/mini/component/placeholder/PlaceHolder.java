package me.mini.component.placeholder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * packageName    : me.mini.component.placeholder.textField
 * fileName       : PlaceHolder
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class PlaceHolder extends JTextField {

    public PlaceHolder(String text) {
        super(text);
        initialize(text);
    }


    private void initialize(String text){
        setForeground(Color.GRAY);
        setFont(new Font("D2Coding", Font.PLAIN, 18));
        addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(text)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.GRAY);
                    setText(text);
                }
            }
        });
    }
}
