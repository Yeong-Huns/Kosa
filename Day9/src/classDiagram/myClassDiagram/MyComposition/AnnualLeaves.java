package classDiagram.myClassDiagram.MyComposition;

/**
 * packageName    : classDiagram.myClassDiagram.MyComposition
 * fileName       : AnnualLeaves
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
public class AnnualLeaves {
    private int count;
    public void UseAnnualLeaves(){
        this.count--;
    }

    public AnnualLeaves() {
        this.count = 7;
    }
}
