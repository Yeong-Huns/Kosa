package main.java.kosa.myapp.ui.views.login;

import main.java.kosa.myapp.ui.components.button.CommonButton;
import main.java.kosa.myapp.ui.views.View;

/**
 * packageName    : main.java.kosa.myapp.ui.views.login
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
        super("회원가입", View.SIGNUP, 12);
        initialize();
    }
    private void initialize() {
        setBounds(460, 455, 85, 35);
        setBorderPainted(false);
        setOpaque(false);
        setContentAreaFilled(false);
    }

}
