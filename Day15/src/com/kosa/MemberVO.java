package com.kosa;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.kosa
 * fileName       : MemberVO
 * author         : Yeong-Huns
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        Yeong-Huns       최초 생성
 */
@Data
@Getter
public class MemberVO {
    private String id;
    private String name;
    private int height;
    private int weight;
    private int age;
}
