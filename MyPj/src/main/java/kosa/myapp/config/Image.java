package main.java.kosa.myapp.config;

/**
 * packageName    : main.java.kosa.myapp.config
 * fileName       : Image
 * author         : Yeong-Huns
 * date           : 2024-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-04        Yeong-Huns       최초 생성
 */
public enum Image {
    ANNUAL("annual.jpg"),
    ATTENDANCE("attendance.jpg");

    private final String name;
    Image(String name) {
        this.name = name;
    }

    public String getPath(){
        String path = "src/main/resources/static/img/";
        return path +name;
    }
}
