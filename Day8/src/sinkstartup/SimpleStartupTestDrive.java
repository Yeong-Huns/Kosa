package sinkstartup;

/**
 * packageName    : sinkstartup
 * fileName       : SimpleStartupTestDrive
 * author         : Yeong-Huns
 * date           : 2024-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-05        Yeong-Huns       최초 생성
 */
public class SimpleStartupTestDrive {
    public static void main(String[] args) {
        SimpleStartup dot = new SimpleStartup();

        int[] locations = {2, 3, 4};
        dot.setLocationCells(locations);

        int userGuess = 2;
        String result = dot.checkYourself(userGuess);
        String testResult = "failed";
        if (result.equals("hit")) {
            testResult = "passed";
        }
        System.out.println(testResult);
    }
}

class SimpleStartup {
    private int[] locationCells;
    private int numOfHits = 0;

    public void setLocationCells(int[] locs) {
        locationCells = locs;
    }

    public String checkYourself(int guess) {
        String result = "miss";
        for (int cell : locationCells) {
            if (guess == cell) {
                result = "hit";
                numOfHits++;
                break;
            } // end if
        } // end for
        if (numOfHits == locationCells.length) {
            result = "kill";
        } // end if
        System.out.println(result);
        return result;
    } // end method

}
