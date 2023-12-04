package Library.MovieData;
import java.io.*;
import java.util.*;

public class MovieDatabase {
    private List<Movie> movies;
    private final String filePath = "./Resources/MovieDatabase.csv"; 
    public MovieDatabase() {
        movies = new ArrayList<>();
        loadMoviesFromFile();
    }

    // Loads movies from the CSV file into the movies list

    private void loadMoviesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] movieDetails = line.split(",");
                // Create a new Movie object from CSV data
                Movie movie = new Movie(movieDetails[0], movieDetails[1],
                        (int)Integer.parseInt(movieDetails[2]), (int)Integer.parseInt(movieDetails[3]));
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Adds a new movie to the database if it doesn't already exist

    public void addMovie(Movie newMovie) {
        if (!movieExists(newMovie)) {
            movies.add(newMovie);
            writeMovieToFile(newMovie);
        } else {
            System.out.println("Movie already exists in database.");
        }
    }
    
    // Check if a movie already exists in the database

    private boolean movieExists(Movie movie) {
        return movies.stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(movie.getTitle()) && m.getReleaseYear() == movie.getReleaseYear());
    }
    
    // Add movie's data to CSV file

    private void writeMovieToFile(Movie movie) {
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

    public void removeMovie(String title) {
        movies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
        updateMovieFile();
    }

    // Updates the CSV file

    private void updateMovieFile() {
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
    
    public Movie getMovieDetails(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

}
