package main.java.kosa.myapp.ui.components.button;

import lombok.Getter;

import java.awt.*;

/**
 * packageName    : main.java.kosa.myapp.ui.components.button
 * fileName       : Btn
 * author         : Yeong-Huns
 * date           : 2024-05-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-05        Yeong-Huns       최초 생성
 */
@Getter
public enum Btn {
    X_LARGE(new Dimension(250, 60), 18),
    LARGE(new Dimension(180, 60), 16),
    DEFAULT(new Dimension(120, 40), 14),
    SMALL(new Dimension(85, 35), 12);

    private final Dimension size;
    private final int fontSize;

    Btn(Dimension size, int fontSize) {
        this.size = size;
        this.fontSize = fontSize;
    }
}
