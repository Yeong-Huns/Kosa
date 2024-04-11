package exGeneric3;

/**
 * packageName    : exGeneric3
 * fileName       : Student
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Student <A, B, C> extends Person {
    private B garde;
    private C height;

    public B getGarde(){
        return garde;
    }
    public void setGarde(B garde){
        this.garde = garde;
    }
    public C getHeight(){
        return height;
    }
    public void setHeight(C height){
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "garde=" + garde +
                ", height=" + height +
                ", name=" + name +
                ", age=" + age +
                '}';
    }
}
