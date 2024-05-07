package main.java.kosa.myapp.repository.member;

import main.java.kosa.myapp.entity.member.Member;
import main.java.kosa.myapp.entity.response.ResponseEntity;
import main.java.kosa.myapp.util.dataBaseConnection.CallableStatementTemplate;

import java.sql.Types;

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
        this.callableStatementTemplate = new CallableStatementTemplate();
    }


    public  ResponseEntity<Void> insertMember(Member member){
        return callableStatementTemplate.execute(
                "{ call member_package.insert_new_member(?, ?, ?, ?, ?, ?)}",
                cs-> {
                    cs.setString(1, member.getId());
                    cs.setString(2, member.getPassword());
                    cs.setString(3, member.getName());
                    cs.setString(4, member.getPhoneNumber());
                    cs.registerOutParameter(5, Types.INTEGER);
                    cs.registerOutParameter(6, Types.VARCHAR);
                }, 5, 6
        );
    }

}
