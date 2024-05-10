package main.java.kosa.myapp.dto.member.response;

import lombok.Builder;

/**
 * packageName    : main.java.kosa.myapp.dto.member.response
 * fileName       : GetAllMemberRequest
 * author         : Yeong-Huns
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        Yeong-Huns       최초 생성
 */
@Builder
public record GetAllMemberResponse(int memberId, String memberName, int deptNo, int roleId) {
}
