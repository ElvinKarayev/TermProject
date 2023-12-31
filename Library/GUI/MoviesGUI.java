package Library.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Comparator;
import java.util.List;

import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;
import Library.MovieData.Watchlist;
import Library.UserData.User;

public class MoviesGUI extends JFrame {
    private JPanel moviesPanel;
    private User user; // Store the reference to the user

    public MoviesGUI(User user) {
        this.user = user; // Initialize the user reference
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        moviesPanel = new JPanel();
        moviesPanel.setLayout(new BoxLayout(moviesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(moviesPanel);

        // Add a "Watchlist" button
        JButton watchlistButton = new JButton("Watchlist");
        watchlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWatchlist();
            }
        });

        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSortingOptions();
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current AdminPanelGUI and open the LoginGUI
                dispose(); // Close the current frame
                new LoginGUI();
            }
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());

        // A panel for the center containing the add movie button and the sort button

        JPanel centerButtonPanel = new JPanel();
        centerButtonPanel.add(watchlistButton);
        watchlistButton.setFocusPainted(false);

        // A panel for the right containing the logout button
        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtonPanel.add(logoutButton);
        logoutButton.setFocusPainted(false);

        // A panel for the left containing the sort button
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftButtonPanel.add(sortButton);
        sortButton.setFocusPainted(false);

        // Add left, center, and right panels to the main button panel
        buttonPanel.add(centerButtonPanel, BorderLayout.CENTER);
        buttonPanel.add(rightButtonPanel, BorderLayout.AFTER_LINE_ENDS);
        buttonPanel.add(leftButtonPanel, BorderLayout.BEFORE_LINE_BEGINS);

        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        displayMovies();

        this.setVisible(true);
    }

    private void displayMovies() {
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

            JButton addToWatchList = new JButton("Add to watchlist");
            addToWatchList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = Watchlist.addMovieToList(user.getUsername(), movie);
                    displayMovies();

                    if (result == -1) {
                        displaySuccessMessage("Movie has already been added to the watchlist.");
                    } else if (result == -2) {
                        displaySuccessMessage("Movie doesn't exist.");
                    } else if (result == 1) {
                        displaySuccessMessage("Movie added successfully");
                    }
                }
            });

            JPanel titlePanel = new JPanel();
            titlePanel.add(titleLabel);
            movieEntryPanel.add(titlePanel, BorderLayout.WEST);

            // Set fixed height for the movie entry panel
            movieEntryPanel.setMinimumSize(new Dimension(0, 50)); // You can adjust the height as needed

            // Set focus painted to false for both buttons
            viewButton.setFocusPainted(false);
            addToWatchList.setFocusPainted(false);

            // A panel for buttons and add it to the movieEntryPanel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(viewButton);
            buttonPanel.add(addToWatchList);
            movieEntryPanel.add(buttonPanel, BorderLayout.EAST);

            gbc.gridy++;
            movieEntriesPanel.add(movieEntryPanel, gbc);
        }

        moviesPanel.add(movieEntriesPanel, BorderLayout.NORTH);

        // Refresh the panel to reflect the changes
        moviesPanel.revalidate();
        moviesPanel.repaint();
    }

    private void openWatchlist() {
        // Open the WatchlistGUI with the user's watchlist
        new WatchListGUI(user);
    }

    private void displaySuccessMessage(String message) {
        // A custom dialog with an "OK" button
        JDialog dialog = new JDialog(this, "Success", true);
        dialog.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        dialog.add(messageLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");

        // Associate the "OK" button with the default button behavior
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        // Set focus painted to false for the "OK" button
        okButton.setFocusPainted(false);

        // Add an Action to the "OK" button for Enter key press
        okButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "OK");
        okButton.getActionMap().put("OK", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButton.doClick();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showSortingOptions() {
        // A dialog for sorting options
        JDialog sortingDialog = new JDialog(this, "Sorting Options", true);
        sortingDialog.setLayout(new FlowLayout());

        // A dropdown for sorting criteria
        String[] sortingOptions = { "Release Year", "Title", "Runtime" };
        JComboBox<String> sortingComboBox = new JComboBox<>(sortingOptions);

        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortMovies((String) sortingComboBox.getSelectedItem());
                sortingDialog.dispose();
            }
        });

        sortingDialog.add(new JLabel("Sort by: "));
        sortingDialog.add(sortingComboBox);
        sortingDialog.add(sortButton);

        sortingDialog.setSize(300, 150);
        sortingDialog.setLocationRelativeTo(this);
        sortingDialog.setVisible(true);
    }

    private void sortMovies(String sortingCriteria) {
        List<Movie> movies = MovieDatabase.getMovies();

        // Use Stream API for sorting based on the chosen criteria
        switch (sortingCriteria) {
            case "Release Year":
                movies.sort(Comparator.comparing(Movie::getReleaseYear));
                break;
            case "Title":
                movies.sort(Comparator.comparing(Movie::getTitle));
                break;
            case "Runtime":
                movies.sort(Comparator.comparing(Movie::getRunningTime));
                break;
        }

        // After updating the watchlist, display it again with the sorted movies
        displayMovies();
    }

}
