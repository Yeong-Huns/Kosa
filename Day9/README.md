>[!tip]- 복습 키워드
> - [0] 라이브러리란 무엇인가?
> - [1] 프레임워크워크란 무엇인가?
> - [2] Enum타입
> - [3] 컬렉션 프레임워크
> - [4] 람다, 스트림 

>[!success]- 오늘의 키워드
>- [0] UML
>- [1] 클래스 다이어그램 
>- [2] 옵저버 패턴 


# UML
>[!list]- 색션 키워드
>- [0] 클래스 다이어그램  
> - [1] 유스케이스 다이어그램 
> - [2] 시퀀스 다이어그램 

>[!danger] 개요
>**통합 모델링 언어**(UML, [영어](https://ko.wikipedia.org/wiki/%EC%98%81%EC%96%B4 "영어"): Unified Modeling Language)는 [소프트웨어 공학](https://ko.wikipedia.org/wiki/%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4%5F%EA%B3%B5%ED%95%99 "소프트웨어 공학")에서 사용되는 표준화된 범용 [모델링 언어](https://ko.wikipedia.org/wiki/%EB%AA%A8%EB%8D%B8%EB%A7%81%5F%EC%96%B8%EC%96%B4 "모델링 언어")이다. 이 표준은 UML을 고안한 [객체 관리 그룹](https://ko.wikipedia.org/wiki/%EA%B0%9D%EC%B2%B4%5F%EA%B4%80%EB%A6%AC%5F%EA%B7%B8%EB%A3%B9 "객체 관리 그룹")에서 관리 하고 있다.
>
>UML은 소프트웨어 집약 시스템의 [시각적 모델](https://ko.wikipedia.org/w/index.php?title=%EC%8B%9C%EA%B0%81%EC%A0%81%5F%EB%AA%A8%EB%8D%B8&action=edit&redlink=1 "시각적 모델 (없는 문서)")을 만들기 위한 도안 표기법을 포함한다.

>[!warning] UML 구성요소
>>[!info]- 소프트웨어 개발 방법론
>>
>>UML 그 자체는 개발 방법이 아니지만 그 당시 주도적이었던 객체 지향 소프트웨어 개발 방법론(예를 들면 
>>[Booch 방법론](https://ko.wikipedia.org/w/index.php?title=Booch%5F%EB%B0%A9%EB%B2%95%EB%A1%A0&action=edit&redlink=1 "Booch 방법론 (없는 문서)"), [객체 모델링 기법](https://ko.wikipedia.org/w/index.php?title=%EA%B0%9D%EC%B2%B4%5F%EB%AA%A8%EB%8D%B8%EB%A7%81%5F%EA%B8%B0%EB%B2%95&action=edit&redlink=1 "객체 모델링 기법 (없는 문서)"), [Objectory](https://ko.wikipedia.org/w/index.php?title=Objectory&action=edit&redlink=1 "Objectory (없는 문서)"))과 잘 어울리도록 설계되었다. 
>>UML 발전해 감에 따라서 UML의 장점을 취하기 위해 몇몇 다른 방법론(예를 들면 객체 모델링 기법)이 개선되었다. 
>>또 UML을 기반으로 한 새 방법론이 만들어지기도 했는데 [IBM 래셔널 통합 프로세스](https://ko.wikipedia.org/wiki/%EB%9E%98%EC%85%94%EB%84%90%5F%ED%86%B5%ED%95%A9%5F%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4 "래셔널 통합 프로세스")(RUP)가 가장 유명하다. 
>>이 외에도 추상 방법론(Abstraction Method), 동적 시스템 개발 방법론 등 더 특수한 해결책이나 다른 목적을 달성하기 위해 설계된 UML 기반의 방법론이 많이 있다.
>
>


**모델**
모델은 이학 및 공학 분야에서 상당히 유용하게 쓰이는 개념으로서 가장 일반적인 의미로 말하면, "모델을 만든다"는 것은 잘 모르고 있는 것을 이해하는데 도움이 될 것으로 추측되는 어떤 것을 사용한다는 뜻이다. 어떤 분야에서는 이 모델의 일련의 수식(방정식)의 집합으로 정의되기도 하며, 다른 분야에서는 컴퓨터 시뮬레이션을 모델로 삼기도 한다.
UML의 여러 가지 그래픽 요소는 하나의 큰 그림, 즉 다이어그램을 그리는데 사용된다. UML은 언어이기 때문에, 이들 그래픽 요소들을 맞추는 데에는 규칙이 필요하다. 다이어그램의 목적은 시스템을 여러 가지 시각에서 볼 수 있는 뷰(View)를 제공하는 것이며, 이러한 뷰의 집합을 모델(Model)이라고 한다. 시스템의 UML 모델은 건물을 짓는 건축가의 스케일 모델과도 비슷하다고 말할 수 있다. UML 모델은 시스템 자체의 “목적 행동”을 설명하는 언어이다. UML 모델은 시스템의 “구현 방법을 설명하는 수단”이 아니다.





## 클래스 다이어그램 

**클래스 다이어그램**
대부분의 사물은 자기만의 속성과 일정한 행동 수단을 지니고 있다. 
이러한 행동을 오퍼레이션(Operation)의 집합으로 생각할 수 있다. UML에서는 두단어 이상으로 이루어진 클래스 이름은 단어 사이의 공백을 없애고, 각 단어의 처음 문자를 모두 대문자로 한다(예:Wikipedia). 
속성과 행동의 이름 또한 마찬가지이지만, 가장 앞 단어의 처음 문자는 소문자로 한다(예:edit()).
**표기법**
![[Pasted image 20240408094123.png]]
![[Pasted image 20240408094154.png]]

### **Generalization(일반화)**
![[Pasted image 20240408121257.png]]
### **Realization(실체화)**
![[Pasted image 20240408121410.png]]

### **Dependency(의존)**
![[Pasted image 20240408121534.png]]

### **Association(연관)**
![[Pasted image 20240408132810.png]]
![[Association.png]]
 
### **Aggregation(집합연관관계)**
![[Pasted image 20240408122254.png]]
![[Aggregation.png]]
### **Composition(합성 또는 복합연관관계)**
![[Pasted image 20240408122635.png]]
![[Pasted image 20240408122653.png]]
![[Composition.png]]


- [9] dependency, association, aggregation, composition  관계


[객체지향](https://effectiveprogramming.tistory.com/category/3.%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%28OOP%29%20%EA%B0%9C%EB%85%90)



## 옵저버 패턴
>📌 `Observer` 패턴은 객체의 상태 변화를 관찰하는 관찰자들에게 통지하는 디자인 패턴이다. 
이 패턴은 일대다 의존성을 가지며, 
한 객체의 상태가 변경되면 그 객체에 의존하는 모든 관찰자에게 자동으로 통지된다. 
주로 이벤트 리스닝, 노티피케이션 시스템 등에 활용된다.

### 1. 자바에서 `Observer` 패턴을 구현하는 기본적인 예제
> 📌 이 예제에서는 `주제(Subject)` 역할을 하는 클래스와 `관찰자(Observer) 인터페이스`, 그리고 구체적인 관찰자 클래스를 만들어 Observer 패턴을 구현한다.

1. **Observer 인터페이스**: 관찰자가 구현해야 하는 인터페이스. `update` 메소드를 통해 상태 변화를 통지받는다.

```java

public interface Observer {
    void update(String message);
}
```

2. **Subject 클래스**: 관찰 대상이 되는 클래스. 관찰자를 등록, 제거하고 상태 변화를 관찰자에게 통지한다.

```java

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }
}
```

3. **ConcreteObserver 클래스**: 구체적인 관찰자를 나타내는 클래스, `Observer` 인터페이스를 구현한다.

```java

public class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received update: " + message);
    }
}
```

4. **사용 예제**: 이제 `Subject` 객체의 상태가 바뀔 때마다 `ConcreteObserver` 객체들이 업데이트를 받는 방식으로 Observer 패턴을 사용.

```java
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        ConcreteObserver observer1 = new ConcreteObserver("Observer 1");
        ConcreteObserver observer2 = new ConcreteObserver("Observer 2");

        subject.attach(observer1);
        subject.attach(observer2);

        subject.setState("New State");
    }
}
```

* 이 예제에서는 `Subject` 클래스가 상태 변화를 갖고, 이 상태 변화를 `ConcreteObserver` 객체들이 통지받아 각자의 `update` 메소드에서 처리하는 구조를 가지고 있다. 
  `Observer` 패턴을 활용함으로써 객체 간의 결합도를 낮추고, 확장성 및 유지보수성을 향상시킬 수 있다.

### 2. `Observer` 패턴에 적용된 `SOLID` 디자인 원칙 분석

SOLID 디자인 원칙은 객체 지향 프로그래밍 및 설계에서 중요한 역할을 합니다. SOLID는 다음과 같은 다섯 가지 원칙의 약자입니다:

#### **단일 책임 원칙(Single Responsibility Principle, SRP)**
* `Obserber` 패턴은 `Subject`와 `Observer`로 구성되며, 이 두 구성 요소는 명확하게 분리된 책임을 가진다.
* `Subject`:  자신의 상태(`Status`) 관리 & 변경 사항이 있을 때 `Observer`에게 통지
* `Observer`: 변경 사항을 받아 적절한 반응을 한다.
#### **개방-폐쇄 원칙(Open-Closed Principle, OCP)**
* `Observer ` 패턴은 `Subject `에 대해 새로운 유형의 `Observers `를 추가할 때 
  `Subject ` 클래스를 수정하지 않고도 확장할 수 있도록 한다.

#### **리스코프 치환 원칙(Liskov Substitution Principle, LSP)**
* `Observer `패턴에서 `Observer` 인터페이스는 모든 구체적인 `Observer` 클래스의 기반이 된다.
  이 인터페이스를 구현하는 모든 클래스는 서로 치환 가능해야 하며, `Subject`는 
  정해진 `Observer` 타입에 의존하지 않고, `interface Observer`를 통해 모든 `Observer` 와
  상호 작용 한다.
#### **인터페이스 분리 원칙(Interface Segregation Principle, ISP)**
* `Observer` 패턴은 `Observer`에게 단 하나의 작업(`Update`)를 정의하는 인터페이스를 제공한다.
  이는 인터페이스 분리 원칙을 따르는 것으로, `Observer`가 필요하지 않은 메서드에 의존하지 않도록 한다. 
  각 `Observer`는 자신에게 필요한 정보만을 제공받음으로써 인터페이스가 최소화되고, 이는 
  더 명확하고 재사용 가능한 디자인을 의미한다.
#### **의존성 역전 원칙(Dependency Inversion Principle, DIP)**
* `Observer` 패턴에서 `Subject`는 정해진(구현된?) 관찰자 클래스에 의존하지 않고, 
  대신 `Observer interface `에 의존한다.
  이로 인해 ==높은 수준의 모듈(`subject`)== 는 ==낮은 수준의 모듈(`ConcreteObserver`)== 에 
  의존하지 않으며, 이는 의존성 역전 원칙을 반영한다.
  `Observer`와 `Subject`사이의 의존성이 인터페이스를 통해 추상화되므로, 
  시스템의 다른 부분을 변경하지 않고도 쉽게 `Observer `나 `Subject `를 수정하거나 
  확장할 수 있다.
  
> 📌 위의 코드에서 ==높은 수준의 모듈== 이란? 
  > 시스템의 핵심 ==비즈니스 로직==이나 ==주요 기능==을 담당하는 부분을 의미한다. 
  > `Observer` 패턴에서는 `Subject `가 이 역할을 수행한다. 
  > 이런경우, 시스템의 다른 부분이 `Subject`에 대한 의존성을 가지는 경우가 많기 때문에 
  > ==높은 수준의 모듈==로 간주된다. 
  > 
  > `Subject`클래스는 구체적인 관찰자 클래스(`ConcreteObserver`)에 직접적으로 의존하지 않는다.
  > 대신, `Observer` 인터페이스를 사용하여 관찰자와의 의존성을 추상화 한다.
  > 이것이 의존성 역전 원칙(`Dependency Inversion Principle`) 의 핵심이다. 
  > 이 원칙은 ==고수준 모듈==이 ==저수준 모듈==에 의존해서는 안 되며, 
  > 둘 다 추상화에 의존해야 한다는 것을 말한다. 
  > `Subject ` 클래스는 `Observer` 인터페이스를 통해 낮은 수준의 모듈(`ConcreteObserver`)
  > 에 대한 의존성을 줄이고, 대신에 인터페이스에 의존하도록 만든다.
  > 
  > 이러한 설계 접근 방식은 시스템의 유연성과 확장성을 향상시킨다. 
  > 새로운 구체적인 `Observer` 클래스를 추가하거나, 기존의 `Observer` 클래스를 수정하더라도, 
  > `Subject` 클래스는 변경되지 않습니다. 이는 시스템의 다른 부분을 변경하지 않고도 
  > `Observer`나 `Subject`를  쉽게 확장하거나 수정할 수 있다는 것을 의미한다.
  > 

### 3. 내가 만든 `Observer` 패턴 예제
> 📌 셀럽을 팔로우하고, 그 셀럽이 새로운 `Post` 를 작성하면 
> 해당 포스트를 `follwer` 들에게 `Update()` 해주는 예제를 만들어 보았다.  

1. `Follower` 인터페이스 : `이름` 과 `post` 를 받는 `update` 메소드를 통해 새로운 `post`를 
   감지한다.

```java
public interface Follower {  
    void update(String name,String post);  
}
```

2. `Famous` 인터페이스 : `Follower` 를 받는 `follow` 메소드와 `unfollow` 메소드를 통해 
   `Follower` 관리를 하고, `notifyFollower()` 메소드로 새 `Post`를 `Follower`들에게 알린다.

```java
public interface Famous {  
    void follow(Follower follower);  
    void unfollow(Follower follower);  
    void notifyFollower();
```

3. `Follower` 을 구체적으로 구현한 `Common` 클래스 : `생성자`를 통해 `String name`을 받고,
   팔로우한 셀럽의 포스트가 새로 올라오면 각자의 `update` 메소드로 처리한다. 

```java
public class Common implements Follower{  
    private String name;  
  
    public Common(String name) {  
        this.name = name;  
    }  
  
    @Override  
    public void update(String CelebrityName ,String post) {  
        System.out.println("new Post : " + CelebrityName + " -> " + name + " : " + post);  
    }  
}
```

4. `Famous`를 구체적으로 구현한 `Celebrity` 클래스 : `follow()`와 `unfollow()` 메소드로 
   `List<Follower> followers` 를 관리하며, `newPost(String post)` 메소드로 
   새 Post를 작성하게 되면, `notifyFollower` 메서드를 호출하여 
   `followers` 를 `forEach`로 순환하며 각각의 `Common` 객체의 `update()` 메서드를 실행한다.

```java
public class Celebrity implements Famous {  
    private String name;  
    private String post;  
    private List<Follower> followers = new ArrayList<>();  
  
    public Celebrity(String name) {  
        this.name = name;  
    }  
  
    @Override  
    public void follow(Follower follower) {  
        followers.add(follower);  
    }  
  
    @Override  
    public void unfollow(Follower follower) {  
        followers.remove(follower);  
    }  
    @Override  
    public void notifyFollower(){  
        followers.forEach(i->i.update(this.name, this.post));  
    }  
  
    public void newPost(String post){  
        this.post = post;  
        notifyFollower();  
    }  
}
```

5. **사용 예제** : 셀럽으로 `일론 머스크` 를 생성하고, 
   `Arrays.asList(new Common(String name)..).forEach(elonMusk::follow)`를 통해
   새로운 팔로워들을 일론 머스크의 `follows 리스트`에 추가하였다.
   그 후, `elonMusk`의 `newPost`메소드를 사용하여 작동을 확인한다.

```java
public class Driver {  
    public static void main(String[] args) {  
        Celebrity elonMusk = new Celebrity("Elon Musk");  
  
        Arrays.asList(new Common("Park"),  
                new Common("Lee"),  
                new Common("John"))  
                .forEach(elonMusk::follow);  
  
        elonMusk.newPost("화성 갈끄니까~~~");  
    }  
}
```

6. 결과

```
new Post : Elon Musk -> Park : 화성 갈끄니까~~~
new Post : Elon Musk -> Lee : 화성 갈끄니까~~~
new Post : Elon Musk -> John : 화성 갈끄니까~~~

Process finished with exit code 0
```

7. 내가 만든 예제의 클래스 다이어그램

```plantuml
class Common{
	- String name;
	..constructor..
	+ Common(String)
	..method..
	+ void update(String, String)
}
interface Follower{
	+ void update(String, String)
}
interface Famous{
	+ void follow(Follower);
	+ void unfollow(Follower);
}
class Celebrity{
	- String name
	- String state
	- List<Follower> followers
	..constructor..
	+ Celebrity(String)
	..method..
	+ follow(Follower)
	+ setState(String)
	+ unfollow(Follower)
	+ notifyFollower()
}
class Driver{
	+ void main(String[])
}
Driver ..> Common : <<create>>
Driver ..> Celebrity : <<create>>
Celebrity "1" *--|> "*" Follower : follwers
Celebrity ..|> Famous
Common..|>Follower
```

![[Pasted image 20240408204459.png]]