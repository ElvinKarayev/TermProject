package Library.GUIs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMovieGUI implements ActionListener {

    private static JLabel titleLabel;
    private static JTextField titleTextField;
    private static JLabel directorLabel;
    private static JTextField directorTextField;
    private static JLabel releaseYearLabel;
    private static JTextField releaseYearTextField;
    private static JLabel runningTimeLabel;
    private static JTextField runningTimeTextField;
    private static JButton updateMovieButton;
    private static JLabel success;

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

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

        updateMovieButton = new JButton("Update movie");
        updateMovieButton.setBounds(100, 140, 100, 25);
        updateMovieButton.addActionListener(new AddMovieGUI());
        panel.add(updateMovieButton);

        success = new JLabel("");
        success.setBounds(10, 170, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = titleTextField.getText();
        String director = directorTextField.getText();
        String releaseYear = releaseYearTextField.getText();
        String runningTime = runningTimeTextField.getText();
        if (title.equals("Inception") && director.equals("Christopher Nolan") && releaseYear.equals("2010")
                && runningTime.equals("148"))
            success.setText("Movie added successfully!");
        else
            success.setText("This movie already added!");
    }
}
