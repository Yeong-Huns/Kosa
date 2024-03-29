## day4

* [오브젝트](http://www.yes24.com/Product/Goods/74219491)
* [객체지향의 사실과 오해](http://www.yes24.com/Product/Goods/18249021)

* What Is an Object? 
	 -> 소프트웨어 번들
* What Is Class 
	-> BluePrint
* What Is Inheritance 
	-> 코드 재활용 
* What Is an Interface 
	-> 계약 , 메서드
* What Is a Package 
	-> 유일한 식별자


### 변수

> 📌 관점에 따라 2가지 방식으로 나눌 수 있다.

1. Pimative data type , Reference data type
-> 데이터 타입으로 분류
> Primative data type : byte, short, int, long, float, double, char, boolean 
> Reference data type : Class, Interface, Array, Enum


3. Instance(Non-Static Field) , Class(Static Fields) , Local, Parameters
-> 필드로 분류

```java
class Human {

	private int age;
	private Smartphone phone;

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return this.age;
	}
	
	public void setPhone(Smartphone phone){
		this.phone = phone;
	}

	public Smartphone getPhone(){
		return this.phone;
	}


	public void sendText(String message){
		phone.sendMessage(message);
	}

	public void speak(){
			
	}
}
```

```java
class SmartPhone {
	// ...
	// ...
	public void sendMessage(String message){
		System.out.println(message);
	}

}
```

```java
main(){
	Human jk = new Human();
	Human dt = new Human();
	Smartphone smarty = new Smartphone(__, __, __, __);

	jk.setPhone(smarty); 
	jk.sendText("돈 갚아!!!"); // 위임
	// 내가 가지고 있는 스마트폰의 기능(메서드)를 활용한다 (위임)
}
```


#### Primitive Data Types

> Primitive Data Types : simple 한 타입 (직역하면 원시 타입인데 적절하지 않은 느낌이라 하심)

##### Default Value

| Daty Type             | Default Value(for fields) |
| --------------------- | ------------------------- |
| byte                  | 0                         |
| short                 | 0                         |
| int                   | 0                         |
| long                  | 0L                        |
| float                 | 0.0f                      |
| double                | 0.0d                      |
| char                  | "\u0000"                  |
| String(or any object) | null                      |
| boolean               | false                     |

##### Arrays
> An _array_ is a container object that holds a fixed number of values of a single type. The length of an array is established when the array is created. After creation, its length is fixed. You have seen an example of arrays already, in the `main` method of the "Hello World!" application. This section discusses arrays in greater detail.

* 한 종류의 값을 담을 수 있는 고정된 공간을 가지는 컨테이너 오브젝트

```java
public class ArrayDemo {  
    public static void main(String[] args) {  
        // declares an array of integers  
        int[] anArray;  
  
        // allocates memory for 10 integers  
        anArray = new int[10];  // 10 개의 int 를 가질 수 있는 Array 생성  
  
        // initialize first element  
        anArray[0] = 100;  
        // initialize second element  
        anArray[1] = 200;  
        // and so forth  
        anArray[2] = 300;  
        anArray[3] = 400;  
        anArray[4] = 500;  
        anArray[5] = 600;  
        anArray[6] = 700;  
        anArray[7] = 800;  
        anArray[8] = 900;  
        anArray[9] = 1000;  
  
        System.out.println("Element at index 0: "  
                + anArray[0]);  
        System.out.println("Element at index 1: "  
                + anArray[1]);  
        System.out.println("Element at index 2: "  
                + anArray[2]);  
        System.out.println("Element at index 3: "  
                + anArray[3]);  
        System.out.println("Element at index 4: "  
                + anArray[4]);  
        System.out.println("Element at index 5: "  
                + anArray[5]);  
        System.out.println("Element at index 6: "  
                + anArray[6]);  
        System.out.println("Element at index 7: "  
                + anArray[7]);  
        System.out.println("Element at index 8: "  
                + anArray[8]);  
        System.out.println("Element at index 9: "  
                + anArray[9]);  
    }  
}
```

```java
OutPut: 
Element at index 0: 100
Element at index 1: 200
Element at index 2: 300
Element at index 3: 400
Element at index 4: 500
Element at index 5: 600
Element at index 6: 700
Element at index 7: 800
Element at index 8: 900
Element at index 9: 1000
```

```java
public class ArrayCopyDemo {  
    public static void main(String[] args) {  
        String[] copyFrom = {  
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",  
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",  
                "Marocchino", "Ristretto" };  
  
        String[] copyTo = new String[7];  
        System.arraycopy(copyFrom, 2, copyTo, 0, 7);  
        for (String coffee : copyTo) {  
            System.out.print(coffee + " ");  
        }    }  
}
```

```java
public class ArrayCopyOfDemo {  
    public static void main(String[] args) {  
        String[] copyFrom = {  
                "Affogato", "Americano", "Cappuccino", "Corretto", "Cortado",  
                "Doppio", "Espresso", "Frappucino", "Freddo", "Lungo", "Macchiato",  
                "Marocchino", "Ristretto" };  
  
        String[] copyTo = java.util.Arrays.copyOfRange(copyFrom, 2, 9);  
        for (String coffee : copyTo) {  
            System.out.print(coffee + " ");  
        }    }  
}
```

##### Primitive declarations with assignments

```java
int x;
x = 234;
byte b = 89; 
bolean isFun = true;
double d = 3456.98;
char c = 'f';
int z = x;
boolean isPunkRock;
isPunkRock = false;
boolean powerOn;
powerOn = isFun;
long big = 3456789L;
float f = 32.5f;
```

### 메소드
> 📌Methods use Instance Variables



파라미터 
```java
void bark(int numOfBarks) <- this
```

argument
```java
Dog d = new Dog();
d.bark(3) <- this
;```


```java
void go(){
	Dog dog = new Dog();
	takeDog(dog);
}
void takeDog(Dog d){
	Dog mydog = d;
}
```

```java
void go(){
	Dog dog = createDog();
	dog.bark();
}
Dog createDog(){
	Dog d = new Dog();
	return d;
}
```
Instance variables -> 자동 초기화
Local variables -> 사용 전 반드시 초기화 필요

Instance Variables : 클래스 내부 변수(메소드 레벨까지 내려가지 않음)

```java
String str1 = "apple"; //리터럴을 이용한 방식
String str2 = "apple"; //리터럴을 이용한 방식
String str3 = new String("example"); //new 연산자를 이용한 방식
String str4 = new String("example"); //new 연산자를 이용한 방식
```

![[str.png]]
```java
public class compare {
    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = new String("abcd");
		
	        if(s1 == s2) {//false
            System.out.println("두개의 값이 같습니다.");
        }else {
            System.out.println("두개의 값이 같지 않습니다.");
        }
    }
}
```

```java
public class compare {
    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = new String("abcd");
		
	        if(s1.equals(s2)) {//true
            System.out.println("두개의 값이 같습니다.");
        }else {
            System.out.println("두개의 값이 같지 않습니다.");
        }
    }
}

```


### inheritance and polymorphism

#### Inheritance
>  - provides a powerful and natural mechanism for organizing and structuring your software
 > - avoids duplicate code
 > - defines a common protocol for a group of classes
 > - enables use of polymorphism

> 📌 객체 지향 프로그래밍은 소프트웨어를 조직화하고 구조화하는 강력하고 자연스러운 메커니즘을 제공합니다. 이를 통해 중복 코드를 피하고, 일련의 클래스에 대한 공통 프로토콜을 정의하며, 다형성의 사용을 가능하게 합니다.

