package com.button;

import javax.swing.*;

/**
 * packageName    : com.button
 * fileName       : BUTTON
 * author         : Yeong-Huns
 * date           : 2024-04-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30        Yeong-Huns       최초 생성
 */
public class BUTTON {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button = new JButton("button");

        frame.add(button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //종료 전략 설정

        frame.getContentPane().add(button);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
