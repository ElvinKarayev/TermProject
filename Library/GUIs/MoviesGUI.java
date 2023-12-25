import javax.swing.*;

import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;
import Library.MovieData.Watchlist;
import Library.UserData.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MoviesGUI extends JFrame {
    private JPanel moviesPanel;
    private MovieDatabase movieDatabase;

    public MoviesGUI(User user) {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        movieDatabase = new MovieDatabase();

        moviesPanel = new JPanel();
        moviesPanel.setLayout(new GridLayout(0, 5, 10, 10));
        JScrollPane scrollPane = new JScrollPane(moviesPanel);

        this.add(scrollPane, BorderLayout.CENTER);

        displayMovies(user);

        this.setVisible(true);
    }

    private void displayMovies(User user) {
        // Clear the previous movie entries
        moviesPanel.removeAll();

        // Get a list of movies from the movieDatabase
        List<Movie> movies = movieDatabase.getMovies();
        Watchlist watchlist = new Watchlist();

        // Display each movie with buttons
        for (Movie movie : movies) {
            JPanel movieEntry = new JPanel(new BorderLayout());
            JLabel titleLabel = new JLabel(movie.getTitle());

            JButton viewButton = new JButton("View");
            setButtonSize(viewButton, 80, 10);
            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MovieDetailsGUI(movie, movie.getReleaseYear());
                }
            });

            JButton addToWatchList = new JButton("Add to watchlist");
            setButtonSize(addToWatchList, 80, 10);
            addToWatchList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    watchlist.addMovieToList(user.getUsername(), movie);
                    displayMovies(user);
                }
            });

            movieEntry.add(titleLabel, BorderLayout.NORTH);
            movieEntry.add(viewButton, BorderLayout.WEST);
            movieEntry.add(addToWatchList, BorderLayout.EAST);

            moviesPanel.add(movieEntry);
        }

        // Refresh the panel to reflect the changes
        moviesPanel.revalidate();
        moviesPanel.repaint();
    }

    private void setButtonSize(JButton button, int width, int height) {
        button.setPreferredSize(new Dimension(width, height));
        button.setMinimumSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
    }

}
