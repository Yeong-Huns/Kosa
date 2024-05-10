package main.java.kosa.myapp.controller.approval;

import main.java.kosa.myapp.dto.approval.response.GetAnnualLeavesApproveResponse;
import main.java.kosa.myapp.entity.approval.Approval;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.repository.approval.ApprovalRepository;

import java.util.List;

/**
 * packageName    : main.java.kosa.myapp.controller.approval
 * fileName       : ApprovalController
 * author         : Yeong-Huns
 * date           : 2024-05-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-09        Yeong-Huns       최초 생성
 */
public class ApprovalController {
    private final ApprovalRepository approvalRepository;
    private static ApprovalController instance;

    public static ApprovalController getInstance() {
        if (instance == null) {
            instance = new ApprovalController();
        }
        return instance;
    }

    private ApprovalController() {
        this.approvalRepository = ApprovalRepository.getInstance();
    }

    public ResponseEntity<List<GetAnnualLeavesApproveResponse>> getAnnualLeavesApproval(Approval approval) {
        System.out.println("ApprovalController getAnnualLeavesApproval : " + approval.getMemberId() + " : " + approval.getApprovalDate() + " : " + approval.getApprovalType());
        return approvalRepository.getAnnualLeavesApproval(approval);
    }
}
