package me.mini.view.signUp;

import me.mini.component.placeholder.PlaceHolder;

/**
 * packageName    : me.mini.view.signUp
 * fileName       : TextPlaceHolder
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public class TextPlaceHolder extends PlaceHolder {
    public TextPlaceHolder(String text, int Y_Value) {
        super(text);
        setBounds(10, Y_Value, 562, 55);
    }
}
