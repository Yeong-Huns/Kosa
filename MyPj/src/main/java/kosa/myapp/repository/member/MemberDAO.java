package main.java.kosa.myapp.repository.member;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * packageName    : main.java.kosa.myapp.repository.member
 * fileName       : MemberDAO
 * author         : Yeong-Huns
 * date           : 2024-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-04        Yeong-Huns       최초 생성
 */
public interface MemberDAO {

    public void insertMember(Member member);

}
