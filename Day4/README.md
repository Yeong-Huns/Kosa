# Day4
데이터베이스 정규화 란?

>**관계형 데이터베이스의 설계에서 중복을 최소화하게 데이터를 구조화하는 프로세스**를 정규화(Normalization)라고 한다. 데이터베이스 정규화의 목표는 이상이 있는 관계를 재구성하여 작고 잘 조직된 관계를 생성하는 것에 있다.

정규화 -> 컬럼의 위치를 결정하는 과정
반정규화 -> 있을 자리가 아님에도 가져다 두는 것 원칙에 구애 받지 않음

메소드  -> 인스턴스 변수를 이용하기 위함

* 상속?
> **Organizing and Structuring**
* Avoid duplicate code (중복코드 최소화)
* Defines a common protocol for a group of classes (공통 프로토콜 설정을 통한 공통 규약 설정)
* Enables use of polymorphism (다형성 활용 가능)

* 다형성?
> 상위 타입(클래스 혹은 인터페이스)의 참조 변수로 하위 타입의 모든 객체를 참조할 수 있다.

>다형성(Polymorphism)이란 프로그램 언어 각 요소들(상수, 변수, 식, 객체, 메소드 등)이 다양한 자료형(type)에 속하는 것이 허가되는 성질을 가리킨다. -위키피디아-



#### overriding 과 overroading 의 차이
* **Overriding**
> - `super` 과 `sub`의 차이 (부모 자식의 관계)
> - Arguments must be the same, and return types must be compatible

* **Overroading**
> - 형제 관계 (상,하 개념이 없음)
> - 그저 우연히 이름이 같을 뿐인 다른 메서드

**`Overroading`** :
```java
public int addNums(int a, int b){
	return a + b; 
}
public double addNums(double a , double b){
	return a + b;
}
public void setUniqueID(String theID){
	uniqueID = theID;
}
public void setUniqueID(int ssNumber){
	setUniqueID(numString);
}
```

```java
System.out.println(1000);
System.out.println("대한민국");
System.out.println(date);
```
> 📌 `System.out.println()` 안에 `int`를 넣어도, `String`을 넣어도 작동하는 것은 오버라이드 활용의 예시이다.


오버로딩은 어쩌다 이름이 같을 뿐인 메서드이다 .
상속과 아무 연관없음(오버라이딩)

오버 로딩의 조건 :
* 리턴 타입은 다를 수 있다.
* 리턴 타입 만을 바꿀 순 없다.
* 더 제한적인 메서드로 (public -> private) 메서드를 오버로딩 하는 것은 자유롭다. <br> 어차피 새로운 메서드(private)는 오버로딩 된 메서드의 계약을 이행할 필요가 없기 때문이다.


`오버로딩` : 형제 간
`오버라이드`: 부모자식 간

## 08장 다형성,  인터페이스

`abstrack class` :
* new 를 막고 싶을 때
* 본인의 하위 클래스가 `abstrack` 일 때
* 추상 메서드를 포함할 때

자바는 다중상속을 허락하지 않음
이유 : DDD 현상 때문 (Deadly Diamond of Death)
```java
class DigitalRecorder {
	int i 
	void burn();
}
class DVDBurner extends DigitalRecorder{
	void burn();
}
class CDBurner extends DigitalRecorder{
	void burn();
}
class ComboDrive extends DVDBurner, CDBurner {
	// void burn() ? -> 대체 어떤 burn()을 선택해야하는가! 
	//오버라이딩하지 않으면 문제 발생!
}
```
-> **DDD구원자 인터페이스 등장**
```java
public interface Pet {
	... // 사실상 클래스
}
public interface Animal {
}
```
> 안에 있는 메서드가 모두 추상 메서드이다.

```java
public class Dog extends Canine implements Pet, Animal{...}
```
> 인터페이스는 abstract class 와 다르게 여러가지가 올 수 있다.

### 전략패턴이란?

> 📌 객체의 행동을 실행 시간에 변경할 수 있도록 해주는 디자인 패턴 중 하나
> 알고리즘의 일부분을 변경하고 싶을 때 전략 패턴을 사용

> 간단하게 말해서 객체가 할 수 있는 행위들 각각을 전략으로 만들어 놓고, 동적으로 행위의 수정이 필요한 경우 전략을 바꾸는 것만으로 행위의 수정이 가능하도록 만든 패턴이다.

간단한 예시와 함께 전략 패턴을 사용해보자.


```java
public interface FlyBehavior  {
	public void fly();
}
```

```java
public interface QuackBehvior {
	public void quack();
}
```

```java
public abstract class Duck {
	FlyBehavior flyBehavior;   // interface
	QuackBehvior quackBehvior; // interface

	public void swim(){
		System.out.println("오리가 헤엄친다!")
	}

	public abstract void display(); // abstract 메서드는 상속받으면 반드시 오버라이딩
	
	public void performQuack(){
		quackBehvior.quack();
	}
	
	public void performFly(){
		flyBehavior.fly();
	}

}
```

> `FlyBehavior` 와 `QuackBehvior` `interface`를 가지고 있는 `abstract class Duck`을 만들어준다. 매우매우 간단한 구조를 가지고 있다.

#### `FlyBehavior`(비행방법)엔 무엇이 있을까?  간단하게 몇 가지만 구현해보자.

```java
public class FlyNoWay implements FlyBehavior{
	@Override  
	public void fly() {  
	    System.out.println("날지 못하는 슬픈 짐승이여...");  
	}
}
```

> 저런.. 나는 법을 모르는 `FlyBehavior` 였다...  날 수 있는 오리를 만들기 위해 하늘을 날 수 있는
> `FlyBehavior`를 만들어보자!

```java
public class FlyWithWings implements FlyBehavior{
	@Override  
	public void fly() {  
	    System.out.println("멋진 달이 될래요!");  
	}
}
```
> 날개로 날 수 있는 `FlyBehavior`를 만들었다! 더 이상 오리는 날 수 없다 엄마에게 혼나지 않는 오리를 만들 수 있을 듯 하다.

#### `QuackBehvior(우는방법)`엔 무엇이 있을까? 간단하게 몇 가지만 구현해보자!
```java
public class Quack implements QuackBehavior{
	@Override  
	public void quack() {  
	    System.out.println("오리가 꽥꽥 울어요!");  
	}
}	
```
> 평범하게 우는 오리를 만들 수 있을 듯 하다.

```java
public class Squeak implements QuackBehavior{  
    @Override  
    public void quack() {  
        System.out.println("장난감 오리에서 꽉꽉 소리가 나요!");  
    }  
}
```
> 이번엔 장난감 오리의 울음소리를 만들어 봤다.

```java
public class MuteQuack implements QuackBehavior{  
    @Override  
    public void quack() {  
        System.out.println("소리가 나지 않는다.");  
    }  
}
```
> 울지 못하는 오리를 위한`MuteQuack`

#### 이제 마지막으로, 다양한 오리를 만들어보자!

```java
public class MallardDuck extends Duck{  
    public MallardDuck() {  
        quackBehavior = new Quack();  // 평범하게 우는 Behavior
        flyBehavior = new FlyWithWings();  // 날개로 하늘을 나는 Behavior
    }  
  
    @Override  
    public void display() {  
        //천둥오리  
        System.out.println("천둥오리처럼 보인다.");  
    }  
}
```

```java
public class RubberDuck extends Duck{  
    public RubberDuck() {  
        flyBehavior = new FlyNoWay();  // 날 수 없는 Behavior
        quackBehavior = new Squeak();  // 장난감 오리의 울음소리 Behavior
    }  
  
    @Override  
    public void display() {  
        System.out.println("고무오리처럼 보인다.");  
    }  
}
```

```java
public class DecoyDuck extends Duck{  
    public DecoyDuck() {  
        quackBehavior = new MuteQuack();  // 울지 못하는 Behavior
        flyBehavior = new FlyNoWay();  // 날 수 없는 Behavior
    }  
  
    @Override  
    public void display() {  
        System.out.println("가짜 오리 처럼 보인다.");  
    }  
}
```

> 장난감 오리(고무오리), 천둥오리, 가짜 오리를 만들어 보았다!
> 그런데 대체 어떤 이점이 있길래 이렇게 만드는 걸까?
> 잠시 간단한 예제를 통해 알아보자.

```java
public class DuckDriver{
	public static void main(String[] args) {
		List<Duck> duckList = new ArrayList<>();
		// Duck 리스트 생성!
		duckList.add(new MallardDuck());
		duckList.add(new DecoyDuck());
		duckList.add(new RubberDuck());
		// 천둥오리, 가짜오리, 고무오리 모두 들어가!
		duckList.forEach(Duck::display); // -> 메서드 참조
		// duckList에 들어있는 오리들, 다들 자기 생김새를 말해봐!
	}
}
```

#### 실행결과
```java
천둥오리처럼 보인다.
고무오리처럼 보인다.
가짜 오리 처럼 보인다.
```

> 매우 신기하게도, `천둥오리.display()` 나 `장난감오리.display()` 와 같이 특정 대상 명령을 하지 않고 `(Duck::display)` 로 사용했는데도 불구하고 알아서 차례대로 결과값이 나온다!


```java
public class DuckDriver{
	public static void main(String[] args) {
		List<Duck> duckList = new ArrayList<>();
		// Duck 리스트 생성!
		duckList.add(new MallardDuck());
		duckList.add(new DecoyDuck());
		duckList.add(new RubberDuck());
		// 천둥오리, 가짜오리, 고무오리 모두 들어가!
		duckList.forEach(Duck::display); // -> 메서드 참조
		// duckList에 들어있는 오리들, 다들 자기 생김새를 말해봐!
		duckList.forEach(Duck::performQuack);  
		duckList.forEach(Duck::performFly);
		// 물론 우는 방법이나 나는 방법에도 적용된다!
	}
}
```

> 이처럼 전략 패턴은 객체의 행동을 실행 시간에 변경할 수 있도록 해준다! 
