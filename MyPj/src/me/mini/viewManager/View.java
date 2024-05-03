package me.mini.viewManager;

/**
 * packageName    : me.mini.viewManager
 * fileName       : View
 * author         : Yeong-Huns
 * date           : 2024-05-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-03        Yeong-Huns       최초 생성
 */
public enum View {
    LOGIN("Login"),
    SIGNUP("SignUp"),
    COMMUTE("CommuteTime"),
    ATTENDANCE("MyAttendance")
    ;

    private final String value;

    View(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
//View.LOGIN