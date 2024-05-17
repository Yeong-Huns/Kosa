package excel;

import java.io.IOException;

/**
 * packageName    :
 * fileName       : ${NAME}
 * author         : Yeong-Huns
 * date           : 2024-04-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-30        Yeong-Huns       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\KOSA\\Desktop";
        String filename = "\\write.xlsx";
        ExcelTest ex = new ExcelTest();
        try{
            ex.write(path, filename);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }
}