package goodDog;

/**
 * packageName    : goodDog
 * fileName       : GoodDog
 * author         : Yeong-Huns
 * date           : 2024-03-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-29        Yeong-Huns       최초 생성
 */
public class GoodDog {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void bark(){
        if(size > 60){
            System.out.println("woof! woooof!");
        } else if (size > 14){
            System.out.println("Ruff! Ruff!");
        } else {
            System.out.println("yip! yip!");
        }
    }
}
