package Library.GUIs;

import javax.swing.*;

import Library.MovieData.Movie;
import Library.MovieData.MovieDatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMovieGUI extends JFrame implements ActionListener {

    private static JLabel titleLabel;
    private static JTextField titleTextField;
    private static JLabel directorLabel;
    private static JTextField directorTextField;
    private static JLabel releaseYearLabel;
    private static JTextField releaseYearTextField;
    private static JLabel runningTimeLabel;
    private static JTextField runningTimeTextField;
    private static JButton addMovieButton;
    private static JLabel success;
    private static JPanel panel;

    public AddMovieGUI() {
        panel = new JPanel();
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);

        titleLabel = new JLabel("Title");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        titleTextField = new JTextField(20);
        titleTextField.setBounds(100, 20, 165, 25);
        panel.add(titleTextField);

        directorLabel = new JLabel("Director");
        directorLabel.setBounds(10, 50, 80, 25);
        panel.add(directorLabel);

        directorTextField = new JTextField();
        directorTextField.setBounds(100, 50, 165, 25);
        panel.add(directorTextField);

        releaseYearLabel = new JLabel("Release Year");
        releaseYearLabel.setBounds(10, 80, 80, 25);
        panel.add(releaseYearLabel);

        releaseYearTextField = new JTextField(20);
        releaseYearTextField.setBounds(100, 80, 165, 25);
        panel.add(releaseYearTextField);

        runningTimeLabel = new JLabel("Running Time");
        runningTimeLabel.setBounds(10, 110, 80, 25);
        panel.add(runningTimeLabel);

        runningTimeTextField = new JTextField();
        runningTimeTextField.setBounds(100, 110, 165, 25);
        panel.add(runningTimeTextField);

        addMovieButton = new JButton("Add movie");
        addMovieButton.setBounds(100, 140, 100, 25);
        addMovieButton.addActionListener(this);
        panel.add(addMovieButton);

        success = new JLabel("");
        success.setBounds(10, 170, 300, 25);
        panel.add(success);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String title = titleTextField.getText();
            String director = directorTextField.getText();
            String releaseYear = releaseYearTextField.getText();
            String runningTime = runningTimeTextField.getText();
            int releaseYearValue = Integer.parseInt(releaseYear);
            int runningTimeValue = Integer.parseInt(runningTime);

            new MovieDatabase();
            if (MovieDatabase.addMovie(new Movie(title, director, releaseYearValue, runningTimeValue))) {
                success.setText("Movie added successfully!");
            } else {
                success.setText("Movie already exists in the database.");
            }
        } catch (NumberFormatException ex) {
            success.setText("Invalid input. Please enter valid integer values for Release Year and Running Time.");
        }
    }

    public static void main(String[] args) {
        AddMovieGUI addMovieGUI = new AddMovieGUI();
    }
}
