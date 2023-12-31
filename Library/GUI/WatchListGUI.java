package Library.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Library.MovieData.Movie;
import Library.MovieData.Watchlist;
import Library.UserData.User;

public class WatchlistGUI extends JFrame {
    private JPanel watchlistPanel;
    private Watchlist watchlist;

    public WatchlistGUI(User user) {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        watchlist = new Watchlist();

        watchlistPanel = new JPanel();
        watchlistPanel.setLayout(new BoxLayout(watchlistPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(watchlistPanel);

        this.add(scrollPane, BorderLayout.CENTER);

        displayWatchlist(user);

        this.setVisible(true);
    }

    private void displayWatchlist(User user) {
        // Clear the previous watchlist entries
        watchlistPanel.removeAll();

        // Get the user's watchlist from the Watchlist class
        List<Movie> userWatchlist = watchlist.getWatchlist(user.getUsername());

        // Use GridLayout for consistent structure
        watchlistPanel.setLayout(new GridLayout(userWatchlist.size(), 1, 10, 10));

        // Display each movie in the user's watchlist with "Remove from Watchlist" button
        for (Movie movie : userWatchlist) {
            JPanel movieEntry = new JPanel(new GridLayout(1, 2, 10, 10));
            JLabel titleLabel = new JLabel(movie.getTitle());

            JButton removeFromWatchlistButton = new JButton("Remove from Watchlist");
            removeFromWatchlistButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    watchlist.removeMovieFromWatchlist(user.getUsername(), movie);
                    displayWatchlist(user);
                }
            });

            movieEntry.add(titleLabel);

            // Set focus painted to false for the button
            removeFromWatchlistButton.setFocusPainted(false);

            // Create a panel for the button and add it to the movieEntry
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(removeFromWatchlistButton);
            movieEntry.add(buttonPanel);

            movieEntry.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add space between lines

            watchlistPanel.add(movieEntry);
        }

        // Refresh the panel to reflect the changes
        watchlistPanel.revalidate();
        watchlistPanel.repaint();
    }
}
