package com.kosa;

import java.util.ArrayList;

/**
 * packageName    : com.kosa
 * fileName       : MemberTest
 * author         : Yeong-Huns
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        Yeong-Huns       최초 생성
 */
public class MemberTest {
    public static void main(String[] args) {
        MemberDAO dao = new MemberDAO();
        ArrayList<MemberVO> list = dao.list();

        for (int i = 0; i < list.size(); i++) {
            MemberVO data = (MemberVO) list.get(i);
            String id = data.getId();
            String name = data.getName();
            int height = data.getHeight();
            int weight = data.getWeight();
            int age = data.getAge();

            System.out.println("아이디는>>" + id +
                    " 이름은>>" + name +
                    " 키는>>" + height +
                    " 몸무게는>>" + weight +
                    " 나이는>>" + age);
        }
    }
}
