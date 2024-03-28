```java
public class Dog extends Animal{  
    public Dog(int size, int age, String name) {  
        super(size, age, name, "개");  
    }  
  
    public Dog(String name) {  
        super(name, "개");  
    }  
  
    public void bark(){  
        super.bark("멍멍 짖습니다.");  
    }  
    public void move(){  
        super.move("정신사납게");  
    }  
}
```