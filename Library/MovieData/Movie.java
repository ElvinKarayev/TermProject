package Library.MovieData;
/**
 * The {@code Movie} class represents a movie entity with properties including title, director,
 * release year, and running time. It provides methods for accessing and modifying these properties,
 * as well as displaying movie information and generating a movie summary.
 */
public class Movie {

    private String title = "";
    private String director = "";
    private int releaseYear;
    private int runningTime;
    /**
     * Constructs a new {@code Movie} object with the specified attributes.
     *
     * @param title       The title of the movie.
     * @param director    The director of the movie.
     * @param releaseYear The release year of the movie.
     * @param runningTime The running time of the movie in minutes.
     */
    public Movie(String title, String director, int releaseYear, int runningTime) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.runningTime = runningTime;
    }

    /**
     * Gets the director of the movie.
     *
     * @return The director's name.
     */

    public String getDirector() {
        return director;
    }

    /**
     * Gets the release year of the movie.
     *
     * @return The release year.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Gets the running time of the movie in minutes.
     *
     * @return The running time in minutes.
     */
    public int getRunningTime() {
        return runningTime;
    }

    /**
     * Gets the title of the movie.
     *
     * @return The title of the movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the director of the movie.
     *
     * @param director The director's name to set.
     */

    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Sets the release year of the movie.
     *
     * @param releaseYear The release year to set.
     */

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Sets the title of the movie.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the running time of the movie in minutes.
     *
     * @param runningTime The running time in minutes to set.
     */
    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    /**
     * Displays detailed information about the movie, including title, director, release year,
     * and running time.
     */
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Director: " + director);
        System.out.println("Release Year: " + releaseYear);
        System.out.println("Running Time: " + runningTime + " minutes");
    }

    /**
     * Generates a movie summary in the format "Title (Release Year) - Directed by Director".
     *
     * @return A summary string for the movie.
     */
    public String getMovieSummary() {
        return title + " (" + releaseYear + ") - Directed by " + director;
    }

     /**
     * Returns a string representation of the movie object in a specific format.
     *
     * @return A string representation of the movie.
     */
    @Override
    public String toString() {
        return "Movie title=" + title + ", director=" + director + ", releaseYear=" + releaseYear + ", runningTime="
                + runningTime;
    }
    /**
     * Compares this movie object with another object for equality based on title, director, release year,
     * and running time.
     *
     * @param obj The object to compare with.
     * @return {@code true} if the objects are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj){
        Movie smth=(Movie) obj;
        return this.getTitle().equals(smth.getTitle()) && this.getDirector().equals(smth.getDirector()) && this.getReleaseYear()==smth.getReleaseYear() && this.getRunningTime()==smth.getRunningTime();
        
    }
}