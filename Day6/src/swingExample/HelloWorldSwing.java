package swingExample;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * packageName    : swingExample
 * fileName       : HelloWorldSwing
 * author         : Yeong-Huns
 * date           : 2024-04-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-03        Yeong-Huns       최초 생성
 */
public class HelloWorldSwing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    private static void createAndShowGUI() {
        // 프레임 생성 및 타이틀 설정
        JFrame frame = new JFrame("Hello World!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);

        // 버튼 생성 및 액션 리스너 추가
        JButton btn = new JButton("Say 'Hello World'");
        btn.addActionListener(new ActionListener() {
            // Java 8 이상의 경우, 람다 표현식으로 대체 가능:
            // btn.addActionListener(e -> System.out.println("Hello World!"));
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World!");

            }
        });
        btn.addActionListener(e-> System.out.println("hi"));

        // 버튼을 프레임에 추가
        frame.getContentPane().add(btn);

        // 프레임을 화면에 표시
        frame.setVisible(true);
    }
}
