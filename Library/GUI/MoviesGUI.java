package Library.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;
import Library.MovieData.Watchlist;
import Library.UserData.User;

public class MoviesGUI extends JFrame {
    private JPanel moviesPanel;
    private MovieDatabase movieDatabase;
    private User user; // Store the reference to the user

    public MoviesGUI(User user) {
        this.user = user; // Initialize the user reference
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        movieDatabase = new MovieDatabase();

        moviesPanel = new JPanel();
        moviesPanel.setLayout(new BoxLayout(moviesPanel, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS
        JScrollPane scrollPane = new JScrollPane(moviesPanel);

        // Add a "Watchlist" button
        JButton watchlistButton = new JButton("Watchlist");
        watchlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWatchlist();
            }
        });

        watchlistButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(watchlistButton);

        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        displayMovies();

        this.setVisible(true);
    }

    private void displayMovies() {
        // Clear the previous movie entries
        moviesPanel.removeAll();

        // Get a list of movies from the movieDatabase
        List<Movie> movies = movieDatabase.getMovies();
        Watchlist watchlist = new Watchlist();

        // Use GridLayout for consistent structure
        moviesPanel.setLayout(new GridLayout(movies.size(), 1, 10, 10));

        // Display each movie with buttons
        for (Movie movie : movies) {
            JPanel movieEntry = new JPanel(new GridLayout(1, 2, 10, 10));
            JLabel titleLabel = new JLabel(movie.getTitle());

            JButton viewButton = new JButton("View");
            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MovieDetailsGUI(movie, movie.getReleaseYear());
                }
            });

            JButton addToWatchList = new JButton("Add to watchlist");
            addToWatchList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    watchlist.addMovieToList(user.getUsername(), movie);
                    displayMovies();
                }
            });

            movieEntry.add(titleLabel);

            // Set focus painted to false for both buttons
            viewButton.setFocusPainted(false);
            addToWatchList.setFocusPainted(false);

            // Create a panel for buttons and add it to the movieEntry
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(viewButton);
            buttonPanel.add(addToWatchList);
            movieEntry.add(buttonPanel);

            movieEntry.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add space between lines

            moviesPanel.add(movieEntry);
        }

        // Refresh the panel to reflect the changes
        moviesPanel.revalidate();
        moviesPanel.repaint();
    }

    private void openWatchlist() {
        // Open the WatchlistGUI with the user's watchlist
        new WatchlistGUI(user);
    }

}
