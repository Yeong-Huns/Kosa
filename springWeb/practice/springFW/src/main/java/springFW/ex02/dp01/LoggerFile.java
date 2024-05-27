package springFW.ex02.dp01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * packageName    : springFW.ex02.dp01
 * fileName       : LoggerFile
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class LoggerFile implements LoggerInterface{
    @Override
    public void writeLog(String log) throws Exception {
        System.out.println("입력 전압: " + log);
        try{
            Path path = Paths.get("./log.txt");
            if(!Files.exists(path)) Files.createFile(path);

            byte[] bytes = (log + System.lineSeparator()).getBytes();

            Files.write(path, bytes, StandardOpenOption.APPEND);
        }   catch (Exception e){
            e.printStackTrace();
        }
    }
}
