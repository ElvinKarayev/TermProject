import Library.GUI.*;
import java.util.List;
import Library.MovieData.*;

public class Main {
    public static void main(String[] args) {
        LoginGUI login = new LoginGUI();
        new MovieDatabase();
        // System.out.println(MovieDatabase.getMovieDetails("Fight Club",1999));
        List<Movie> a = MovieDatabase.getMovies();
    }

}
