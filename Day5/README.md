# 5 일차

메소드는 Instance 변수를 사용한다.
> - Methods use Instance Variables

## today
* 8장 (다형성, 인터페이스) 마무리
* 생성자
* 가비지 컬렉터

## [Design Pattern]SOLID 원칙
> 📌 SOLID란 객체 지향 프로그래밍을 하면서 지켜야하는 5대 원칙으로 각각 SRP(단일 책임 원칙), OCP(개방-폐쇄 원칙), LSP(리스코프 치환 원칙), DIP(의존 역전 원칙), ISP(인터페이스 분리 원칙)의 앞글자를 따서 만들어졌다. SOLID 원칙을 철저히 지키면 시간이 지나도 변경이 용이하고, 유지보수와 확장이 쉬운 소프트웨어를 개발하는데 도움이 되는 것으로 알려져있다.

###  [ 단일 책임의 원칙(SRP, Single Responsibility Principle) ]
> 📌[단일 책임 원칙 (Single responsibility principle)](https://ko.wikipedia.org/wiki/%EB%8B%A8%EC%9D%BC_%EC%B1%85%EC%9E%84_%EC%9B%90%EC%B9%99 "단일 책임 원칙")

한 [클래스](https://ko.wikipedia.org/wiki/%ED%81%B4%EB%9E%98%EC%8A%A4_(%EC%BB%B4%ED%93%A8%ED%84%B0_%EA%B3%BC%ED%95%99) "클래스 (컴퓨터 과학)")는 하나의 책임만 가져야 한다.

### [ 개방 폐쇄 원칙 (Open-Closed Principle, OCP) ]
> 📌 “소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.”

### [ 인터페이스 분리 원칙 (Interface segregation principle, ISP) ]
> 📌 “특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.”


### [ 리스코프 치환 원칙 (Liskov Substitution Principle, LSP) ]
📌 “프로그램의 [객체](https://ko.wikipedia.org/wiki/%EA%B0%9D%EC%B2%B4 "객체")는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.” [계약에 의한 설계](https://ko.wikipedia.org/w/index.php?title=%EA%B3%84%EC%95%BD%EC%97%90_%EC%9D%98%ED%95%9C_%EC%84%A4%EA%B3%84&action=edit&redlink=1 "계약에 의한 설계 (없는 문서)")를 참고하라.

### [ 의존 역전 원칙 (Dependency Inversion Principle, DIP) ]
> 📌 프로그래머는 “추상화에 의존해야지, 구체화에 의존하면 안된다.” [의존성 주입](https://ko.wikipedia.org/wiki/%EC%9D%98%EC%A1%B4%EC%84%B1_%EC%A3%BC%EC%9E%85 "의존성 주입")은 이 원칙을 따르는 방법 중 하나다.



* My_Sql : primary_Key 기준으로 index organized table
* Oracle : 저장 빠름 / 조회 느림

데이터 : 음식
데이터 타입 : 여러가지 그릇
어떤식으로 organized structuring 할 것인가
Data Structrue (자료 구조) : Table , Tree, List , Stack, Heap, Queue ...

* 인스턴스 변수 : 클래스의 변수지만 메서드 밖에
* 로컬 변수 : 메서드 내부에 존재

멀티 쓰레드 프로그래밍 : 스택이 2개 이상
병렬 처리의 문제 -> 스레드 동기화


#### Constructor ?
* Not a Method
* It's got the code that runs when you say new
* Constructor is Code Block -> that runs when you instantiate an object.
* to initialize the state of an object.
* to make and assign values to the object's instance.

```java
puclic class Duck{
	int size: 
	public Duck() {
	this.size = ? 
	}
}
```
생성자 생성시
-> 하나라도 생성했을 경우 기본 생성자 만들어주지않음 !
```java
public class Apple {  
    private int weight;  
    private Color color;  
  
    public Apple(int weight, Color color) {  
        this.weight = weight;  
        this.color = color;  
    }  // -> 생성자 생성
}
```

```java
Apple apple = new Apple(); // -> 오류 발생!!! 
// 생성자가 이미 존재하는 경우 생성자를 만들어주지 않음 !
```


### scope
```java
public void doStuff(){
	boolean b = true; 
	go(4);
}
public void go(int x){
	int z = x + 24; 
	Crazy();
}
public void crazy(){
	char c = 'a';
}
```

> b 의 라이프 사이클 -> go 가 종료 / 스코프 -> doStuff();


final -> 언젠가 초기화를 하면 바꾸지 못함

Wrapping a primitive
현재는 autoBoxing 에 의해 그렇게 필요하진 않다.
하지만 래퍼클래스 내부의 static 클래스 중에 유용한것이 꽤 있어서 알아서 나쁠건 없다.


### 제네릭 타입
```java
	class Dog<>{//-> 제네릭

}
```

```java
public class Box{
	private Object object;

	public void set(Object object){
		this.object = object;
	}

	public Object get() {
		return object;
	}	

}
```

```java
public class Box<T> {
	// T stands for "Type"
	private T t;
	public void set(T t){
		this.t = t;
	}
	public T get(){
		return t;
	}
}   //-> Box 클래스의 제네릭 버전!
```
> 💡 `Object`의 모든 항목은 T로 바뀐다.
> 형식 변수는 지정한 기본 형식이 아닌 모든 형식(모든 클래스 형식, 인터페이스 형식, 배열 형식 또는 다른 형식 변수)이 될 수 있다.
> 이와 동일한 기술을 적용하여 제네릭 타입 인터페이스를 만들 수 있다.



### why use Generics

> 💡 간단히 말해서, 제네릭은 클래스와 인터페이스, 메서드를 정의할 때 클래스와 인터페이스가 매개 변수가 될 수 있도록 한다.
> 메서드를 정의할 때 사용되는 `formal parameters` 와 마찬가지로
> `Type parameters` 다른 입력에  다시 사용할 수 있는 방법을 제공해준다.
> 차이점은 `formal parameters`는 `Value` 인 반면, `type parameters`는 `Type`이다.

> 코드에 `Generics`를 사용하는 것은 `non-generics` 코드에 비해 많은 장점이 있다.

* 컴파일 시점에 강력한 타입 체킹을 가능하게 해준다.
* Cast(타입 캐스팅) 근절
  아래의 `code snippet`(코드 조각)은 제네릭을 사용하지 않았을 때 캐스팅을 필요로 한다.
  ```java
  List list = new ArrayList();
  list.add("hello");
  String s = (String) list.get(0); // Object -> String 
  //다운 캐스팅 필요!!!
  ```
  반면 제네릭을 사용하여 다시 작성하였을 경우, 코드는 캐스팅을 필요로 하지 않게 된다.
  ```java
  List<String> list = new ArrayList<>();
  list.add("hello");
  String s = list.get(0); // String -> String, 캐스팅 X
  ```

* 프로그래머의 제네릭_알고리즘 구현을  가능하게 한다.
  제네릭을 사용함으로써, 프로그래머는 다양한 타입에서 동작하고, 커스터마이징 가능하며, 타입 안정성이 뛰어나고 쉽게 읽을 수 있는 제네릭 알고리즘을 구현할 수 있게 되었다.

```java
Dog<T> {} // -> Formal parameter , Type parameter, Type variable

= new Dog<Integer>; // ->  Actual Argument , Type Argument
```

> 제네릭을 사용하면 런타임 시점까지 갈 수도 있는 버그를 컴파일 시점에 잡아낼 수 있다.


#### 원시 형식
> 원시 형식은 형식 인수가 없는 제네릭 클래스 또는 인터페이스의 이름.

```java
public class Box<T>{

	} // -> 이렇게 만들었지만
Box rawBox = new Box(); // -> 이렇게 사용해도 된다.
```

```java
List list = new ArrayList();  
list.add(100);  
list.add("korea");  
list.add(10L);  
list.add(3.14);
// -> 타입 자체는 제네릭이지만 , 이런식으로 raw타입으로 사용하게 되면 
// 사용할 순 있지만 경고가 뜬다.
```


#### Type inference (타입 인퍼런스)

```java
public class Util {  
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2){  
        return p1.getKey() == p2.getKey() && p1.getValue().equals(p2.getValue());  
    }  
}
```

```java
Pair<Long, String> Test1 = new OrderedPair<>(1L, "Generic")
Pair<Long, String> Test2 = new OrderedPair<>(1L, "Generic")
boolean compareResult = Util.<Long, String>compare(Test1, Test2); // 제네릭타입
```
> 본래라면 이런식으로 사용하지만, Type inference 기능을 사용하면 꺾쇠 괄호안에 형식을 지정하지 않을 수 있다. (Test1 과 Test2의 형을 보고 유추)

```java
boolean compareResult = Util.compare(Test1, Test2);
```

#### Nested Classes
> 📌 클래스 안의 클래스, 메서드 안의 클래스

```java
class OuterClass {
...
	class InnerClass{
	...
	}
	static class StaticNestedClass {
	...
	}

}

```

* static newsted class
* Non-static nested class = Inner Class(Nested Non -static Class)


#### Nested Classees 를 사용하는 이유?

* 한 곳에서만 사용되는 클래스를 논리적으로 그룹화하는 방법
* 캡슐화를 증가시킨다.
* 읽기 쉽고 유지 관리하기 쉬운 코드로 이어질 수 있다.

#### Inner Class
>
```java
class OuterClass{
	...
	class InnerClass{
	...
	...
	}
	...

}
```



---
Reference
* 출처: [https://mangkyu.tistory.com/194](https://mangkyu.tistory.com/194) [MangKyu's Diary:티스토리]*