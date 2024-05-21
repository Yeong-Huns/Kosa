package org.example.pro6.ex03;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * packageName    : org.example.pro6.ex02
 * fileName       : MemberVO
 * author         : Yeong-Huns
 * date           : 2024-05-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-21        Yeong-Huns       최초 생성
 */
@Getter
@Setter
public class MemberVO {
    private String id;
    private String pwd;
    private String name;
    private String email;
    private LocalDate joinDate;
}
