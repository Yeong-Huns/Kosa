package me.mini.view.signUp;

import me.mini.component.button.CommonButton;
import me.mini.view.main.MainCard;
import me.mini.view.main.MainLayOut;

import java.awt.*;

/**
 * packageName    : me.mini.view.signUp
 * fileName       : SignUpBtn
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class SignUpBtn extends CommonButton {
    public SignUpBtn() {
        super("회원가입", "attendance");
        setBounds(192, 555 ,180, 60);
        setFont(new Font("D2Coding", Font.PLAIN, 18));
    }
}
