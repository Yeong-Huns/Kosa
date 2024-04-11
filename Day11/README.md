# 스트림이란? 

## 스트림의 특징
자바 프로그래밍 시 , 데이터는 `배열`이나 `컬렉션 클래스`에 저장해서 사용 
-> 빠른 작업을 위해 표준화된 방법으로 데이터를 가져온 후 연산

### `Iterator` 와 `Stream`을 사용하여 출력하는 과정비교
```java
public class StreamTest {  
    public static void main(String[] args) {  
        List<String> fruits = Arrays.asList("apple", "orange", "banana");  
        for (String result : fruits) {  
            System.out.println(result);  
        }  
        //stream 을 사용할 경우   
		List<String> fruits2 = Arrays.asList("apple", "orange", "banana");  
        Stream<String> fruits2stream = fruits2.stream();  
        fruits2stream.forEach(System.out::println);  
    }  
}
```

### 스트림의 장점
* `람다식`을 사용해서 간결하게 요소들을 다룰 수 있다.
* 대용량의 데이터를 병렬로 처리할 수 있다.
* 데이터를 최종 처리하기 전에 여러 가지 중간 처리를 할 수 있다.

---
## 스트림의 종류 
| 리턴 타입           | 데이터 소스  |
| --------------- | ------- |
| Stream\<T>      | 컬렉션     |
| Stream\<T>      | 배열      |
| IntStream       | int 범위  |
| LongStream      | long 범위 |
| Stream\<Path>   | 디렉토리    |
| Stream\<String> | 텍스트 파일  |
| IntStream       | 랜덤 수    |

### 컬렉션에서 스트림 얻기 
```java
public class CollectionToStreamTest{
	public static void main(String[] args){
		List<Student> studentList ＝ new ArrayList<>();
		studentList.add(new Student("홍길동", 2));
		studentList.add(new Student("이순신", 1));
		studentList.add(new Student("박찬호", 2));
		studentList.add(new Student("손흥민", 3));
		studentList.add(new Student("동길동", 4));	
		
		Stream<Student> stream = studentList.stream();
		//-> ArrayList를 Stream으로 리턴한다.
		stream.forEach(i->System.out.println(i.getName()));
	}
}
```

### 배열에서 스트림 얻기
```java
public class ArrayToStreamTest{
	public static void main(String[] args){
		Student[] stdArr = {
			new Student("홍길동", 2),
			new Student("이순신", 1),
			new Student("박찬호", 2),
			new Student("손흥민", 3),
			new Student("동길동", 4)
		}
		Stream<Student> stream = Arrays.stream(stdArr);
		//-> 배열을 스트림 객체로 리턴한다.
		stream.forEach(i->System.out.println(i.getName()));

		int[] intArr = {1,2,3,4,5};
		IntStream intStream = Arrays.stream(intArr);
		intStream.forEach(i->System.out.println(i+ ", "));
		//-> 정수 타입 배열은 IntStream 으로 빠르게 처리할 수 있다.
	}
}
```

### 특정 정수 범위에서 스트림 얻기
```java
IntStream intStream1 = IntStream.range(0, 100) // -> 100이 포함된다.
IntStream intStream2 = IntStream.rangeClosed(0, 100) // -> 100이 포함된다.
```

> 📌 0 ~ 100 범위의 스트림을 얻을 수 있다.

### 랜덤 수에서 스트림 얻기
```java
IntStream intStream1 = new Random().ints(5); 
//-> 5개 임의 정수로 스트림을 얻는다. 

IntStream intStream2 = new Random().ints(1, 50).limit(10); 
//-> 1~50 사이의 정수를 얻은 후, 10개 출력한다.

DoubleStream doubleStream1 = new Random().doubles(5);
//-> 5개 임의의 실수로 스트림을 얻는다.
```

> 📌 결과 

```java
-132217446
-270009177
158943372
2003339194

12, 7, 13, 25, 10, 49, 46 26, 22, 19, //-> limit(10)에 의해 10개만 출력

0.123451231
0.315451231
0.434123131
0.412331332
0.445457131
```

### 파일로부터 스트림 얻기
>📌 `Files` 클래스의 정적 메서드 `lines()`로 파일에서 스트림을 얻을 수 있다.

```java
Path path = Paths.get(FileToStreamTest.class.getResource("Student.txt").toURI());
//-> 파일의 절대경로를 얻고 Path 객체를 얻는다. 
Stream<String> fileStream = Files.lines(path, Charset.defaultCharset());
//-> 운영체제의 기본 인코딩을 지정 후, 파일로부터 스트림을 얻는다.
```

---
## 스트림 파이프 라인
>📌 자바 스트림은 중간 처리와 최종 처리 스트림으로 나눌 수 있다. 

```java
List<Student> studentList = Arrays.asList(
	new Student("kim", 1, 100),
	new Student("lee", 2, 80),
	new Student("park", 3, 70),
	new Student("son", 4, 60)
);
int average = 
studentList.stream()
	.filter(i->i.getGrade == 1)
	.mapToInt(Student::getScore)
	.average();
//-> 1학년 점수 평균

```

1. `Student` 컬렉션에서 스트림을 얻는다.
2. `Stream` 인터페이스의 중간 처리 메서드인 `filter()` 메서드로 학년별로 학생들을 분류해서 
   결과값의 스트림을 얻습니다.
3. `Stream` 인터페이스의 중간 처리 메서드인 `mapToInt()` 메서드로 학생들의 점수로 이루어진 
    스트림을 얻는다.    
4. 최종 처리 메서드인 `average()`메서드로 시험 점수 평균을 구한 후 , 출력한다.

---
## 중간 처리 스트림 메서드
### 필터링 메서드
| 리턴타입         | 메서드                     | 설명               |
| ------------ | ----------------------- | ---------------- |
| Stream       | distinct()              | 중복제거             |
| Stream       | filter(Predicate)       | 조건식을 만족하는 요소만 선택 |
| IntStream    | filter(IntPredicate)    | 조건식을 만족하는 요소만 선택 |
| DoubleStream | filter(DoublePredicate) | 조건식을 만족하는 요소만 선택 |

> 📌 `disltinct()` 와 `filter()` 메서드 사용하기

```java
List<String> fruitList = Arrays.asList("apple", "banana", "mango", "Strawberry", "banana", "mango");

fruitList.stream()
	.distinct() //-> 과일 이름의 중복을 제거
	.forEach(System.out::println);

fruitList.stream()
	.filter(i->i.length() < 6) //-> 문자 개수가 6개보다 적은 과일만 선택
	.forEach(System.out::println);
	
fruitList.stream()
	.distinct()
	.filter(i->i.length() < 6) 
	.forEach(System.out::println);
	//-> 중복 과일 제거 후, 문자 개수가 6개보다 적은 과일만 선택 
```

### 매핑(변환) 메서드
>📌매핑 메서드는 스트림의 요소를 다른 요소들로 변환하는 작업을 수행한다.

### `flatMapXXX()`메서드
>📌`flatMapping` 메서드는 한 개의 요소를 여러 개의 요소로 변환(1: n) 할 때 사용한다.

| 리턴타입         | 메서드                                        | 설명                           |
| ------------ | ------------------------------------------ | ---------------------------- |
| Stream\<R>   | flatMap(Function\<T, Stream\<R>>)          | 객체 T를 Stream\<R>로 변환         |
| DoubleStream | flatMap(DoubleFunction\<DoubleStream>)     | double 타입을 DoubleStream으로 변환 |
| IntStream    | flatMap(IntFunction\<IntStream>)           | int 타입을 IntStream 으로 변환      |
| DoubleStream | flatMapToDouble\<Function\<T,DoubleStream> | 객체 T를 DoubleStream 으로 변환     |
```java
List<String> studentList = Arrays.asList("홍길동 2 80", "이순신 3 77", "손흥민 2 80");
studentList.stream()
	.flatMap(i-> Arrays.stream(i.split(" ")))
	.forEach(System.out::println);
```

### mapXXX() 메서드
>📌`mapXXX` 메서드는 스트림의 요소를 다른 타입의 요소로 변환(1: 1)시 사용한다.
>각각의 데이터 타입을 다른 타입으로 변환하는 더 많은 `mapXXX()`메서드가 API문서에 설명되어 있다.

| 리턴 타입        | 메서드                               | 설명                    |
| ------------ | --------------------------------- | --------------------- |
| IntStream    | mapToInt(ToIntFunction\<T>)       | 객체 T를 int 타입으로 변환     |
| DoubleStream | mapToDouble(ToDoubleFunction\<T>) | 객체 T 를 double 타입으로 변환 |
| LongStream   | mapToLong(ToLongFunction\<T>)     | 객체 T를 long 타입으로 변     |
```java
studentList.stream()
	.mapToInt(Student::getScore) 
	.forEach(System.out::println);
	//-> 각각의 student 객체의 점수만 얻어와서 스트림을 얻는다.
```

### 정렬 메서드 
>📌정렬 메서드는 스트림의 요소를 정렬하는 기능을 제공한다. 
>기본타입이거나 문자열인 경우는 자동으로 정렬하지만, 요소들이 객체타입인 경우 
>`Comparator`를 구현해서 `sorted()` 매서드의 매개변수로 전달해야 한다.

| 리턴 타입        | 메서드                     | 설명                          |
| ------------ | ----------------------- | --------------------------- |
| Stream\<T>   | sorted()                | 스트림 요소들을 정렬                 |
| Stream\<T>   | sorted(Comparator \<T>) | 스트림 요소들을 Comparator 에 따라 정렬 |
| DoubleStream | sorted()                | double 요소들을 오름차순으로 정렬       |
| IntStream    | sorted()                | int 요소들을 오름차순으로 정렬          |
>💡다음은 기본 타입 데이터를 정렬 후 출력하는 예제이다.

```java
List<Integer> scoreList = Arrays.asList(77,67,88,99,100);
scoreList.stream()
	.sorted()
	.forEach(System.out::println);
```

>💡다음은 `Comparator`를 이용해서 `Student` 객체를 시험 점수순으로 정렬해서 출력하는 예제이다.

```java
public class student implements Comparable<Student>{
	private String name; 
	...
	private int Score;

	@Override
	public int compareTo(Student O){
		return Integer.compare(score, O.score);
	}
}

public class TestDriver {
	public static void main(String[] args){
	
	studentList.stream()
		.sorted() //-> 시험 점수를 오름차순 정렬
		.forEach(i->System.out.println(i.getScore()));

	studentList.stream()
		.sorted(Comparator.reverseOrder()) //-> 시험 점수를 내림차순 정렬
		.forEach(i->System.out.println(i.getScore()));
	}
}
```

>📌`Comparable` 인터페이스를 구현한 `Student` 객체의 `sorted()` 메서드 호출 시 `compareTo()`
>메서드에 구현한 대로 시험 점수순으로 `Student` 객체를 정렬하는 예시이다.

### 루핑 메서드
>📌 중간 처리 스트림에서는 `peek()` 메서드를 이용하면 요소 전체에 반복 작업을 할 수 있다.

```java
fruitList.stream()
	.distinct()
	.filter(i->i.length() < 8)
	.peek(a->System.out.println(a)); //-> 단독 실행 X

fruitList.stream()
	.distinct()
	.filter(i->i.length() < 8)
	.peek(a->System.out.println(a))
	.filter(i->i.startsWith("a"))
	.peek(a->System.out.println(a))
	.forEach(System.out::println);
	//-> 중간 처리 메서드가 리턴하는 스트림의 요소 출력
```

>💡 이외에도 `skip()` 이나 `limit()` 같은 중간 처리 메서드를 제공한다.

## 최종 처리 스트림 메서드
### `Optional` 클래스
> 📌 결과값이 `null` 되는 경우를 대비하기 위해서 `Optional` 클래스를 제공한다.
> `Optional` 클래스는 결과값이 `null` 인 경우 디폴트 값을 설정할 수 있다.

| 리턴타입        | 메서드                                              | 설명                   |
| ----------- | ------------------------------------------------ | -------------------- |
| boolean     | isPresent()                                      | 값 저장 유무 판별           |
| T<br>double | orElse(T)<br>orElse(double)                      | 저장된 값이 없는 경우 디폴트값 지정 |
| void        | ifPresent(Consumer)<br>ifPresent(DoubleConsumer) | 지정된 값이 있는 경우 처리      |
>💡 다음은 `Optional` 클래스를 이용해서 3가지 방법으로 `null`을 처리하는 예제이다.

```java
// 첫 번째 방법 (isPresent 사용)
OptionalDouble optional =
	studentList.stream()
	.mapToInt(Student::getScore)
	.average()
	.getAsDouble()

if(optional.isPresent()){ //값이 존재하는 경우
	System.out.println(optional.getAsDouble());
}else{
	System.out.println("시험 점수를 입력해주세요.")
}
// 두 번째 방법 (orElse 사용하기)
double avg = studentList.stream()
	.mapToInt(Student::getScore)
	.average()
	.orElse(0.0) //-> 0.0
// 세 번째 방법(람다식 이용하기)
studentList.stream()
	.mapToInt(Student::getScore)
	.average()
	.ifPresent(System.out::println);
```

### 매칭 메서드
> 📌 `stream`에 조건식을 만족하는 요소가 있는지 판별한다.

| 리턴타입    | 메서드                                   | 설명                                 |
| ------- | ------------------------------------- | ---------------------------------- |
| boolean | allMatch(Predicate\<T> predicate)     | 모든 `Stream`요소들이 모든 조건을 만족하는지 판별    |
| boolean | anyMatch(Predicate\<T> predicate)     | 임의의 `Stream` 요소가 조건을 만족하는지 판별      |
| boolean | nontMatch(Predicate\<T> predicate)    | 모든 `Stream`요소가 조건을 만족하지 않는지 판별     |
| boolean | allMatch(IntPredicate\<T> predicate)  | 모든 `IntStream`요소들이 모든 조건을 만족하는지 판별 |
| boolean | anyMatch(IntPredicate\<T> predicate)  | 임의의 `IntStream` 요소가 조건을 만족하는지 판별   |
| boolean | nontMatch(IntPredicate\<T> predicate) | 모든 `IntStream` 요소가 조건을 만족하지 않는지 판별 |

>💡예제

```java
boolean b = Arrays.stream(strArr)
	.anyMatch(i->i>100);
System.out.println(b);
boolean c = Arrays.stream(strArr)
	.noneMath(i->i>100);
```

### 기본 집계 메서드
> 📌 대량의 데이터 처리 시 , `집계 메서드`를 사용해서 쉽게 구할 수 있다. 
> 이런식으로 대량의 데이터를 가공해서 값을 출력하는 과정을 `리덕션(Reduction)` 이라고 한다.

| 리턴타입                            | 메서드                          | 설명             |
| ------------------------------- | ---------------------------- | -------------- |
| long                            | count()                      | 스트림 요소 수량      |
| Optional                        | findFirst()                  | 스트림 첫 번째 요소    |
| Optional\<T><br>OptionalXXX     | max(Comparator\<T>)<br>max() | 스트림 요소 중 최대 요소 |
| Optional<\T><br>OptionalXXX<br> | min(Comparator\<T>)<br>min() | 스트림 요소 중 최소 요소 |
| OptionalDouble<br>              | average()                    | 스트림 요소 평균      |
| int, long, double               | sum()                        | 스트림 요소 총합      |
>💡 다음은 여러 가지 집계 메서드를 이용해서 점수들을 출력하는 예제들이다.

```java
Arrays.stream(new int[]{55,66,77,88,99}).count();
//-> 전체 요소 개수를 얻는다.
Arrays.stream(new int[]{55,66,77,88,99}).sum();
//-> 합을 얻는다.
Arrays.stream(new int[]{55,66,77,88,99}).average().getAsDouble();
//-> 평균을 구한다.
Arrays.stream(new int[]{55,66,77,88,99}).max().getAsInt();
//-> 최대값을 구한다.
Arrays.stream(new int[]{55,66,77,88,99}).findFirst().getAsInt();
//-> 첫번째 값을 구한다.
```

### 사용자 집계 메서드
>📌기본 집계 메서드 외에 `Reduce()`메서드를 이용해서 프로그래머가 직접 원하는 집계 메서드를 
>만들어서 사용할 수 있다.

| 인터페이스     | 리턴 타입        | 메서드                                                |
| --------- | ------------ | -------------------------------------------------- |
| Stream    | Optional\<T> | reduce(BinaryOperator\<T>accumulator)              |
| Stream    | T            | reduce(T identity, BinaryOperator\<T> accumulator) |
| IntStream | OptionalInt  | reduce(intBinaryOperator op)                       |
| IntStream | int          | reduce(int identity, IntBinaryOperator op)         |
>💡 `reduce()` 예제

```java
studentList.stream()
	.map(Student::getScore)
	.reduce((a,b)-> a+b) //-> 학생들의 점수를 누적시키는 형태
	.get();

studentList.stream()
	.map(Student::getScore)
	.reduce(0, (a,b)->a+b) // -> 0(기본값)에 점수 누적
```

### 요소 수집 메서드
>📌 `collect()` 메서드를 이용해서 출력 결과를 컬렉션으로 저장할 수 있다.

| 리턴 타입          | 메서드                                    | 설명                            |
| -------------- | -------------------------------------- | ----------------------------- |
| List\<T>       | collect(Collectors.toList())           | 스트림 요소를 List로 변환              |
| Collection\<T> | collect(Collectors.toCollection(\<T>)) | 스트림 요소를 정한 Collection\<T>로 변환 |
| Map\<K, U>     | collect(Collectors.toMap(Key, value))  | 스트림 요소를 Map(K, U)로 변환         |
>💡 `collect()` 예제

```java
Arrays.stream(array)
	.filter(i->i.getGender()==Student.FEMALE)
	.collect(Collectoras.toList());

Arrays.stream(array)
	.filter(i->i.getGender()==Student.FEMALE)
	.collect(Collectoras.toCollection(HashSet::new));
	//-> 스트림 요소를 HashSet에 저장
	
Arrays.stream(array)
	.collect(Collectors.toMap(i->i.getName(), s->s))
	//-> 스트림 요소를 HashSet에 저장
```

### 요소 그루핑 메서드
>📌 `collect()`의 메서드 집계 기능을 이용하면 대량의 데이터를 처리해서 편리하게 통계 데이터를 추출할 수 있다.

### `partitioningBy()` 메서드

>📌 `partitioningBy()` 는 스트림 요소를 두 개의 집합으로 분할할 때 사용하는 메서드이다.

```java
Map<Boolean, List<Student>> stdByGender = 
	studentList.stream()
		.collect(Collectors.partitioningBy(i->i.getGender() == Student.MALE)); //-> 성별을 기준으로 true, false 로 나눠서 분류함
	List<Student> maleList = stdByGender.get(true);
	List<Student> femaleList = stdByGender.get(false);
```

### `groupingBy()` 메서드
>📌 데이터를 더 세부적으로 분류할 경우 `groupingBy` 메서드를 사용하면 편리하다.

```java
Map<Integer, Map<Integer, List<Student>>> stdGroupByGreade = 
    studentList.stream()
.collect(Collectors.groupingBy(Student::getGrade
,Collectors.groupingBy(Student::getGender)));

```

### 그루핑 매핑 및 집계 메서드 
>📌 그루핑 데이터에 대해서 여러 가지 집계나 매핑 기능을 제공한다.
>간단한 예제를 만들어보고 `Stream` 정리를 마친다.

```java
public class MyStream {  
    public String returnRepeatedString(String string, int times) {  
        return Stream.of(string)  
                .flatMap(e-> Arrays.stream(e.split("")))  
                .map(a-> String.valueOf(a).repeat(times))  
                .reduce("", (a,b)->a+b);  
    }  //-> 문자열을 반복출력 
  
    public void sortAndCount(List<Customer> customers){  
            Stream.of(customers)  
                    .forEach(i->i.stream()  
                            .collect(Collectors.groupingBy(Customer::getFirstName))  
                            .entrySet()  
                            .stream()  
                            .sorted(Comparator.comparingInt(Key->Key.getValue().size()))  
                            .forEach(Result->System.out.println(Result.getKey() + " " + Result.getValue().size())));  
    }  //-> 어떤 성씨가 가장 많은지 성씨 : count 형식으로 출력
}
```


---
# 제네릭 심화
## 제한된 타입 파라미터

```java
public class Person {  
    protected String name;  
  
    public Person(String name) {  
        this.name = name;  
    }  
  
    public Person() {  
    }    public String getName() {  
        return name;  
    }  
  
    @Override  
    public String toString() {  
        return  
                "이름= '" + name + '\'';  
    }  
}
```

```java
public class Student extends Person {  
    protected int grade;  
  
    public Student() {  
    }    public Student(String name, int grade) {  
        super.name = name;  
        this.grade = grade;  
    }  
    public int getGrade() {  
        return grade;  
    }  
  
    @Override  
    public String toString() {  
        return "Student{" +  
                "점수=" + grade +  
                ", 이름='" + name + '\'' +  
                '}';  
    }  
}
```

```java
public class College extends Student{  
    private int credit;  
  
    public College(String name, int grade, int credit) {  
        super(name, grade);  
        this.credit = credit;  
    }  
  
    @Override  
    public String toString() {  
        return "College{" +  
                "점수=" + credit +  
                ", 학년=" + grade +  
                ", 이름='" + name + '\'' +  
                '}';  
    }  
}
```

```java
public class Box <T extends Person>{  
    private T data;  
    public T getData(){  
        return data;  
    }  
    public void setData(T data){  
        this.data = data;  
    }  
}
```

```java
public class BoxTest {  
    public static void main(String[] args) {  
        Box<Person> box = new Box<>();  
		  //-> Person 타입 지정
        box.setData(new Person("홍길동"));  
        System.out.println(box.getData());  
  
        box.setData(new Student("김복순", 2));  
        System.out.println(box.getData());  
  
        box.setData(new College("대조영",2,4));  
        System.out.println(box.getData());  
        //-> Person 하위클래스 객체는 setData() 메서드의 매개변수로 전달 가능

		//box.setData(new String("안녕하세요!"));
		//String 은 Person의 하위객체가 아니기에 에러발생
    }  
}
```

## 와일드 카드 타입 [ \<?>, \<? extends ...>, \<? super...>]
* 제네릭 타입\<?>: 모든 클래스 타입이나 인터페이스 타입이 적용된다.
* 제네릭 타입\<? extends 타입>: 지정한 타입이나 하위 타입만 적용된다.
* 제네릭 타입\<? super 타입>: 지정한 타입이나 상위 타입만 적용된다.

```java
//-> 예제는 길어서 깃허브 링크로 대체
```

---
* [GitHub]([Kosa/Day11 at main · Yeong-Huns/Kosa (github.com)](https://github.com/Yeong-Huns/Kosa/tree/main/Day11))
