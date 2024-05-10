package main.java.kosa.myapp.entity.approval;

import lombok.Data;

import java.time.LocalDate;

/**
 * packageName    : main.java.kosa.myapp.entity.approval
 * fileName       : Approval
 * author         : Yeong-Huns
 * date           : 2024-05-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-08        Yeong-Huns       최초 생성
 */
@Data
public class Approval {
   private long approvalId;
   private LocalDate approvalDate;
   private int deptNO;
   private long memberId;
   private int approvalType;
   private int confirm;
   private String content;
   private LocalDate confirmRequestDate;
}
