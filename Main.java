
import java.util.List;

import Library.GUIs.LoginGUI;
import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;
import Library.MovieData.Watchlist;

public class Main {
    public static void main(String[] args) {
       // LoginGUI login = new LoginGUI();
        new MovieDatabase(); 
       // System.out.println(MovieDatabase.getMovieDetails("Fight Club",1999));
        List<Movie> a=MovieDatabase.getMovies();
        Movie b=a.get(2);
        Watchlist.removeMovieFromWatchlist("Elvin", b);
    }

}
