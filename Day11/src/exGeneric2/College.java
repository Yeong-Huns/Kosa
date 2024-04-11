package exGeneric2;

/**
 * packageName    : MyGeneric
 * fileName       : College
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class College extends Student {
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
