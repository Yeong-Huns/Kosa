package kosa.forLoop;

public class EnhanceForDemo {
    public static void main(String[] args){
        int[] numbers =
                {1,2,3,4,5,6,7,8,9,10};
        for (int item : numbers) {  // 향상된 forLoop
            System.out.println("Count is: " + item);
        }
    }
}
