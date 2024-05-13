package main.java.kosa.myapp.ui.components.panels;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.components.panels
 * fileName       : ContentPanel
 * author         : Yeong-Huns
 * date           : 2024-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-04        Yeong-Huns       최초 생성
 */
@Getter
public class ContentPanel extends JPanel {
    @Setter
    private int attendanceId;
    private SpringLayout layout;
    public ContentPanel() {
        super();
        layout = new SpringLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(550, 200));
    }
}
