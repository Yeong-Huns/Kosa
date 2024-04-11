package myStream;

import java.util.Arrays;
import java.util.List;

/**
 * packageName    : myStream
 * fileName       : Dummy
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Dummy {
    public static List<Customer> dummyCustomers(){
        return Arrays.asList(new Customer("Kim","HaYoon",30),
                new Customer("Kim","HaYoon",40),
                new Customer("Park","jiHoon",10),
                new Customer("Lee","HaYoon",30),
                new Customer("son","kijung",20),
                new Customer("David","HaYoon",30),
                new Customer("park","jiHoon",30),
                new Customer("Lee", "HaYoon", 30),
                new Customer("Son", "Joon",20),
                new Customer("Park","jiHoon",10),
                new Customer("Park","HaYoon",10),
                new Customer("kim","jiHoon",10),
                new Customer("lee","jiHoon",10),
                new Customer("park","HaYoon",10),
                new Customer("Son","jiHoon",10),
                new Customer("Park","HaYoon",10),
                new Customer("son","jjj",15),
                new Customer("lee","HaYoon",22),
                new Customer("kim","jHoon",55),
                new Customer("kim","loon",20),
                new Customer("park","dddon",14),
                new Customer("lee","dddon",14),
                new Customer("park","dddon",14),
                new Customer("kim","dddon",14),
                new Customer("kim","dddon",14)
                );
    }
}
