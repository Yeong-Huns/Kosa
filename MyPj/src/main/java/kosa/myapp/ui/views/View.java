package main.java.kosa.myapp.ui.views;

/**
 * packageName    : me.mini.viewManager
 * fileName       : View
 * author         : Yeong-Huns
 * date           : 2024-05-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-04        Yeong-Huns       최초 생성
 */
public enum View {
    LOGIN("Login"),
    SIGNUP("SignUp"),
    COMMUTE("CommuteTime"),
    ATTENDANCE("Attendance"),
    MY_ATTENDANCE("MyAttendance"),
    ANNUAL_LEAVES("AnnualLeaves"),
    APPROVAL("Approval"),
    SETTING("Setting")
    ;
    private final String Value;

    View(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return Value;
    }
}
