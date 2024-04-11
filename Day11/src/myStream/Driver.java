package myStream;

import java.util.List;

/**
 * packageName    : myStream
 * fileName       : Driver
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class Driver {
    public static void main(String[] args) {
        MyStream myStream = new MyStream();

        List<Customer> customers = Dummy.dummyCustomers();
        myStream.sortAndCount(customers); // 손님의 리스트를 받고 성씨의 갯수로 오름차순

        System.out.println(myStream.returnRepeatedString("폭풍저그 홍진호가 간다!",2));
    }
}
