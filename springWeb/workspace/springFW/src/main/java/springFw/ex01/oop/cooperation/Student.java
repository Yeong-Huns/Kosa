package springFw.ex01.oop.cooperation;

public class Student extends GeneralPassanger{
	
	private int money = 0;;
	
	public Student(int money) {
		super(money);
		this.money = money;
	}

	public Student(String name, int money) {
		super(name, money);
		// TODO Auto-generated constructor stub
		this.money = money;
	}

	@Override
	public void deposit(int amount) {
		// TODO Auto-generated method stub
		this.money += amount + (amount*0.2);
	}
	
	
	
	

}
