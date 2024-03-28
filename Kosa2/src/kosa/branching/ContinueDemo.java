package kosa.branching;

public class ContinueDemo {
    public static void main(String[] args) {

        String searchMe = "peter piper picked a " + "peck of pickled peppers";
        int max = searchMe.length();    // String 길이
        int numPs = 0;  // p의 갯수

        for (int i = 0; i < max; i++) {
            // interested only in p's
            if (searchMe.charAt(i) != 'p')
                continue;   // p가 아니면 다음구문 실행

            // process p's
            numPs++;    // p면 실행
        }
        System.out.println("Found " + numPs + " p's in the string.");   // p의 갯수 출력
    }   // 주어진 문자의 갯수 or 단어 갯수 찾는 문제에서 사용가능
}
