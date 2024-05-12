package main.java.kosa.myapp.repository.approval;

import main.java.kosa.myapp.dto.approval.GetDeptApprovalResponse;
import main.java.kosa.myapp.dto.approval.Approval;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.util.dataBaseConnection.CallableStatementTemplate;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 * packageName    : main.java.kosa.myapp.repository.approval
 * fileName       : ApprovalRepository
 * author         : Yeong-Huns
 * date           : 2024-05-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-09        Yeong-Huns       최초 생성
 */
public class ApprovalRepository {
    private static ApprovalRepository instance;
    private final CallableStatementTemplate callableStatementTemplate;

    public static ApprovalRepository getInstance() {
        if (instance == null) {
            instance = new ApprovalRepository();
        }
        return instance;
    }

    private ApprovalRepository() {
        this.callableStatementTemplate = CallableStatementTemplate.getCallableStatement();
    }

    public ResponseEntity<List<Approval>> getAnnualLeavesApproval(Approval approval) {
        return callableStatementTemplate.queryForMany(
                " { call approval_package.get_member_annual_leaves(?, ?, ?, ?, ?, ?, ?) }",
                cs -> {
                    cs.setInt(1, approval.getMemberId());
                    cs.setInt(2, approval.getApprovalType());
                    cs.setInt(3, approval.getApprovalDate().getYear());
                    cs.setInt(4, approval.getApprovalDate().getMonthValue());
                    cs.registerOutParameter(5, OracleTypes.CURSOR);
                    cs.registerOutParameter(6, Types.INTEGER);
                    cs.registerOutParameter(7, Types.VARCHAR);
                }, (rs, rowNum) -> {
                    LocalDate approvalDate = rs.getDate(3) != null ? rs.getDate(3).toLocalDate() : null;
                    return Approval.builder()
                            .confirm(rs.getInt(1))
                            .content(rs.getString(2))
                            .approvalDate(approvalDate)
                            .build();
                });
    }

    public ResponseEntity<List<Approval>> getMemberStatementOfReason(Approval approval){
        return callableStatementTemplate.queryForMany(
                "{ call approval_package.get_member_statement_of_reason(?, ?, ?, ?, ?, ?, ?)}",
                cs -> {
                    cs.setInt(1, approval.getMemberId());
                    cs.setInt(2, approval.getApprovalType());
                    cs.setInt(3, approval.getApprovalDate().getYear());
                    cs.setInt(4, approval.getApprovalDate().getMonthValue());
                    cs.registerOutParameter(5, OracleTypes.CURSOR);
                    cs.registerOutParameter(6, Types.INTEGER);
                    cs.registerOutParameter(7, Types.VARCHAR);
                }, (rs, rowNum) -> {
                    LocalDate approvalDate = rs.getDate(3) != null ? rs.getDate(3).toLocalDate() : null;
                    return Approval.builder()
                            .confirm(rs.getInt(1))
                            .content(rs.getString(2))
                            .approvalDate(approvalDate)
                            .build();
                });
    }


    public ResponseEntity<List<GetDeptApprovalResponse>> getDeptAnnualLeaves(Approval approval){ //부서별 연차조회
        return callableStatementTemplate.queryForMany(
                "{ call approval_package.get_dept_annual_leaves(?, ?, ?, ?, ?, ?, ?) }",
            cs -> {
                cs.setInt(1, approval.getMemberId());
                cs.setInt(2, approval.getApprovalType());
                cs.setInt(3, approval.getApprovalDate().getYear());
                cs.setInt(4, approval.getApprovalDate().getMonthValue());
                cs.registerOutParameter(5, OracleTypes.CURSOR);
                cs.registerOutParameter(6, Types.INTEGER);
                cs.registerOutParameter(7, Types.VARCHAR);
            }, (rs, rowNum) -> {
                    LocalDate approvalDate = rs.getDate(6) != null ? rs.getDate(6).toLocalDate() : null;
                    return GetDeptApprovalResponse.builder()
                            .approvalId(rs.getInt(1))
                            .memberId(rs.getInt(2))
                            .memberName(rs.getString(3))
                            .confirm(rs.getInt(4))
                            .content(rs.getString(5))
                            .approvalDate(approvalDate)
                            .build();
                });
    }

    public ResponseEntity<List<GetDeptApprovalResponse>> getDeptStatementOfReason(Approval approval){ //부서별 퇴근 누락 사유서 조회
         return callableStatementTemplate.queryForMany(
                "{ call approval_package.get_dept_statement_of_reason(?, ?, ?, ?, ?, ?, ?)}",
                cs -> {
                    cs.setInt(1, approval.getMemberId());
                    cs.setInt(2, approval.getApprovalType());
                    cs.setInt(3, approval.getApprovalDate().getYear());
                    cs.setInt(4, approval.getApprovalDate().getMonthValue());
                    cs.registerOutParameter(5, OracleTypes.CURSOR);
                    cs.registerOutParameter(6, Types.INTEGER);
                    cs.registerOutParameter(7, Types.VARCHAR);
                }, (rs, rowNum) -> {
                    LocalDate approvalDate = rs.getDate(6) != null ? rs.getDate(6).toLocalDate() : null;
                     return GetDeptApprovalResponse.builder()
                             .approvalId(rs.getInt(1))
                             .memberId(rs.getInt(2))
                             .memberName(rs.getString(3))
                             .confirm(rs.getInt(4))
                             .content(rs.getString(5))
                             .approvalDate(approvalDate)
                             .build();
                });
    }

    public ResponseEntity<Void> resisterApproval(Approval approval){ //연차등록
         return callableStatementTemplate.executeUpdate(
          "{ call approval_package.resister_approval(?,?,?,?,?)}",
                cs -> {
              cs.setInt(1, approval.getMemberId());
              cs.setString(2, approval.getContent());
              cs.setDate(3, Date.valueOf(approval.getApprovalDate()));
              cs.registerOutParameter(4, Types.INTEGER);
              cs.registerOutParameter(5, Types.VARCHAR);
          });
    }

    public ResponseEntity<Void> insertStatementReason(Approval approval){ //퇴근 누락 사유서
        return callableStatementTemplate.executeUpdate(
          "{ call approval_package.insert_statement_reason(?, ?, ?, ?, ?)}",
                cs-> {
              cs.setInt(1, approval.getMemberId());
              cs.setString(2, approval.getContent());
              cs.setDate(3, Date.valueOf(approval.getApprovalDate()));
              cs.registerOutParameter(4, Types.INTEGER);
              cs.registerOutParameter(5, Types.VARCHAR);
          });
    }

    public ResponseEntity<Void> approveOrRejectAnnualLeave(Approval approval){ //연차 수락 혹은 거절
        return callableStatementTemplate.executeUpdate(
                "{ call approval_package.approve_or_reject_annual_leave(?, ?, ?, ?, ?)}",
                cs -> {
                    cs.setInt(1, approval.getMemberId());
                    cs.setInt(2,approval.getApprovalId());
                    cs.setInt(3, approval.getConfirm());
                    cs.registerOutParameter(4, Types.INTEGER);
                    cs.registerOutParameter(5, Types.VARCHAR);
                });
    }

    public ResponseEntity<Void> approveOrRejectMissing(Approval approval) { //퇴근 누락 사유서 수락 혹은 반려
        return callableStatementTemplate.executeUpdate(
                "{ call approval_package.approve_or_reject_missing(?, ?, ?, ?, ?)}",
                cs -> {
                    cs.setInt(1, approval.getMemberId());
                    cs.setInt(2, approval.getApprovalId());
                    cs.setInt(3, approval.getConfirm());
                    cs.registerOutParameter(4, Types.INTEGER);
                    cs.registerOutParameter(5, Types.VARCHAR);
                });
    }
}
