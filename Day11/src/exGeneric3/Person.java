package exGeneric3;

/**
 * packageName    : MyGeneric
 * fileName       : Person
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Person <A, B>{
    protected A name;
    protected B age;
    public A getName(){
        return name;
    }
    public void setName(A name){
        this.name = name;
    }
    public B getAge(){
        return age;
    }
    public void setAge(B age){
        this.age = age;
    }

}
