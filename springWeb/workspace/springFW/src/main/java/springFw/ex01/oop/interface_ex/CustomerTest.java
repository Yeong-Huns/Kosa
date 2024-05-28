package springFw.ex01.oop.interface_ex;

public class CustomerTest {
	
	public static void main(String[] args) {
		Customer cust = new Customer();
		cust.buy();
		cust.sell();
		
		Buy buy = cust; // Interface -> Spec , 사용자 정의 자료형 
		buy.buy();
	}

}
