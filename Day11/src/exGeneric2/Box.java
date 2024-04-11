package exGeneric2;

/**
 * packageName    : MyGeneric
 * fileName       : Box
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Box <T extends Person>{
    private T data;
    public T getData(){
        return data;
    }
    public void setData(T data){
        this.data = data;
    }
}
