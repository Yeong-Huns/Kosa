package main.java.kosa.myapp.dto.approval.response;

import lombok.Builder;
import main.java.kosa.myapp.ui.views.approval.AnnualRecordJPanel;

import java.time.LocalDate;

/**
 * packageName    : main.java.kosa.myapp.dto.approval.response
 * fileName       : GetMemberAnnualLeaves
 * author         : Yeong-Huns
 * date           : 2024-05-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-09        Yeong-Huns       최초 생성
 */
@Builder
public record GetAnnualLeavesApproveResponse(int confirm, String content, LocalDate approvalDate) {
    public AnnualRecordJPanel toAnnualRecordJPanel() {
        return AnnualRecordJPanel.builder()
                .confirm(confirm)
                .content(content)
                .approvalDate(approvalDate)
                .build();
    }
}
