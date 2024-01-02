package Library.MovieData;

import java.io.*;
import java.util.*;
/**
 * The {@class MovieDatabase} represents a movie database that allows the management
 * of a collection of movies. It provides functionality for loading movie data from a CSV file,
 * adding and removing movies, updating the database file, and retrieving movie details.
 *
 * The database is initialized by loading movie data from a CSV file located at a specified
 * file path upon construction. It maintains an in-memory list of movies for efficient access
 * and modification.
 *
 * This class is designed to handle basic movie database operations, including adding,
 * removing, and retrieving movie information.
 *  @author Ramiz Mammadov
 */
public class MovieDatabase {
    private static List<Movie> movies;
    private static final String filePath = "./Resources/MovieDatabase.csv";
     /**
     * Constructs a new {@code MovieDatabase} instance, initializing the list of movies
     * and populating it by loading data from the CSV file located at the specified file path.
     */
    public MovieDatabase() {
        movies = new ArrayList<>();
        loadMoviesFromFile();
    }

    /**
     * Loads movies from the CSV file specified by {@code filePath} into the movie database.
     * Each line in the CSV file represents a movie record, which is parsed and added as a Movie object
     * to the in-memory movie collection.
     *
     * The CSV file format is expected to have four fields per line in the following order:
     * 1. Movie Title
     * 2. Director's Name
     * 3. Release Year
     * 4. Running Time (in minutes)
     *
     * If the CSV file cannot be read or parsed correctly, any encountered IOExceptions are printed
     * to the standard error stream.
     */

    public static void loadMoviesFromFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] movieDetails = line.split(",");
                // Create a new Movie object from CSV data
                int year = Integer.parseInt(movieDetails[2].trim());
                int runningTime = Integer.parseInt(movieDetails[3].trim());
                Movie movie = new Movie(movieDetails[0], movieDetails[1], year, runningTime);
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new movie to the movie database if it does not already exist. The method checks
     * if a movie with the same title and release year is already in the database before adding
     * a new movie.
     *
     * @param newMovie The Movie object representing the movie to be added.
     * @return {@code true} if the movie is added successfully; {@code false} if the movie
     *         already exists in the database.
     */

    public static boolean addMovie(Movie newMovie) {
        if (!movieExists(newMovie)) {
            movies.add(newMovie);
            writeMovieToFile(newMovie);
            return true; // Movie added successfully
        } else {
            return false; // Movie already exists in the database
        }
    }

    /**
     * Checks if a movie with the same title and release year already exists in the movie database.
     *
     * @param movie The Movie object to check for existence in the database.
     * @return {@code true} if a movie with the same title and release year exists in the database;
     *         {@code false} otherwise.
     */

    public static boolean movieExists(Movie movie) {
        return movies.stream().anyMatch(
                m -> m.getTitle().equalsIgnoreCase(movie.getTitle()) && m.getReleaseYear() == movie.getReleaseYear());
    }

    /**
     * Writes movie data to the CSV file used for storing movie records. The data includes the movie's
     * title, director, release year, and running time.
     *
     * @param movie The Movie object to write to the CSV file.
     */

    private static void writeMovieToFile(Movie movie) {
        try (FileWriter fw = new FileWriter(filePath, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(movie.getTitle() +
                    "," + movie.getDirector() +
                    "," + movie.getReleaseYear() +
                    "," + movie.getRunningTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Removes a movie from the movie database based on its title, while ignoring case.
     * If a movie with the specified title is found and removed successfully, the database file
     * is updated to reflect the changes.
     *
     * @param title The title of the movie to be removed.
     * @return {@code true} if the movie with the specified title is removed successfully; {@code false}
     *         if no movie with the specified title is found.
     */
    public static boolean removeMovie(String title) {
        boolean movieRemoved = movies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
        if (movieRemoved) {
            updateMovieFile();
        }
        return movieRemoved;
    }
    /**
     * Updates the CSV file used for storing movie records with the current collection of movies
     * in the movie database. The method overwrites the existing file with the updated movie data.
     */
    public static void updateMovieFile() {
        try (FileWriter fw = new FileWriter(filePath, false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            for (Movie movie : movies) {
                out.println(movie.getTitle() +
                        "," + movie.getDirector() +
                        "," + movie.getReleaseYear() +
                        "," + movie.getRunningTime());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the details of a movie from the movie database based on its title and release year.
     * The method searches for a movie with the specified title (ignoring case) and release year,
     * and returns the first matching movie found.
     *
     * @param title The title of the movie to retrieve.
     * @param year The release year of the movie to retrieve.
     * @return A Movie object representing the movie with the specified title and release year,
     *         or {@code null} if no matching movie is found in the database.
     */

    public static Movie getMovieDetails(String title, int year) {

        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title) && movie.getReleaseYear() == year)
                .findFirst()
                .orElse(null);
    }

    public static List<Movie> getMovies() {
        return movies;
    }

}
