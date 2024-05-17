package com.event1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * packageName    : com.event
 * fileName       : EVENT
 * author         : Yeong-Huns
 * date           : 2024-04-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30        Yeong-Huns       최초 생성
 */
public class EventTest implements ActionListener {
    private JButton button;

    public static void main(String[] args) {
        EventTest test = new EventTest();
        test.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        button = new JButton("Click Me");

        button.addActionListener(this);

        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setText("클릭 완료");
    }
}
