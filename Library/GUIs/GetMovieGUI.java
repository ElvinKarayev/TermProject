package Library.GUIs;

import javax.swing.*;

import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetMovieGUI extends JFrame implements ActionListener {

    private static JLabel titleLabel;
    private static JTextField titleTextField;
    private static JButton getMovieButton;
    private static JLabel success;
    private static JPanel panel;
    private static MovieDatabase movieDatabase;

    public GetMovieGUI() {
        panel = new JPanel();
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);

        titleLabel = new JLabel("Title");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        titleTextField = new JTextField(20);
        titleTextField.setBounds(100, 20, 165, 25);
        panel.add(titleTextField);

        getMovieButton = new JButton("Get movie");
        getMovieButton.setBounds(100, 60, 130, 25);
        getMovieButton.addActionListener(this);
        panel.add(getMovieButton);

        success = new JLabel("");
        success.setBounds(10, 170, 300, 25);
        panel.add(success);

        movieDatabase = new MovieDatabase();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = titleTextField.getText();
        Movie movie = MovieDatabase.getMovieDetails(title);
        if (movie != null) {
            MovieDetailsGUI movieDetailsGUI = new MovieDetailsGUI(movie);
        } else {
            success.setText("Movie not found!");
        }
    }

    public static void main(String[] args) {
        GetMovieGUI getMovieGUI = new GetMovieGUI();
    }
}
