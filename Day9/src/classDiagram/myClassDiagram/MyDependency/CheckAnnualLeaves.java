package classDiagram.myClassDiagram.MyDependency;

/**
 * packageName    : classDiagram.myClassDiagram.MyDependency
 * fileName       : CheckAnnualLeaves
 * author         : Yeong-Huns
 * date           : 2024-04-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-08        Yeong-Huns       최초 생성
 */
public class CheckAnnualLeaves {
    public boolean CheckAnnualLeaves(AnnualLeaves annualLeaves){
        return annualLeaves.getAnnualLeavesCount() > 0;
    }
}
