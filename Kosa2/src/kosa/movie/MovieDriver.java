package kosa.movie;

public class MovieDriver {
    public static void main(String[] args) {
        Movie movie1 = new Movie();
        movie1.setTitle("파묘");
        movie1.playIt();

        System.out.println(movie1.getTitle());

        Movie movie2 = new Movie();
        movie2.setTitle("범죄도시 4");
        movie2.playIt();
    }
}
