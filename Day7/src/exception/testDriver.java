package exception;

import java.time.LocalDateTime;

/**
 * packageName    : exception
 * fileName       : testDriver
 * author         : Yeong-Huns
 * date           : 2024-04-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-04        Yeong-Huns       최초 생성
 */
public class testDriver {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        int year = dateTime.getYear();

    }
}
