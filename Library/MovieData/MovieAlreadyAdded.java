package Library.MovieData;
/**
 * A custom exception class to represent the situation where a movie is already added to the database.
 * This exception is thrown when an operation attempts to add a movie to the database, but a movie
 * with the same title and release year already exists in the database.
 */
public class MovieAlreadyAdded extends Exception {
    public MovieAlreadyAdded(){
        super();
    }
}
