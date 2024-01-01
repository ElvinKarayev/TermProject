package Library.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Library.MovieData.Movie;
import Library.MovieData.Watchlist;
import Library.UserData.User;

public class WatchListGUI extends JFrame {
    private JPanel watchlistPanel;
    private User user; // Store the reference to the user

    public WatchListGUI(User user) {
        this.user = user; // Initialize the user reference
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        watchlistPanel = new JPanel();
        watchlistPanel.setLayout(new BoxLayout(watchlistPanel, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS
        JScrollPane scrollPane = new JScrollPane(watchlistPanel);

        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSortingOptions();
            }
        });

        sortButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sortButton);

        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        displayWatchlist();

        this.setVisible(true);
    }

    private void showSortingOptions() {
        // Create a dialog for sorting options
        JDialog sortingDialog = new JDialog(this, "Sorting Options", true);
        sortingDialog.setLayout(new FlowLayout());

        // Create a dropdown for sorting criteria
        String[] sortingOptions = { "Release Year", "Title" };
        JComboBox<String> sortingComboBox = new JComboBox<>(sortingOptions);

        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Movie> sortedWatcList = sortWatchlist((String) sortingComboBox.getSelectedItem());
                displayWatchlist(sortedWatcList);
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

    private List<Movie> sortWatchlist(String sortingCriteria) {
        List<Movie> movies = Watchlist.getWatchlist(user.getUsername());

        // Use Stream API for sorting based on the chosen criteria
        switch (sortingCriteria) {
            case "Release Year":
                // movies.sort(Comparator.comparing(Movie::getReleaseYear));
                Collections.sort(movies, new Comparator<Movie>() {

                    @Override
                    public int compare(Movie o1, Movie o2) {
                        return o1.getReleaseYear() - o2.getReleaseYear();
                    }

                });
                break;
            case "Title":
                movies.sort(Comparator.comparing(Movie::getTitle));
                break;
        }

        // After updating the watchlist, display it again with the sorted movies
        displayWatchlist(movies);
        return movies;
    }

    private void displayWatchlist(List<Movie> userWatchlist) {
        // Clear the previous watchlist entries
        watchlistPanel.removeAll();

        // Use BorderLayout to place the watchlist entries at the top
        watchlistPanel.setLayout(new BorderLayout());

        JPanel watchlistEntriesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Display each movie in the watchlist
        for (Movie movie : userWatchlist) {
            JPanel watchlistEntryPanel = new JPanel(new BorderLayout());
            JLabel titleLabel = new JLabel(movie.getTitle());

            JButton viewButton = new JButton("View");
            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MovieDetailsGUI(movie, movie.getReleaseYear());
                }
            });

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle remove button click
                    Watchlist.removeMovieFromWatchlist(user.getUsername(), movie);
                    displayWatchlist(); // Refresh the watchlist after removing
                }
            });

            JPanel titlePanel = new JPanel();
            titlePanel.add(titleLabel);
            watchlistEntryPanel.add(titlePanel, BorderLayout.WEST);

            // Set fixed height for the watchlist entry panel
            watchlistEntryPanel.setMinimumSize(new Dimension(0, 50));

            // Set focus painted to false for buttons
            viewButton.setFocusPainted(false);
            removeButton.setFocusPainted(false);

            // Create a panel for buttons and add it to the watchlistEntryPanel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(viewButton);
            buttonPanel.add(removeButton);
            watchlistEntryPanel.add(buttonPanel, BorderLayout.EAST);

            gbc.gridy++;
            watchlistEntriesPanel.add(watchlistEntryPanel, gbc);
        }

        // Calculate total watch time using the Stream API
        int totalWatchTime = userWatchlist.stream()
                .mapToInt(Movie::getRunningTime)
                .sum();

        // Display total watch time (you can customize this part)
        displayTotalWatchTime(totalWatchTime);

        watchlistPanel.add(watchlistEntriesPanel, BorderLayout.NORTH);

        // Refresh the panel to reflect the changes
        watchlistPanel.revalidate();
        watchlistPanel.repaint();
    }

    // Call this method initially to display the watchlist
    private void displayWatchlist() {
        List<Movie> userWatchlist = Watchlist.getWatchlist(user.getUsername());
        displayWatchlist(userWatchlist);
    }

    private void displayTotalWatchTime(int totalWatchTime) {
        // Example: Display total watch time at the bottom of the panel
        JLabel totalWatchTimeLabel = new JLabel("Total Watch Time: " + totalWatchTime + " minutes");
        totalWatchTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        watchlistPanel.add(totalWatchTimeLabel);
    }
}
