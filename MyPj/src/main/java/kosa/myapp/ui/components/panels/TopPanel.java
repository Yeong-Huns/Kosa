package main.java.kosa.myapp.ui.components.panels;

import main.java.kosa.myapp.ui.components.label.BoldLabel;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.common
 * fileName       : TopPanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class TopPanel extends JPanel {
    public TopPanel() {
        super(new BorderLayout());
        initialize();
    }

    public TopPanel(String str){
        super(new BorderLayout());
        initialize();
        add(new BoldLabel(" "+str, 24), BorderLayout.WEST);
    }

    private void initialize(){
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(580, 100));
    }

    public TopPanel setAbsoluteLayout(){
        Dimension size = getPreferredSize();
        setBounds(10,10, size.width, size.height);
        return this;
    }
}
