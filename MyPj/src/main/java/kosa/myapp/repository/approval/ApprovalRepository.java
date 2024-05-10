package main.java.kosa.myapp.repository.approval;

import main.java.kosa.myapp.dto.approval.response.GetAnnualLeavesApproveResponse;
import main.java.kosa.myapp.entity.approval.Approval;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.util.dataBaseConnection.CallableStatementTemplate;
import main.java.kosa.myapp.util.dataBaseConnection.DBConnection;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public ResponseEntity<List<GetAnnualLeavesApproveResponse>> getAnnualLeavesApproval(Approval approval) {
        return callableStatementTemplate.queryForMany(
                " { call approval_package.get_member_annual_leaves(?, ?, ?, ?, ?, ?, ?) }",
                cs -> {
                    cs.setInt(1, (int)approval.getMemberId());
                    cs.setInt(2, approval.getApprovalType());
                    cs.setInt(3, approval.getApprovalDate().getYear());
                    cs.setInt(4, approval.getApprovalDate().getMonthValue());
                    cs.registerOutParameter(5, OracleTypes.CURSOR);
                    cs.registerOutParameter(6, Types.INTEGER);
                    cs.registerOutParameter(7, Types.VARCHAR);
                }, (rs, rowNum) -> {
                    LocalDate approvalDate = rs.getDate(3) != null ? rs.getDate(3).toLocalDate() : null;
                    return GetAnnualLeavesApproveResponse.builder()
                            .confirm(rs.getInt(1))
                            .content(rs.getString(2))
                            .approvalDate(approvalDate)
                            .build();
                });

    }


}
