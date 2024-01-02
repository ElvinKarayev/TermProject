package Library.MovieData;
/**
 * A custom exception class to represent the situation where a movie does not exist in the database.
 * This exception is thrown when an operation attempts to retrieve details of a movie that is not
 * found in the movie database.
 */
public class movieNotExist extends Exception {
    movieNotExist() {
        super();
    }
}
