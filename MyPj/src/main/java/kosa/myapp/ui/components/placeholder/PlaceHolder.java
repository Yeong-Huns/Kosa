package main.java.kosa.myapp.ui.components.placeholder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * packageName    : main.java.kosa.myapp.ui.components.placeholder.textField
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
        setPreferredSize(new Dimension(562, 55));
        initialize(text);
        //전달받은 text 내용대로 placeHolder 생성
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
    public PlaceHolder setYPosition(int yPosition){
        Dimension size = getPreferredSize();
        setBounds(10, yPosition, size.width, size.height);
        return this;
    }
}
