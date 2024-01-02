package Library.MovieData;
/**
 * A custom exception class to represent the situation where an invalid or fake release year is encountered
 * for a movie. This exception is thrown when an operation attempts to use a release year value that is
 * considered invalid or unrealistic.
 */
public class FakeReleaseYear extends Exception {
    public FakeReleaseYear() {
        super();
    }
}
