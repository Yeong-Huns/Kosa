package main.java.kosa.myapp.ui.components.button;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.common
 * fileName       : CommonButton
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class CommonButton extends JButton {

    public CommonButton(String text) {
        super(text);
        initialize(Btn.DEFAULT);
    }

    public CommonButton(String text, Btn buttonType) {
        super(text);
        initialize(buttonType);
    }

    private void initialize(Btn buttonType){
        setFont(new Font("D2Coding", Font.BOLD, buttonType.getFontSize()));
        setPreferredSize(buttonType.getSize());
    }

    public CommonButton setPosition(int x, int y){
        Dimension size = getPreferredSize();
        setBounds(x, y, size.width, size.height);
        return this;
    }

    public void setButtonAppearance(boolean isVisible){
        setBorderPainted(isVisible);
        setOpaque(isVisible);
        setContentAreaFilled(isVisible);
    }


}

