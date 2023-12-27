package Library.GUI;
import javax.swing.*;

import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminPanelGUI extends JFrame {
    private JPanel moviesPanel;
    private MovieDatabase movieDatabase;

    public AdminPanelGUI() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        movieDatabase = new MovieDatabase();

        moviesPanel = new JPanel();
        moviesPanel.setLayout(new GridLayout(0, 5, 10, 10));
        JScrollPane scrollPane = new JScrollPane(moviesPanel);

        JButton addButton = new JButton("Add Movie");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a dialog to add a new movie
                new AddMovieGUI();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

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

            JButton deleteButton = new JButton("Delete");
            setButtonSize(deleteButton, 80, 10);
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    movieDatabase.removeMovie(movie.getTitle());
                    displayMovies();
                }
            });

            movieEntry.add(titleLabel, BorderLayout.NORTH);
            movieEntry.add(viewButton, BorderLayout.WEST);
            movieEntry.add(deleteButton, BorderLayout.EAST);

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

    public static void main(String[] args) {
        AdminPanelGUI adminPanel = new AdminPanelGUI();
    }
}
