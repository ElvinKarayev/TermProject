package Library.MovieData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class Watchlist {
    private static String pathToList = "./Resources/Watchlist.csv";

    public static int addMovieToList(String user, Movie user_movie) {
        if (getWatchlist(user).contains(user_movie)) {

            try {
                throw new MovieAlreadyAdded();
            } catch (MovieAlreadyAdded e) {
                return -1;
            }
        } else {
            try {
                if (MovieDatabase.movieExists(user_movie)) {
                    updateWatchlist(user, user_movie);
                } else {
                    throw new movieNotExist();
                }
            } catch (movieNotExist e) {
                return -2;
            }
        }
        return 1;
    }

    public static void removeMovieFromWatchlist(String user, Movie movie) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToList))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(user + '-')) {
                    String removedMovie = String.format(
                            "[Movie title='%s', director='%s', releaseYear='%d', runningTime='%d']",
                            movie.getTitle(), movie.getDirector(), movie.getReleaseYear(), movie.getRunningTime());
                    int indexOf = line.indexOf(removedMovie);
                    int indexofUserLastLetter = line.indexOf('-');
                    int ifmovieislast = line.indexOf(removedMovie);

                    if (indexOf == indexofUserLastLetter + 1) {
                        line = line.replace(removedMovie, "");
                        line = line.replace("-,", "-");
                    } else {
                        // this if statement checks whether our movie is the last one in our watchlist
                        // data
                        if (ifmovieislast + removedMovie.length() == line.length()) {

                            line = line.replace("," + removedMovie, "");
                        }
                        // and this one is for middle movies in watchlist storing for example we have
                        // movie stored like that [movie1],[another movie],[another movie 2]
                        // in order for this to be like [movie1],[another movie2]
                        else {
                            line = line.replace(removedMovie, "");
                            line = line.replace(",,", ",");
                        }
                    }
                    if (!line.contains("[")) {
                        continue;
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
                    line += String.format(",[Movie title='%s', director='%s', releaseYear='%d', runningTime='%d']",
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

    public static LinkedList<Movie> getWatchlist(String user) {
        LinkedList<Movie> userWatchlist = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToList))) {
            String line;
            int flag = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(user + '-')) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                throw new WatchlistNotExist();
            }
            String[] Movies = line.substring(line.indexOf("-") + 1).split("],\\[");
            String[] MovieDetails = Movies[0].split("'");
            String Title;
            String director;
            int releaseYear;
            int runningTime;
            for (String movie : Movies) {
                MovieDetails = movie.split("'");
                Title = MovieDetails[1];
                director = MovieDetails[3];
                releaseYear = Integer.parseInt(MovieDetails[5]);
                runningTime = Integer.parseInt(MovieDetails[7]);
                userWatchlist.add(new Movie(Title, director, releaseYear, runningTime));
            }
        } catch (WatchlistNotExist e) {
            // System.out.println("Your watchlist is empty");
        } catch (IOException e) {
            System.out.println("there was an error while reading your Watchlist");
        }
        return userWatchlist;
    }
}
