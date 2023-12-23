package Library.MovieData;
import java.io.*;
import java.util.*;

public class MovieDatabase {
    private static List<Movie> movies;
    private static final String filePath = "./Resources/MovieDatabase.csv";


    public MovieDatabase() {
        movies = new ArrayList<>();
        loadMoviesFromFile();
    }

    // Loads movies from the CSV file into the movies list

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

    // Adds a new movie to the database if it doesn't already exist

    public static boolean addMovie(Movie newMovie) {
        if (!movieExists(newMovie)) {
            addMovie(newMovie);
            writeMovieToFile(newMovie);
            return true; // Movie added successfully
        } else {
            return false; // Movie already exists in the database
        }
    }

    // Check if a movie already exists in the database

    public static boolean movieExists(Movie movie) {
        return movies.stream().anyMatch(
                m -> m.getTitle().equalsIgnoreCase(movie.getTitle()) && m.getReleaseYear() == movie.getReleaseYear());
    }

    // Add movie's data to CSV file

    public  static void writeMovieToFile(Movie movie) {
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

    // Removes a movie from the database

    public static boolean removeMovie(String title) {
        boolean movieRemoved = movies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
        if (movieRemoved) {
            updateMovieFile();
        }
        return movieRemoved;
    }

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

    // Retrieves details of a movie


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
