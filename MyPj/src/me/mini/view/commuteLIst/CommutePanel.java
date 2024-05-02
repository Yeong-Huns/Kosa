package me.mini.view.commuteLIst;

import me.mini.view.common.*;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : me.mini.view.commuteLIst
 * fileName       : CommutePanel
 * author         : Yeong-Huns
 * date           : 2024-05-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-02        Yeong-Huns       최초 생성
 */
public class CommutePanel extends JPanel{
    public CommutePanel(String string) {
        super(new BorderLayout());
        initialize(string);
    }
    private void initialize(String string){
        add(new TopPanel(new TitleLabel(string)), BorderLayout.NORTH);
        add(new ScrollPanel(new ScrollableRecordsPanel()), BorderLayout.CENTER);
    }
}
