package springFW.ex01.oop.interface_ex;

/**
 * packageName    : springFW.ex01.oop.interface_ex
 * fileName       : CustomerTest
 * author         : Yeong-Huns
 * date           : 2024-05-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-27        Yeong-Huns       최초 생성
 */
public class CustomerTest {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.buy();
        customer.sell();

        Buy buy = customer;
        buy.buy();
    }
}
