package Library.GUIs;

import javax.swing.*;

import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;

public class MovieDetailsGUI extends JFrame {
    private static JLabel titleLabel;
    private static JLabel titleTextLabel;
    private static JLabel directorLabel;
    private static JLabel directorTextLabel;
    private static JLabel releaseYearLabel;
    private static JLabel releaseYearTextLabel;
    private static JLabel runningTimeLabel;
    private static JLabel runningTimeTextLabel;
    private static JPanel panel;

    public MovieDetailsGUI(Movie movie) {
        new MovieDatabase();

        panel = new JPanel();
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);

        titleLabel = new JLabel("Title");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        titleTextLabel = new JLabel(MovieDatabase.getMovieDetails(movie.getTitle()).getTitle());
        titleTextLabel.setBounds(100, 20, 165, 25);
        panel.add(titleTextLabel);

        directorLabel = new JLabel("Director");
        directorLabel.setBounds(10, 50, 80, 25);
        panel.add(directorLabel);

        directorTextLabel = new JLabel(MovieDatabase.getMovieDetails(movie.getTitle()).getDirector());
        directorTextLabel.setBounds(100, 50, 165, 25);
        panel.add(directorTextLabel);

        releaseYearLabel = new JLabel("Release Year");
        releaseYearLabel.setBounds(10, 80, 80, 25);
        panel.add(releaseYearLabel);

        releaseYearTextLabel = new JLabel(
                Integer.toString(MovieDatabase.getMovieDetails(movie.getTitle()).getReleaseYear()));
        releaseYearTextLabel.setBounds(100, 80, 165, 25);
        panel.add(releaseYearTextLabel);

        runningTimeLabel = new JLabel("Running Time");
        runningTimeLabel.setBounds(10, 110, 80, 25);
        panel.add(runningTimeLabel);

        runningTimeTextLabel = new JLabel(
                Integer.toString(MovieDatabase.getMovieDetails(movie.getTitle()).getRunningTime()));
        runningTimeTextLabel.setBounds(100, 110, 165, 25);
        panel.add(runningTimeTextLabel);

        this.setVisible(true);
    }

}
