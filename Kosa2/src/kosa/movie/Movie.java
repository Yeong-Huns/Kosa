package kosa.movie;

public class Movie {
    private String title;
    private String genre;
    private int rating;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void playIt(){
        System.out.println(this.title + " 영화 상영");
    }

}
