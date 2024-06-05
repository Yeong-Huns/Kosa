package app.labs.member.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter @ToString 
public class Member {
    private String userid;
    private String name;
    private String password;
    private String password2;
    private String phone;
    private String email;
}