복습 키워드 : `제네릭` 
오늘 키워드 : 컬렉션즈프레임워크 , 스트림

자바_서브노트_ :  
이것이 자바다 연습문제 ,  자바의 정석 연습문제 or 종이책 연습문제

디자인 패턴 -> 추천 (헤드퍼스트 디자인 패턴)

---
# Collections 
## 컬렉션이란란 무엇인가? 
`컬렉션즈 프레임워크`란 컬렉션들을 조작하고 표현하기 위한 하나로 통합된 아키텍쳐이다. 
* **인터페이스:** 컬렉션을 나타내는 추상 데이터 유형이다. 인터페이스를 사용하면 표현의 세부 사항과 관계없이 컬렉션을 조작할 수 있다. 객체 지향 언어에서 인터페이스는 일반적으로 계층 구조를 형성한다.
* **구현:** 컬렉션 인터페이스의 구체적인 구현이다. 본질적으로 재사용 가능한 데이터 구조다.
* **알고리즘:** 컬렉션 인터페이스를 구현하는 개체에 대해 검색 및 정렬과 같은 유용한 계산을 수행하는 방법이다. 알고리즘은 _다형성_ 이라고 한다 . 즉, 적절한 컬렉션 인터페이스의 다양한 구현에서 동일한 방법을 사용할 수 있다. 본질적으로 알고리즘은 재사용 가능한 기능이다.

## 컬렉션즈 프레임워크의 장점 
* **프로그래밍 노력 감소:** 컬렉션 프레임워크는 유용한 데이터 구조와 알고리즘을 제공함으로써 
  프로그램 작동에 필요한 하위 수준의 설계가 아닌 프로그램의 핵심 로직에 집중할 수 있도록 해준다. 
  `Java Collections Framework`는 관련되지 않은 API 간의 상호 운용성을 촉진함으로써 
  API를 연결하기 위해 어댑터 객체나 변환 코드를 작성할 필요가 없도록 해준다.
* **프로그램 속도 및 품질 향상:** 이 컬렉션 프레임워크는 유용한 데이터 구조 및 알고리즘의 고성능, 고품질 구현을 제공한다. 
  각 인터페이스의 다양한 구현은 상호 교환이 가능하므로 컬렉션 구현을 전환하여 프로그램을 쉽게 조정할 수 있다. 
  자신만의 데이터 구조를 작성하는 번거로움에서 벗어나 프로그램의 품질과 성능을 개선하는 데 더 많은 시간을 할애할 수 있다.
* **관련되지 않은 API 간의 상호 운용성을 허용** 컬렉션 인터페이스는 API가 컬렉션을 앞뒤로 전달하는 데 사용되는 언어다. 
  내 네트워크 관리 API가 노드 이름 모음을 제공하고 GUI 도구 키트에 열 제목 모음이 필요한 경우 API는 독립적으로 작성되었더라도 원활하게 상호 운용됩니다.
* **새로운 API를 배우고 사용하는 데 드는 노력 감소** 많은 API는 자연스럽게 입력에 대한 컬렉션을 가져와서 출력으로 제공한다. 
  과거에는 이러한 각 API에는 컬렉션을 조작하는 데 사용되는 작은 하위 API가 있었다. 
  이러한 임시 컬렉션 하위 API 사이에는 일관성이 거의 없었기 때문에 처음부터 하나씩 배워야 했고, 사용할 때 실수하기 쉬웠다. 
* **새로운 API를 설계하는 데 드는 노력 감소** 이는 이전 장점의 반대 측면이다. 
  디자이너와 구현자는 컬렉션에 의존하는 API를 만들 때마다 바퀴를 다시 만들 필요가 없다. 
  대신 표준 컬렉션 인터페이스를 사용할 수 있다.
* **소프트웨어 재사용 촉진:** 표준 컬렉션 인터페이스를 준수하는 새로운 데이터 구조는 본질적으로 재사용이 가능하다. 
  이러한 인터페이스를 구현하는 객체에서 작동하는 새로운 알고리즘도 마찬가지다.

# 컬렉션(Collection) 프레임워크란?

다수의 객체를 저장하고 효율적으로 추가, 삭제, 검색할 수 있도록 구현된 인터페이스와 클래스들을 말한다. 

주요 인터페이스로 `List`, `Set`, `Map`이 있다.

> 배열도 다수의 객체를 저장할 수 있다. 하지만, 저장할 수 있는 크기가 고정적이며, 중간 인덱스의 자료를 삭제했을 때 빈 곳이 생기기도 한다. 이로 인해 고정적 크기의 연속된 객체를 저장하는 것은 좋지만, 유동적인 크기를 갖는 객체 저장에는 적합하지 않을 수 있다.

> 컬렉션이란? 여러 가지 자료 구조 개념을 자바 프로그래밍에서 쉽게 사용할 수 있도록 
> 미리 클래스로 만들어 제공하는 기능이다. 

> `Iterator` , `Enumeration` 사용시 통일감을 제공한다. 

### List

#### 특징

* 순서를 유지하고 저장한다.
* 중복 저장이 가능하다.

#### 구현 클래스

* `ArrayList`
* `Vector` _(Thread safe)_
* `LinkedList`

> `ArrayList`와 `Vector` 는 구조적으로 유사함
> `LinkedList`는 서로 떨어져 있어도 서로간의 주소를 가지고 있다. 
> 하나를 넣고 빼고 할 때 이점이 있다. (사이에 들어갈때 상호 주소를 끊고 다시 연결)
### Set

#### 특징

* 순서를 유지하지 않고 저장한다.
* 중복 저장이 불가능하다. -> 중복 자동제거 

#### 구현 클래스

* `HashSet`
* `TreeSet` _(Binary Tree)_

### Map

#### 특징

* 키와 값의 쌍으로 저장됨
* 키는 중복 저장 안됨

#### 구현 클래스

* `HashMap`
* `HashTable` _(Thread safe)_
* `TreeMap` _(Binary Tree)_
* `Properties` _(Child of HashTable)_

## List 컬렉션

* 객체를 인덱스로 관리한다.  
   * 인덱스는 자동 부여된다.  
   * 인덱스로 객체를 검색, 삭제하는 기능을 제공한다.
* 객체 자체를 저장하는 것이 아니라 각 인덱스에 객체의 주소를 저장한다.
* `ArrayList`, `Vector`, `LinkedList`등이 있다.

### 추상 메소드

#### 객체 추가

* `boolean add(E e)`: 주어진 객체를 맨 끝에 추가한다.
* `void add(int index E element)`: 주어진 인덱스에 객체를 추가한다.
* `E set(int index, E element)`: 주어진 인덱스에 있는 저장된 객체를 주어진 객체로 바꾼다.

#### 객체 검색

* `boolean contains(Object o)`: 주어진 객체가 저장되어 있는지 여부를 확인한다.
* `E get(int index)`: 주어진 인덱스에 저장된 객체를 리턴한다.
* `boolean isEmpty()`: 컬렉션이 비어있는지 조사한다.
* `int size()`: 저장되어 있는 전체 객체의 수를 반환한다.

#### 객체 삭제

* `void clear()`: 저장된 모든 객체를 삭제한다.
* `E remove(int index)`: 주어진 인덱스에 저장되어 있는 객체를 삭제한다.
* `boolean remove(Object o)`: 주어진 객체를 삭제한다.

### ArrayList 구현체

* `new ArrayList<T>()`처럼 생성자로 생성이 가능하다.  
   * 초기 용량은 10이다.  
   * 자바 5 이후부터 제네릭 타입을 사용했다.
* 용량이 늘어나는 유연한 배열로 생각하면 된다.  
   * 인덱스 0부터 차례로 객체가 저장된다.

> 리스트 중간에 데이터를 추가, 삭제하는 경우, 해당 인덱스를 기준으로 데이터가 밀려나거나 당겨지는 현상이 일어난다. 중간 데이터가 삭제되거나 추가되는 일이 많은 경우, `LinkedList`를 사용하면 성능상의 이득을 볼 수 있다.  
> 그러나 인덱스 검색이 일어날 시, 혹은 맨 마지막에만 데이터가 추가되는 경우에는 `ArrayList`가 유리하다.

#### Array.asList(T... a)

`Array.asList(T... a)` 메소드는 배열을 리스트로 만들 때 용이하다. 해당 메소드의 인자 값에 배열을 주면 해당 데이터를 가지는 `ArrayList`가 반환된다.

```java
public class AsListTest {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(new Integer[]{1, 2, 3, 4}); // 혹은 Arrays.asList(1, 2, 3, 4);

        for (Integer integer : integerList) {
            System.out.println("integer = " + integer);
        }
    }
}
```

![](https://proxy-prod.omnivore-image-cache.app/0x0,sge6wejYm3RuvabmnPunjAecexSkVYstezoq4Npc-Apg/https://velog.velcdn.com/images%2Fjakeseo_me%2Fpost%2F2d376e9c-ae89-4795-88b3-c2141ed16164%2Fimage.png)

내부 구현을 보면, `new ArrayList<>(a)`의 결과를 반환하는 것을 볼 수 있다.

### Vector 구현체

* 기본적으로 `ArrayList`와 동일한 내부 구조를 갖는다.
* 유일한 차이점은 **스레드 환경에서 안전하게 객체를 추가, 삭제할 수 있다는 것**이다.  
   * 이러한 특성을 _Thread Safe_ 하다고 한다.  
   * 동기화된(synchronized) 메소드로 구성되어 있기 때문에 가능하다.

### LinkedList 구현체

* `ArrayList`와 다르게 내부 배열에 객체를 저장해서 인덱스로 관리하는 것이 아니라, 인접 참조를 링크해서 체인처럼 관리한다.
* 중간 인덱스의 데이터를 제거하거나 추가할 때, 앞 뒤 링크만 변경되고 나머지 링크는 변경되지 않는다.  
   * 이러한 이유 때문에 중간 인덱스에서 잦은 추가, 삭제가 일어날 때는 `LinkedList` 구현체가 유리하다.

## Set 컬렉션

* **중복 데이터가 저장되지 않는다.**
* **순서를 보장하지 않는다.**
* `HashSet`, `LinkedSet`, `TreeSet` 등이 있다.
* 인덱스를 관리하지 않기 때문에, 인덱스를 매개값으로 갖는 메소드가 없다.

### 추상 메소드

#### 객체 추가

* `boolean add(E e)`: 주어진 객체를 저장한다. 성공 `true` 실패 `false`

#### 객체 검색

* `boolean contains(Object o)`: 주어진 객체가 저장되어있는지 확인한다.
* `boolean isEmpty()`: 컬렉션이 비어있는지 조사한다.
* `Iterator<E> iterator()`: 저장된 객체를 한번씩 가져오는 반복자(`Iterator` 객체)를 반환한다.
* `int size()`: 저장되어 있는 전체 객체 수를 리턴한다.

> `Set` 컬렉션에는 인덱스로 객체를 검색하여 가져오는 메소드가 없다. 대신 `Iterator`객체를 반환하는 메소드가 있고, `Iterator` 객체에서는 `hasNext()`, `next()`, `remove()`를 제공한다.

> 단순 반복을 하고 싶다면 `Iterator`를 쓰지 않고 향상된 `for`문을 이용한 조회도 가능하다.  
> `for(E element : set) { ... }`

#### 객체 삭제

* `void clear()`: 저장된 모든 객체를 삭제한다.
* `boolean remove(Object o)`: 주어진 객체를 삭제한다.

## HashSet

`HashSet`은 동일 객체를 판단할 때, `hashCode()` 메소드를 통해 해시 코드를 얻어낸 뒤에 이미 저장된 객체들의 해시코드와 비교하고, 동일한 해시코드가 있다면, `equals()` 메소드로 두 객체를 비교해서 `true`가 나오면 마침내 동일한 객체로 판단한다.

### 자바에서 `hashCode`,`equals` 메서드를 오버라이딩 해야하는 이유
`HashCode `와  `equal` 메서드를 Java 에서 오버라이딩하는 것은 객체의 동등성 비교와 해시 기반 컬렉션에서 객체를 올바르게 관리하기 위해 매우 중요하다. 

### `equals ` 메서드 오버라이딩의 이유
* **정확한 동등성 비교**: 객체의 내용이 같은지 비교하기 위해선 `equal ` 메서드를 오버라이딩해야 한다.
  이를 통해 두 객체의 상태(필드 값)가 같은지를 비교할 수 있어, 논리적 동등성을 구현할 수 있다.

### `hashcode` 메서드 오버라이딩의 이유
* **해시 기반 컬렉션의 올바른 동작**: - `HashSet`, `HashMap`, `Hashtable`과 같은 해시 기반 컬렉션은 객체를 저장하고 검색할 때 `hashCode` 메서드를 사용한다.
   `equals` 메서드를 오버라이딩하고 `hashCode` 메서드를 오버라이딩하지 않으면, 동일한 내용의 객체임에도 불구하고 다른 해시 코드를 반환하여 같은 객체로 인식되지 않을 수 있다. 
  이는 해시 기반 컬렉션에서 예상치 못한 동작을 초래할 수 있다..
- **규약 준수**: `Object` 클래스의 문서에 따르면, `equals` 메서드가 같다고 판단하는 두 객체는 반드시 같은 해시 코드를 반환해야 한다(`hashCode`의 일관된 반환 값). 
  이러한 규약을 지키기 위해서는 `equals`를 오버라이딩할 때 `hashCode`도 함께 오버라이딩해야 한다.


### 요약
- `equals` 메서드를 오버라이딩하면 객체의 논리적 동등성을 정확하게 비교할 수 있다.
- `hashCode` 메서드를 오버라이딩하면 해시 기반 컬렉션에서 객체를 올바르게 관리할 수 있으며, `equals` 메서드와의 일관성을 유지할 수 있다.
- 둘 다 오버라이딩하지 않으면 기본 동작만 사용되어 예상치 못한 결과를 초래할 수 있다.

> Java에서 `hashCode`와 `equals` 메서드를 오버라이딩하는 예시를 살펴본다. 
> `Person` 클래스가 있고, 이 클래스의 객체들이 이름과 나이를 기반으로 동등성을 판단해야 한다고 하자.

### 예제 코드

* Person 객체

```java
public class Person {
    private String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("equals method called");

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        System.out.println("hashCode method called");

        return Objects.hash(name, age);
    }
}
```

* main 함수

```java
public class HashSetTest {
    public static void main(String[] args) {
        Person person1 = new Person("제이크", 30);
        Person person2 = new Person("제이크", 30);

        System.out.println("person1 = " + System.identityHashCode(person1));
        System.out.println("person2 = " + System.identityHashCode(person2));
        System.out.println(person1 == person2);

        Set<Person> personSet = new HashSet<>();

        personSet.add(person1);
        personSet.add(person2);

        int size = personSet.size();
        System.out.println("size = " + size);
    }
}
```

* 결과

![](https://proxy-prod.omnivore-image-cache.app/0x0,sgPlXxHAikiQXXAw8eQycEtKOS3_pInNbyjj4F04uDNE/https://velog.velcdn.com/images%2Fjakeseo_me%2Fpost%2F1c2ec9aa-b5a7-4d04-94cd-d11e145031c2%2Fimage.png)

비록 두 개의 객체가 다른 `identityHashCode`를 갖고 있지만, `.hashCode()` 메소드의 결과가 같고 `.equals()` 메소드의 결과가 `true`라서 둘은 `HashSet` 내부에서 같은 값이라고 인식된다.

### `equals` 메서드 오버라이딩

* `equals` 메서드는 인자로 받은 객체와 현재 객체가 동일한지 비교합니다. 이 예제에서는 `name`과 `age` 필드가 둘 다 같을 때 두 `Person` 객체를 동일하다고 판단한다.

### `hashCode` 메서드 오버라이딩

* `hashCode` 메서드는 객체의 해시 코드를 반환한다. 이 예제에서는 `name`의 해시 코드와 `age` 값을 사용하여 계산된 고유한 정수를 반환한다.
* `hashCode` 메서드는 `equals` 메서드와 일관되게 구현되어야 하며, `equals` 메서드가 두 객체를 같다고 판단하면, 이들 객체는 반드시 같은 해시 코드를 반환해야 한다.

이러한 방식으로 `hashCode`와 `equals` 메서드를 오버라이딩하면, `Person` 객체들이 `HashSet`, `HashMap`, `Hashtable` 같은 해시 기반 컬렉션에서 올바르게 동작하게 된다. 예를 들어, `HashSet`에 `Person` 객체를 추가할 때, `hashCode` 메서드로 해시 코드를 계산하고, 이미 같은 해시 코드를 가진 객체가 존재한다면 `equals` 메서드로 두 객체가 실제로 동일한지 확인한다.



## Map 컬렉션

* `Map` 컬렉션은 `키(key)`와 `값(value)`로 구성된 `Entry` 객체를 저장하는 구조를 가지고 있다.
* 키, 값은 모두 객체이다.
* 키는 중복이 안되며, 값은 중복이 된다.
* `HashMap`, `HashTable`, `LinkedHashMap`, `Properties`, `TreeMap` 등이 있다.

### 추상 메소드

#### 객체 추가

* `V put(K key, V value)`: 주어진 키로 값을 저장한다. 새로운 키일 경우 `null`을 리턴하고, 이전에 있던 키에 새로운 값을 덮어 씌우는 경우 이전에 있던 값을 리턴한다.

#### 객체 검색

* `boolean containsKey(Object key)`: 주어진 키가 있는지 여부를 파악한다.
* `boolean containsValue(Object value)`: 주어진 값이 있는지 여부를 파악한다.
* `Set<Map.Entry<K, V>> entrySet()`: 키와 값의 쌍으로 구성된 모든 `Map.Entry` 객체를 `Set`에 담아서 반환한다.
* `V get(Object key)`: 주어진 키가 있는 값을 리턴한다.
* `boolean isEmpty()`: 컬렉션이 비어있는지 여부를 확인한다.
* `Set<K> keySet()`: 모든 키를 `Set` 객체에 담아서 리턴한다.
* `int size()`: 저장된 키의 총 수를 리턴한다.
* `Collection<V> values()`: 저장된 모든 값을 `Collection`에 담아서 리턴한다.

#### 객체 삭제

* `void clear()`: 모든 `Map.Entry` 객체를 삭제한다.
* `V remove(Object key)`: 주어진 키와 일치하는 `Map.Entry`를 삭제하고 값을 반환한다.

### 반복 방법

* `ketSet()` 메소드로 모든 키의 `Set`을 얻고 `.get()` 메소드로 모든 키를 조회해보면 된다.
* `.entrySet()` 메소드로 모든 `Map.Entry`의 `Set`을 얻고 `.iterator()` 메소드를 통해서 반복자(`Iterator`)를 얻은 후 돌려보면 된다.

### HashMap

* 키 타입의 객체가 있다면, `hashCode()`와 `equals()` 메소드를 둘 다 확인 후에 같은 객체라고 판단한다.
* 키와 값은 primitive type이 올 수 없다.  
   

### HashTable

* `HashMap`과 동일한 내부구조를 갖고 있다.
* 동기화된 (`synchronized`) 메소드를 갖고 있어서, 멀티 스레드 환경에서 안전하게 객체를 추가, 삭제 가능하다. _(thread safe)_
* 현재 잘 사용하지 않는다.
### Properties

* `HashTable`의 하위 클래스이다.
* 키와 값을 `String`이라는 타입으로만 제한한 클래스이다.
* 보통 애플리케이션의 옵션 정보, 데이터베이스 연결 정보, 다국어 정보 등이 저장된 프로퍼티 파일(`.properties`)을 읽을 때 주로 사용한다.  
 *  프로퍼티 파일(`.properties`)은 키와 값이 `=`기호로 연결된 텍스트 파일로 `ISO-8859-1` 문자셋으로 저장된다. 이 문자셋으로 저장할 수 없는 한글은 유니코드로 변환되어 저장된다.
* 프로퍼티 파일을 읽기 위해서는 `Properties` 객체를 생성 후에 `.load()` 메소드를 이용하면 된다. `.load()` 메소드는 프로퍼티 파일로부터 데이터를 읽기 위해 `FileReader` 객체를 매개값으로 받는다.

## 검색기능을 강화시킨 컬렉션

* `TreeSet`
* `TreeMap`

위 두 컬렉션은 이진트리_(binary tree)_를 이용해서 계층적 구조를 가지도록 객체를 저장한다.

### 이진 트리 구조란?

부모 자식 관계를 갖고 위에서 아래로 뻗어나가는 트리 구조인데, 특수한 특성을 띈 트리 구조이다. 트리 구조란 것은 그냥 상위 노드에 하위 노드가 붙어있는 형태이고, 상위에서 하위 데이터 접근이 가능하다. 보통 시작점인 루트 노드에 여러 자식노드가 붙으면서 시작된다.

![](https://proxy-prod.omnivore-image-cache.app/0x0,sVzs35OcpbJrNPPY_o8GUlYNOGmLWZCE-WjgbTUdo1bI/https://velog.velcdn.com/images%2Fjakeseo_me%2Fpost%2F9f10aaef-b836-48b0-91d4-c46e36d584ee%2Fimage.png)

위와 같이 루트노드로부터 시작되어 하위에 연결된 다른 노드들이 있는 구조이다. 위와 같은 트리 노드 구조에서 이진 트리는 아래와 같은 조건을 만족하는 트리 구조를 말한다.

#### 이진트리의 조건

* 한 부모 노드의 자식 노드는 최대 2개까지만 붙는다.
* 부모 노드의 값보다 작은 자식은 왼쪽에 붙는다.
* 부모 노드의 값보다 큰 자식은 오른쪽에 붙는다.

이진 트리의 조건을 만족하면 다음과 같은 특성을 갖게 된다.

![](https://proxy-prod.omnivore-image-cache.app/0x0,srowRUnUgb6CTuR_-RGn5q3V32hRzT6NPRf3lW10qofU/https://velog.velcdn.com/images%2Fjakeseo_me%2Fpost%2F7cad01cf-fb9f-47c8-93dc-5021d597772d%2Fimage.png)

> 이진트리의 조건을 충족하면, 큰 값을 찾거나 작은 값을 찾기 매우 쉽다.

> 핵심은 데이터의 삽입, 삭제 등의 과정을 거쳤을 때도 규칙을 깨지 않고, **위와 같은 규칙을 지킴으로써 내가 원하는 데이터를 빠르게 검색하려는 데에 있다.**

#### 이진트리의 약점

* 밸런스가 맞지 않을 수 있다.

![](https://proxy-prod.omnivore-image-cache.app/0x0,shAeqMqNNT1GfvPCJGoBcK0AeQA2IEZs1QNFogEBrJRA/https://velog.velcdn.com/images%2Fjakeseo_me%2Fpost%2F87672cde-951f-438a-a3da-0ec4e0e721cb%2Fimage.png)

위와 같은 노드가 있을 때, 위는 완벽히 밸런스가 맞진 않지만, 꽤나 밸런스가 맞는 이진트리이다. 하지만, 가장 큰 값을 찾으려 오른쪽 노드로 계속 향했을 때 나오는 값은 `11`이 아닌 `9`이다.

![](https://proxy-prod.omnivore-image-cache.app/0x0,s2tz_-2zBhfOhdI7JqsdP0lEGa9q1IIbYn2uMZzYBuK4/https://velog.velcdn.com/images%2Fjakeseo_me%2Fpost%2Fde3ec5b3-9893-4750-b6bd-9dadf94a42bf%2Fimage.png)

위와 같은 경우에도 검색을 빠르게 하려는 이진 트리의 목적에 위배되는 구조이다.

#### 이진트리 약점 극복

이를 위해 `TreeMap`과 `TreeSet`은 이진트리에서 더 업그레이드된 레드-블랙 트리를 사용한다.

> 참고 링크: [레드블랙트리 정리](https://velog.io/@jakeseo%5Fme/%EC%9D%B4%EA%B2%83%EC%9D%B4-%EC%9E%90%EB%B0%94%EB%8B%A4-%EC%B6%94%EA%B0%80-%EC%A0%95%EB%A6%AC-%EB%A0%88%EB%93%9C%EB%B8%94%EB%9E%99-%ED%8A%B8%EB%A6%AC-%EC%A0%95%EB%A6%AC)

### TreeSet

* 이진트리를 기반으로 한 `Set` 컬렉션이다.
* 객체 저장 시 자동으로 부모 값보다 낮은 값은 왼쪽 자식 노드에 높은 값은 오른쪽 자식 노드에 정렬된다.

`TreeSet`은 `Set` 인터페이스가 제공하는 메소드와 별개로 자신만의 메소드를 가지고 있으므로, `TreeSet` 타입으로 선언해야 할 때가 있다.

* `first()`: 제일 낮은 객체를 리턴
* `last()`: 제일 높은 객체를 리턴
* `pollFirst() or pollLast()`: 제일 높거나 낮은 객체를 꺼내오고 컬렉션에서 제거
* `floor(E e) or ceiling(E e)`: 동등한 객체가 없다면 높은 혹은 동등한 객체가 없다면 낮은 객체를 가져옴 동등한 게 있으면 동등한 걸 가져옴

### NevigableSet

* `TreeSet`의 데이터를 반환받을 때, `TreeSet.descendingSet()` 등의 메소드를 이용하면 `NevigableSet`을 반환받을 수 있다.  
   * 정렬등에 유연하게 구성할 수 있는 `Set`이다.  
         * `NevigableSet`에서 `.descendingSet()`을 한번 더 호출하면 오름차순으로 구성된다.

### TreeMap

* 이진트리를 기반으로 한 `Map` 컬렉션이다.
* 키(`key`)와 값(`value`) 중에 키(`key`)를 기준으로 정렬한다.
* `TreeSet`처럼 `.descendingKeySet()` 이나 `.descendingMap()`을 통해 `NavigableKeySet`이나 `NavigableMap`을 얻을 수 있다.

## Comparable과 Comparator

* `TreeSet`과 `TreeMap`은 정렬을 위해 `java.lang.Comparable`을 구현한 객체를 요구한다.
* 사용자 정의 클래스도 `Comparable`을 구현하면 자동 정렬이 된다.

`Comparable`을 상속하면, `int compareTo(T o)` 메소드를 구현해주면 된다.

* 주어진 객체보다 크면 1(양수)을 리턴
* 주어진 객체와 같으면 0을 리턴
* 주어진 객체보다 작으면 -1(음수)을 리턴

위의 규칙을 따르는 `compareTo()`를 작성하면 오름차순 정렬이 자동으로 된다.

### 나의 예제
```java
public class Driver {  
    public static void main(String[] args) {  
        int numberOfCustomer = (int)(Math.random()*50+100);  
        String[] nameList = {"Kim", "Park", "Lee", "Choi"};  
        List<String> list = new ArrayList<>();  
        for(int i = 0; i < numberOfCustomer; i++){  
            list.add(nameList[(int)(Math.random()*4)]); 
            // list 안에 kim, park, Lee 랜덤 저장  
        }  
  
        HashMap<String, Integer> hashMap = new HashMap<>();  
        for(String str : list){  
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);  
            // -> 해시맵에 키가 이미 존재하면 value += 1 || 0(default) += 1        }  
        // 빈도수를 해시맵에 키 : 밸류 형태로 저장  
  
        List<Map.Entry<String, Integer>> sortedlist = new ArrayList<>(hashMap.entrySet());  
        sortedlist.stream()  
                .max(Comparator
					 comparing(Map.Entry::getValue))  
                .ifPresent(i->System.out.println("방문한 손님중 가장 많은 성씨는 " + i.getKey() + "입니다.\n"));  
        //가장 많이 나온 성씨를 출력(최빈값)  
  
        sortedlist.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));  
        //과연 그럴까? 내림차순 정렬로 확인  
  
        sortedlist
        .forEach(i-> System.out.println(i.getKey() + " " + i.getValue()));  
    }  
}
```


## `Iterator` 와 `Enumeration` 
자바에서 `Iterator`와 `Enumeration`은 컬렉션 내의 요소들을 순회하기 위한 인터페이스입니다. 이 둘은 비슷한 목적을 가지고 있지만, 사용 방법과 기능적인 면에서 차이가 있습니다.

### Enumeration

- `Enumeration` 인터페이스는 자바 초기 버전에서 소개되었다.
- `hasMoreElements()`와 `nextElement()` 메소드를 제공하여 컬렉션의 요소들을 순회한다.
- `Enumeration`은 오직 읽기 작업만 지원한다. 즉, 요소들을 순회하면서 요소를 제거하거나 변경하는 등의 작업은 지원하지 않는다.
- 주로 구식 코드나 레거시 시스템에서 `Vector`와 `Hashtable` 클래스와 같은 구식 컬렉션 클래스들과 함께 사용된다.

### Iterator

- `Iterator` 인터페이스는 `Enumeration`의 단점을 보완하기 위해 자바 2 버전에서 소개되었다.
- `hasNext()`, `next()`, `remove()` 메소드를 제공하여 컬렉션의 요소들을 순회하고, 필요한 경우 요소를 제거할 수 있다.
- `Iterator`는 `Enumeration`에 비해 더 많은 기능을 제공하며, `Collection` 인터페이스를 구현하는 모든 자바 컬렉션 클래스와 함께 사용된다.
- 자바 5부터는 `for-each` 루프가 도입되어 컬렉션의 요소를 더욱 쉽게 순회할 수 있게 되었다. 이 때 내부적으로 `Iterator`를 사용한다.

### 차이점 요약

- **기능성:** `Iterator`는 요소 제거 기능을 포함하여 더 많은 기능을 제공한다.
- **사용성:** `Iterator`는 `Enumeration`보다 더 널리 사용되며, 모든 컬렉션 클래스와 함께 사용할 수 있다.
- **역사:** `Enumeration`은 더 오래된 인터페이스이며, 주로 구식 컬렉션 클래스와 함께 사용다.

대부분의 현대 자바 코드에서는 `Enumeration`보다는 `Iterator`를 사용하는 것이 권장된다.

### Enumeration 예제
```java

import java.util.Enumeration;
import java.util.Vector;

public class EnumerationExample {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<Integer>();

        // 요소 추가
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);

        // Enumeration을 이용한 요소 순회
        Enumeration<Integer> enumeration = vector.elements();
        while(enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }
    }
}
```

### Iterator 예제
```java
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        // 요소 추가
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // Iterator를 이용한 요소 순회 및 조건에 맞는 요소 제거
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer number = iterator.next();
            if(number % 2 == 0){ // 짝수인 요소 제거
                iterator.remove();
            }
        }

        // 남은 요소 출력
        System.out.println(list);
    }
}
```


## 동기화된 컬렉션 만들기

앞에 언급됐던 컬렉션 중 `Vector`와 `HashTable`은 동기화된 메소드로 구성되어 멀티스레드환경에서 안전하지만, `ArrayList`, `HashSet`, `HashMap`은 `synchronized` 메소드로 구성되지 않아 멀티 스레드 환경에서 안전하지 않다.

컬렉션을 Thread-safe 로 만드려면, `Collections.synchronizedXxx()` 메소드를 이용하면 된다.

## 병렬처리 컬렉션 만들기 (동기화 컬렉션의 한계)

동기화된 (`synchronized`) 컬렉션은 스레드 환경에서 안전은 보장하지만, 스레드가 하나의 요소를 처리할 때 잠금이 발생하기 때문에 대기시간이 발생하고 그래서 빠른 속도를 보장하진 못한다. 

이러한 문제를 해결하기 위해 자바에서는 멀티 스레드가 컬렉션의 요소를 병렬적으로 처리할 수 있도록 특별한 컬렉션을 제공한다. `ConcurrentHashMap`과 `ConcurrentLinkedQueue`이다.

위 컬렉션은 부분(segment) 잠금을 이용하여, 처리하는 요소가 포함된 부분만 잠금하고 나머지 부분은 다른 스레드가 변경할 수 있도록 한다.

```java
Map<K, V> map = new ConcurrentHashMap<K, V>();
```

```java
Queue<E> queue = new ConcurrentLinkedQueue<E>();
```

> `ConcurrentLinkedQueue`는 락-프리 알고리즘을 구현하여, 여러개의 스레드가 동시에 접근해도 잠금을 사용하지 않고 최소한 하나의 스레드가 안전하게 요소를 저장하거나 얻도록 해준다.

---
# `Data Structure`(자료 구조)
**컴퓨터 과학** 에서 데이터 구조는 일반적으로 `데이터`에 대한 효율적인 액세스를 위해 선택되는 
데이터 구성, 관리 및 저장 형식이다. (`format`)

[데이터 구조 연습](https://www.javatpoint.com/data-structure-tutorial)

> 자바의 `콜렉션즈 프레임워크`는  자료구조의 묶음이다.


---
## 메서드 참조
**람다 식**은 기존 메소드를 호출하는 경우 경우에 따라 호출하는 것 외에는 아무 작업도 수행하지 않는 경우가 있다. 
이러한 경우 기존 메서드를 이름으로 참조하는것이 더 명확한 경우가 더 많다. 
메서드 참조를 사용하면 아래와 같은 작업을 수행할 수 있다.

```java
...
appleList.forEach(i -> System.out.println(i));

appleList.forEach(System.out::println);
// 위와 동일한 기능을 수행한다. 
```

---

# Aggregate Operations(`stream`)


자바의 `Aggregate Operations`(집계 연산)은 스트림 API의 핵심 부분으로, 
데이터 컬렉션을 선언적으로 처리할 수 있는 방법을 제공한다. 
이는 함수형 프로그래밍 접근 방식을 사용하여, 데이터를 필터링, 변환, 집계하는 등의 연산을 수행할 수 있게 해준다.

### Aggregate Operations의 주요 특징:

1. **선언적인 처리**: 데이터 처리 방식을 명시적으로 기술할 수 있으며, 어떻게 처리될지보다는 무엇을 할지에 초점을 맞춘다.
2. **내부 반복**: `Aggregate Operations`는 스트림 API를 사용하여 내부 반복을 수행다. 이는 개발자가 명시적으로 반복문을 작성할 필요가 없게 해주어 코드의 가독성과 유지보수성을 향상시킵니다.
3. **함수형 프로그래밍 스타일**: 람다 표현식과 메소드 참조를 사용하여, 간결하고 유연한 방식으로 연산을 정의할 수 있다.

### 주요 연산 종류:

* **필터링(Filtering)**: 특정 조건에 맞는 요소만 선택한다.
* **매핑(Mapping)**: 각 요소에 함수를 적용하여 새로운 요소로 변환한다.
* **리듀싱(Reducing)**: 모든 요소를 단일 결과로 합치는 과정이다.
* **수집(Collecting)**: 결과를 새로운 컬렉션에 수집한다.
* **매칭(Matching)**: 주어진 조건에 부합하는 요소가 있는지 확인한다.
* **정렬(Sorting)**: 스트림의 요소를 정렬한다.
* **집계(Aggregating)**: 요소들의 합계, 평균, 최대값, 최소값 등을 계산한다.


### 나의 예제
```java
public class Main {  
    public static void main(String[] args) {  
  
        int numberOfCustomer = (int) (Math.random() * 50 + 50);  
        String[] nameList = {"Kim", "Park", "Lee", "Choi"};  

//1부터 numberOfCustomer 까지 순환  
        new ArrayList<>(IntStream.rangeClosed(1, numberOfCustomer)    
                .mapToObj(i -> nameList[(int)(Math.random()*numberOfCustomer) % nameList.length])    
                .collect(Collectors.groupingBy(obj -> obj))  
                .entrySet())  
                .stream()  
                .sorted(Comparator.comparingInt(a -> a.getValue().size()))  
                .forEach(a->
                System.out.println(a.getKey() + " " + a.getValue().size()));
    }
}
```