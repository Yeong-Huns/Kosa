```java
public class AnimalDriver {  
    public static void main(String[] args) {  
        Cat cat1 = new Cat("나비");  
        Cat cat2 = new Cat(10, 3, "치즈");  
        Chicken chicken = new Chicken("금계");  
        Dog dog = new Dog("바둑이");  
        Tiger tiger = new Tiger("백호");  
        cat1.bark();  
        cat1.move();  
  
        cat2.getStatus();  
  
        chicken.bark();  
        chicken.move();  
  
        dog.bark();  
        dog.move();  
  
        tiger.bark();  
        tiger.move();  
    }  
}
```