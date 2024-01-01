package Library.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;

public class AdminPanelGUI extends JFrame {
    private static JPanel moviesPanel;

    public AdminPanelGUI() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        JPanel buttonPanel = new JPanel(new BorderLayout());

        // Create a panel for the center containing the add movie button
        JPanel centerButtonPanel = new JPanel();
        centerButtonPanel.add(addButton);
        addButton.setFocusPainted(false);

        // Add both left and center panels to the main button panel

        buttonPanel.add(centerButtonPanel, BorderLayout.CENTER);

        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        displayMovies();

        this.setVisible(true);
    }

    protected static void displayMovies() {
        // Clear the previous movie entries
        moviesPanel.removeAll();

        // Get a list of movies from the movieDatabase
        List<Movie> movies = MovieDatabase.getMovies();

        // Use BorderLayout to place the movie entries at the top
        moviesPanel.setLayout(new BorderLayout());

        JPanel movieEntriesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Display each movie with buttons
        for (Movie movie : movies) {
            JPanel movieEntryPanel = new JPanel(new BorderLayout());
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
                    MovieDatabase.removeMovie(movie.getTitle());
                    displayMovies();
                }
            });

            JPanel titlePanel = new JPanel();
            titlePanel.add(titleLabel);
            movieEntryPanel.add(titlePanel, BorderLayout.WEST);

            // Set fixed height for the movie entry panel
            movieEntryPanel.setMinimumSize(new Dimension(0, 50)); // You can adjust the height as needed

            // Set focus painted to false for both buttons
            viewButton.setFocusPainted(false);
            deleteButton.setFocusPainted(false);

            // Create a panel for buttons and add it to the movieEntryPanel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(viewButton);
            buttonPanel.add(deleteButton);
            movieEntryPanel.add(buttonPanel, BorderLayout.EAST);

            gbc.gridy++;
            movieEntriesPanel.add(movieEntryPanel, gbc);
        }

        moviesPanel.add(movieEntriesPanel, BorderLayout.NORTH);

        // Refresh the panel to reflect the changes
        moviesPanel.revalidate();
        moviesPanel.repaint();
    }
}
