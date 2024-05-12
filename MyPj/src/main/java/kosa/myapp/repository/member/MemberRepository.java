package main.java.kosa.myapp.repository.member;

import main.java.kosa.myapp.dto.member.DeleteMemberRequest;
import main.java.kosa.myapp.dto.member.Member;
import main.java.kosa.myapp.dto.member.UpdateDeptAndRoleRequest;
import main.java.kosa.myapp.dto.response.ResponseEntity;
import main.java.kosa.myapp.util.dataBaseConnection.CallableStatementTemplate;
import oracle.jdbc.OracleTypes;

import java.sql.Types;
import java.util.List;

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


    public ResponseEntity<Void> insertMember(Member member){
        return callableStatementTemplate.executeUpdate(
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
    }

    public ResponseEntity<Integer> login(Member member){
         return callableStatementTemplate.queryForOne(
                "{ call member_package.login(?, ?, ?, ?, ?) }",
                cs-> {
                    cs.setString(1, member.getId());
                    cs.setString(2, member.getPassword());
                    cs.registerOutParameter(3, OracleTypes.CURSOR);
                    cs.registerOutParameter(4, Types.INTEGER);
                    cs.registerOutParameter(5, Types.VARCHAR);
                }, (rs, rowNum) -> rs.getInt(1)
        );
    };

    public ResponseEntity<Void> deleteMember(DeleteMemberRequest request){
        return callableStatementTemplate.executeUpdate(
                "{ call member_package.delete_member(?, ?, ?, ?)}",
                cs -> {
                    cs.setInt(1, request.currentUserId());
                    cs.setInt(2, request.targetUserId());
                    cs.registerOutParameter(3, Types.INTEGER);
                    cs.registerOutParameter(4, Types.VARCHAR);
                }
        );
    }

    public ResponseEntity<List<Member>> getAllMember(int currentUserId){
        return callableStatementTemplate.queryForMany(
                "{ call member_package.get_all_member(?, ?, ?, ?)}",
                cs -> {
                  cs.setInt(1, currentUserId);
                  cs.registerOutParameter(2, OracleTypes.CURSOR);
                  cs.registerOutParameter(3, Types.INTEGER);
                  cs.registerOutParameter(4, Types.VARCHAR);
                }, (rs, rowNum) -> Member.builder()
                        .memberId(rs.getInt(1))
                        .name(rs.getString(2))
                        .deptNo(rs.getInt(3))
                        .roleId(rs.getInt(4))
                        .build()
        );
    }

    public ResponseEntity<Void> updateDeptAndRole(UpdateDeptAndRoleRequest request){
        return callableStatementTemplate.executeUpdate(
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
    }
}
