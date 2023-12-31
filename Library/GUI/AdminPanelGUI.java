package Library.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;

public class AdminPanelGUI extends JFrame {
    private JPanel moviesPanel;
    private MovieDatabase movieDatabase;

    public AdminPanelGUI() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        movieDatabase = new MovieDatabase();

        moviesPanel = new JPanel();
        moviesPanel.setLayout(new BoxLayout(moviesPanel, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS
        JScrollPane scrollPane = new JScrollPane(moviesPanel);

        JButton addButton = new JButton("Add Movie");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a dialog to add a new movie
                new AddMovieGUI();
                displayMovies();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        addButton.setFocusPainted(false);

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

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    movieDatabase.removeMovie(movie.getTitle());
                    displayMovies();
                }
            });

            movieEntry.add(titleLabel);

            // Set focus painted to false for both buttons
            viewButton.setFocusPainted(false);
            deleteButton.setFocusPainted(false);

            // Create a panel for buttons and add it to the movieEntry
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(viewButton);
            buttonPanel.add(deleteButton);
            movieEntry.add(buttonPanel);

            movieEntry.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add space between lines

            moviesPanel.add(movieEntry);
        }

        // Refresh the panel to reflect the changes
        moviesPanel.revalidate();
        moviesPanel.repaint();
    }

    public static void main(String[] args) {
        AdminPanelGUI adminPanel = new AdminPanelGUI();
    }
}
