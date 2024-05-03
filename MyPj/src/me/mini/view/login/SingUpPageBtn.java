package me.mini.view.login;

import me.mini.component.button.CommonButton;

import java.awt.*;

/**
 * packageName    : me.mini.view.login
 * fileName       : SingUpBtn
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class SingUpPageBtn extends CommonButton {
    public SingUpPageBtn() {
        super("회원가입", "SignUp");
        initialize();
    }
    private void initialize() {
        setFont(new Font("D2Coding", Font.BOLD, 12));
        setBounds(460, 455, 85, 35);
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
    }
}
