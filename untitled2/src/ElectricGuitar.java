import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : ElectricGuitar
 * author         : Yeong-Huns
 * date           : 2024-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-05        Yeong-Huns       최초 생성
 */
@Getter
public class ElectricGuitar {
    private String brand = "야마하";
    private int numOfPickups;
    private boolean rockStarUsesIt;

    @Builder
    public ElectricGuitar(String brand, int numOfPickups, boolean rockStarUsesIt) {
        this.brand = brand;
        this.numOfPickups = numOfPickups;
        this.rockStarUsesIt = rockStarUsesIt;
    }
}
