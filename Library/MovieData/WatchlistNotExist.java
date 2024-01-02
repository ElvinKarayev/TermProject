package Library.MovieData;
/**
 * A custom exception class to represent the situation where a user's watchlist does not exist.
 * This exception is thrown when an operation attempts to retrieve a watchlist that is not found
 * for a specific user.
 * @author Elvin Garayev
 */
public class WatchlistNotExist extends Exception {
    public WatchlistNotExist(){
        super();
    }
}
