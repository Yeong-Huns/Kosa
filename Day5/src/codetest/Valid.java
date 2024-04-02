package codetest;

/**
 * packageName    : codetest
 * fileName       : Verifier
 * author         : Yeong-Huns
 * date           : 2024-04-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-02        Yeong-Huns       최초 생성
 */
public class Valid {
    private final String[] babbling = {"aya", "woo", "ye", "ma"};
    public int getCount(String[] strList){
        int value = 0;
        for(String str: strList){
            if(isContainBabbles(str)) value+=1;
        }
        return value;
    }

    public boolean isContainBabbles(String str){
        String valid = str;

        for(String babble: babbling){
            if(valid.contains(babble)){
                valid = valid.replaceFirst(babble, "");
            }
        }// ""
        return valid.isEmpty();
    }

}
