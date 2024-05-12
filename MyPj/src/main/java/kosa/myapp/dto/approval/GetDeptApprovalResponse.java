package main.java.kosa.myapp.dto.approval;

import lombok.Builder;

import java.time.LocalDate;

/**
 * packageName    : main.java.kosa.myapp.dto.approval.response
 * fileName       : getDeptAnnualLeavesResponse
 * author         : Yeong-Huns
 * date           : 2024-05-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-12        Yeong-Huns       최초 생성
 */
@Builder
public record GetDeptApprovalResponse(int approvalId ,int memberId, String memberName, int confirm, String content, LocalDate approvalDate) {

}
