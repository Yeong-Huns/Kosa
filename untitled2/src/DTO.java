/**
 * packageName    : PACKAGE_NAME
 * fileName       : DTO
 * author         : Yeong-Huns
 * date           : 2024-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-05        Yeong-Huns       최초 생성
 */
public record DTO (String brand,
        int numOfPickups,
        boolean rockStarUsesIt) {

    public ElectricGuitar toEntity (String brand, int numOfPickups, boolean rockStarUsesIt){
        return ElectricGuitar.builder()
                .brand(brand)
                .numOfPickups(numOfPickups)
                .rockStarUsesIt(rockStarUsesIt)
                .build();
    }
}
