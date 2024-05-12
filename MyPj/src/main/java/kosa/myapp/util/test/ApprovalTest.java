package main.java.kosa.myapp.util.test;

import main.java.kosa.myapp.dto.approval.GetDeptApprovalResponse;
import main.java.kosa.myapp.dto.approval.Approval;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.repository.approval.ApprovalRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * packageName    : main.java.kosa.myapp.util.test
 * fileName       : ApprovalTest
 * author         : Yeong-Huns
 * date           : 2024-05-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-11        Yeong-Huns       최초 생성
 */
public class ApprovalTest {
    private final ApprovalRepository repository;
    public ApprovalTest() {
        repository = ApprovalRepository.getInstance();
    }
    private int approvalID;
    public void get_member_annual_leaves_Success(){
        System.out.println("멤버별 연차 등록 조회에 성공.");
        ResponseEntity<List<Approval>>  response = repository.getAnnualLeavesApproval(Approval.builder()
                .memberId(1)
                .approvalType(1)
                .approvalDate(LocalDate.now())
                .build());
        System.out.println(response.getErrorMessage());
        response.runIfSuccess(V->V.forEach(System.out::println));
    }

    public void get_member_annual_leaves_Not_Found(){
        System.out.println("멤버별 연차 등록 조회 결과 X");
        ResponseEntity<List<Approval>>  response = repository.getAnnualLeavesApproval(Approval.builder()
                .memberId(4)
                .approvalType(1)
                .approvalDate(LocalDate.of(2021,2,3))
                .build());
        response.printLog();
        response.runIfSuccess(V->V.forEach(System.out::println));
    }

    public void getMemberStatementOfReason_Success(){
        System.out.println("멤버별 퇴근 누락 사유서 조회 O");
        ResponseEntity<List<Approval>> response = repository.getMemberStatementOfReason(
                Approval.builder()
                        .memberId(3)
                        .approvalType(2)
                        .approvalDate(LocalDate.of(2023,5,2))
                        .build());
        response.printLog();
        response.runIfSuccess(V->V.forEach(System.out::println));
    }

    public void getMemberStatementOfReason_Not_Found(){
        System.out.println("멤버별 퇴근 누락 사유서 기록 X");
        ResponseEntity<List<Approval>> response = repository.getMemberStatementOfReason(
                Approval.builder()
                        .memberId(3)
                        .approvalType(2)
                        .approvalDate(LocalDate.of(2032,5,8))
                        .build());
        response.printLog();
        response.runIfSuccess(V->V.forEach(System.out::println));
    }

    public void getDeptAnnualLeaves_Success(){
        System.out.println("부서별 연차조회 기록O");
        ResponseEntity<List<GetDeptApprovalResponse>> response = repository.getDeptAnnualLeaves(
                Approval.builder()
                        .memberId(3)
                        .approvalType(1)
                        .approvalDate(LocalDate.of(2024,4,29))
                        .build());
        response.printLog();
        response.runIfSuccess(V->V.forEach(System.out::println));
    }

    public void getDeptAnnualLeaves_Not_Found(){
        System.out.println("부서별 연차조회 기록X");
        ResponseEntity<List<GetDeptApprovalResponse>> response = repository.getDeptAnnualLeaves(
                Approval.builder()
                        .memberId(3)
                        .approvalType(1)
                        .approvalDate(LocalDate.of(2023,5,8))
                        .build());
        response.printLog();
        response.runIfSuccess(V->V.forEach(System.out::println));
    }

    public void resisterApproval(){
        System.out.println("연차 등록 성공");
        ResponseEntity<Void> response = repository.resisterApproval(
                Approval.builder()
                        .memberId(3)
                        .content("연차 등록합니다!")
                        .approvalDate(LocalDate.of(2024,5,31))
                        .build());
        response.printLog();
        response.showDialogs();
    }

    public void insertStatementReason(){
        System.out.println("퇴근 누락 사유서 등록 성공");
        ResponseEntity<Void> response = repository.insertStatementReason(
                Approval.builder()
                        .memberId(3)
                        .content("깜빡하고 못 눌렀습니다!")
                        .approvalDate(LocalDate.of(2024,4,11))
                        .build());
        response.printLog();
        response.showDialogs();
    }

    public void approveOrRejectAnnualLeave(){
        ResponseEntity<Void> response3 = repository.approveOrRejectAnnualLeave(
        Approval.builder()
                .memberId(5)
                .approvalId(128)
                .confirm(1)
                .build());
        response3.printLog();
        response3.showDialogs();
    }
    public void setApprovalId(int approvalId){
        approvalID = approvalId;
    }
}
