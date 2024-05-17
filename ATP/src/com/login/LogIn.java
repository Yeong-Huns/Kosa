package com.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * packageName    : com.login
 * fileName       : LogIn
 * author         : Yeong-Huns
 * date           : 2024-04-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30        Yeong-Huns       최초 생성
 */
public class LogIn extends JFrame {
    public LogIn() {
        JPanel panel = new JPanel();
        // ID
        JLabel idLabel = new JLabel("ID: ");
        JTextField idTextBox = new JTextField(10); // 텍스트박스
        // Pwd
        JLabel pwdLabel = new JLabel("Password: ");
        JPasswordField pwdTextBox = new JPasswordField(10);
        // loginBtn
        JButton loginButton = new JButton("Login");

        panel.add(idLabel);
        panel.add(idTextBox);
        panel.add(pwdLabel);
        panel.add(pwdTextBox);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String localId = "Kim";
                String localPwd = "1234";
                if(localId.equals(idTextBox.getText()) && localPwd.equals(pwdTextBox.getText())) {
                    JOptionPane.showMessageDialog(null, "Login Successful"); // 모달창 출력 ()
                } else {
                    JOptionPane.showMessageDialog(null, "Login Failed");
                }

            }
        });

        add(panel);

        JPasswordField password = new JPasswordField();
        setVisible(true); // 프레임 창이 뜨는걸 확인 가능
        setSize(600, 400); //프레임 크기 조절
        setLocationRelativeTo(null); // 가운데에서 창이 뜸
        setResizable(false);//사이즈 고정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x 버튼 종료


    }

    public static void main(String[] args) {
        new LogIn();
    }
}
