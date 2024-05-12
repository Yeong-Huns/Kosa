package main.java.kosa.myapp.dto.approval;

import lombok.Builder;
import lombok.Getter;

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
@Getter
@Builder
public class Approval {
   private int approvalId;
   private LocalDate approvalDate;
   private int deptNO;
   private int memberId;
   private int approvalType;
   private int confirm;
   private String content;
   private LocalDate confirmRequestDate;

   @Override
   public String toString() {
      return "Approval{" +
              "approvalId=" + approvalId +
              ", approvalDate=" + approvalDate +
              ", deptNO=" + deptNO +
              ", memberId=" + memberId +
              ", approvalType=" + approvalType +
              ", confirm=" + confirm +
              ", content='" + content + '\'' +
              ", confirmRequestDate=" + confirmRequestDate +
              '}';
   }
}
