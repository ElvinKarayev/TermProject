package Library.MovieData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class Watchlist {
    private static String pathToList = "./Resources/Watchlist.csv";

    public static void addMovieToList(String user, Movie user_movie) {
        try {
            if (MovieDatabase.movieExists(user_movie)) {
                updateWatchlist(user, user_movie);
            } else {
                throw new MovieNotExist();
            }
        } catch (MovieNotExist e) {
            System.out.println("Movie Doesn't exist");
        }

    }

    public static void removeMovieFromWatchlist(String user, Movie movie) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToList))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(user + '-')) {
                    String removedMovieIfFirst = String.format("[Movie title='%s', director='%s', releaseYear='%d']",
                            movie.getTitle(), movie.getDirector(), movie.getReleaseYear());
                    String removedMovie = String.format(",[Movie title='%s', director='%s', releaseYear='%d']",
                            movie.getTitle(), movie.getDirector(), movie.getReleaseYear());
                    int indexOf = line.indexOf(removedMovie);
                    int indexofUserLastLetter = line.indexOf('-');
                    if (indexOf == indexofUserLastLetter + 1) {
                        line.replace(removedMovieIfFirst, "");
                    } else {
                        line.replace(removedMovieIfFirst, "");
                    }

                }
                lines.add(line);
            }
        } catch (Exception e) {
        }
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(pathToList))) {
            for (String line : lines) {
                wr.write(line);
                wr.newLine();
            }

        } catch (Exception e) {
        }
    }

    public static void updateWatchlist(String user, Movie movie) {
        ArrayList<String> lines = new ArrayList<>();
        boolean userFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(pathToList))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(user + "-")) {
                    line += String.format(", [Movie title='%s', director='%s', releaseYear='%d', runningTime='%d']",
                            movie.getTitle(), movie.getDirector(), movie.getReleaseYear(), movie.getRunningTime());
                    userFound = true;
                }
                lines.add(line);
            }
        } catch (Exception e) {

        }

        // If the user was not found, add a new entry for the user
        if (!userFound) {
            String newEntry = String.format("%s-[Movie title='%s', director='%s', releaseYear='%d', runningTime='%d']",
                    user, movie.getTitle(), movie.getDirector(), movie.getReleaseYear(), movie.getRunningTime());
            lines.add(newEntry);
        }

        // Rewrite the file with the updated lines
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathToList))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {

        }
    }

}
