package main.java.kosa.myapp.repository.member;

import main.java.kosa.myapp.Main;
import main.java.kosa.myapp.dto.member.request.DeleteMemberRequest;
import main.java.kosa.myapp.dto.member.request.LoginRequest;
import main.java.kosa.myapp.dto.member.request.UpdateDeptAndRoleRequest;
import main.java.kosa.myapp.dto.member.response.GetAllMemberResponse;
import main.java.kosa.myapp.entity.member.Member;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.ui.dialogs.DialogUtils;
import main.java.kosa.myapp.util.dataBaseConnection.CallableStatementTemplate;
import oracle.jdbc.OracleTypes;

import javax.swing.*;
import java.sql.Types;
import java.util.List;
import java.util.OptionalInt;

/**
 * packageName    : main.java.kosa.myapp.repository
 * fileName       : MemberDAO
 * author         : Yeong-Huns
 * date           : 2024-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-04        Yeong-Huns       최초 생성
 */
public class MemberRepository {
    private static MemberRepository instance;
    public static MemberRepository getInstance() {
        if (instance == null) {
            instance = new MemberRepository();
        }
        return instance;
    }

    private final CallableStatementTemplate callableStatementTemplate;

    private MemberRepository() {
        this.callableStatementTemplate = CallableStatementTemplate.getCallableStatement();
    }


    public void insertMember(Member member){
        ResponseEntity<Void> insertMember = callableStatementTemplate.executeUpdate(
                "{ call member_package.insert_new_member(?, ?, ?, ?, ?, ?)}",
                cs-> {
                    cs.setString(1, member.getId());
                    cs.setString(2, member.getPassword());
                    cs.setString(3, member.getName());
                    cs.setString(4, member.getPhoneNumber());
                    cs.registerOutParameter(5, Types.INTEGER);
                    cs.registerOutParameter(6, Types.VARCHAR);
                }
        );
        insertMember.executeIfSuccessOrElseThrow(
                () -> DialogUtils.showSuccessMessage(insertMember.getErrorMessage()),
                () -> DialogUtils.showFailureMessage(insertMember.getErrorMessage())
        );
    }

    public int login(LoginRequest request){
         ResponseEntity<Integer> primaryKey = callableStatementTemplate.queryForOne(
                "{ call member_package.login(?, ?, ?, ?, ?) }",
                cs-> {
                    cs.setString(1, request.id());
                    cs.setString(2, request.password());
                    cs.registerOutParameter(3, OracleTypes.CURSOR);
                    cs.registerOutParameter(4, Types.INTEGER);
                    cs.registerOutParameter(5, Types.VARCHAR);
                }, (rs, rowNum) -> rs.getInt(1)
        );
        int sessionKey = primaryKey.executeIfSuccessOrElseThrow(
                        ()->DialogUtils.showSuccessMessage(primaryKey.getErrorMessage()),
                        ()->DialogUtils.showFailureMessage(primaryKey.getErrorMessage()));
        Main.setSessionKey(sessionKey);
        return sessionKey;
    };

    public void deleteMember(DeleteMemberRequest request){
        ResponseEntity<Void> deleteMember = callableStatementTemplate.executeUpdate(
                "{ call member_package.delete_member(?, ?, ?, ?)}",
                cs -> {
                    cs.setInt(1, request.currentUserId());
                    cs.setInt(2, request.targetUserId());
                    cs.registerOutParameter(3, Types.INTEGER);
                    cs.registerOutParameter(4, Types.VARCHAR);
                }
        );
        deleteMember.executeIfSuccessOrElseThrow(
                ()->DialogUtils.showSuccessMessage(deleteMember.getErrorMessage()),
                ()->DialogUtils.showFailureMessage(deleteMember.getErrorMessage())
        );
    }

    public List<GetAllMemberResponse> getAllMember(int currentUserId){
        ResponseEntity<List<GetAllMemberResponse>> allMembers = callableStatementTemplate.queryForMany(
                "{ call member_package.get_all_member(?, ?, ?, ?)}",
                cs -> {
                  cs.setInt(1, currentUserId);
                  cs.registerOutParameter(2, OracleTypes.CURSOR);
                  cs.registerOutParameter(3, Types.INTEGER);
                  cs.registerOutParameter(4, Types.VARCHAR);
                }, (rs, rowNum) -> GetAllMemberResponse.builder()
                        .memberId(rs.getInt(1))
                        .memberName(rs.getString(2))
                        .deptNo(rs.getInt(3))
                        .roleId(rs.getInt(4))
                        .build()
        );
        return allMembers.orElseThrow(() -> {});
    }

    public void updateDeptAndRole(UpdateDeptAndRoleRequest request){
        ResponseEntity<Void> updateDeptAndRole = callableStatementTemplate.executeUpdate(
                "{ call member_package.update_deptno_and_role(?, ?, ?, ?, ?, ?) }",
                cs -> {
                    cs.setInt(1, request.currentUserId());
                    cs.setInt(2, request.targetMemberId());
                    cs.setInt(3, request.deptNo());
                    cs.setInt(4, request.roleId());
                    cs.registerOutParameter(5, Types.INTEGER);
                    cs.registerOutParameter(6, Types.VARCHAR);
                }
        );
        updateDeptAndRole.executeIfSuccessOrElseThrow(
                ()->DialogUtils.showSuccessMessage(updateDeptAndRole.getErrorMessage()),
                ()->DialogUtils.showFailureMessage(updateDeptAndRole.getErrorMessage())
        );
    }
}
