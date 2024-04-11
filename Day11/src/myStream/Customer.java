package myStream;

/**
 * packageName    : myStream
 * fileName       : Customer
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Customer {
    private String name;
    private int age;
    public Customer(String firstName, String lastName, int age) {
        if(isValid(firstName, lastName, age)) {
            throw new IllegalArgumentException("유효하지 않은 값");
        }
        this.name = firstName.toUpperCase()+"_"+lastName;
        this.age = age;
    }
    private boolean isValid(String firstName, String lastName, int age){
        return firstName.isBlank()||lastName.isBlank()||age<0;
    }


    public String getFirstName(){
        return name.split("_")[0];
    }
}
