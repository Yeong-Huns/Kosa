package main.java.kosa.myapp.dto.member;

import lombok.Builder;

/**
 * packageName    : main.java.kosa.myapp.dto.member.request
 * fileName       : DeleteMemberRequest
 * author         : Yeong-Huns
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        Yeong-Huns       최초 생성
 */
@Builder
public record DeleteMemberRequest(int currentUserId, int targetUserId) {
}
