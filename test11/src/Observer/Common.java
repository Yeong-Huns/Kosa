package Observer;

/**
 * packageName    : Observer
 * fileName       : Tom
 * author         : Yeong-Huns
 * date           : 2024-04-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-10        Yeong-Huns       최초 생성
 */
public class Common implements Subscriber{

    private String name;

    public Common(String name) {
        this.name = name;
    }

    @Override
    public void update(String content) {
        System.out.println("새로운 영상이 업로드 되었습니다!!!" + content);
    }

    @Override
    public void update(int size) {
        System.out.println(name+"이 보고있는 화면\n 구독자 수: " + size);
    }
}
