package Library.GUIs;

import javax.swing.*;

import Library.MovieData.MovieDatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteMovieGUI extends JFrame implements ActionListener {

    private static JLabel titleLabel;
    private static JTextField titleTextField;
    private static JButton deleteMovieButton;
    private static JLabel success;
    private static JPanel panel;

    public DeleteMovieGUI() {
        panel = new JPanel();
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);

        titleLabel = new JLabel("Title");
        titleLabel.setBounds(10, 20, 80, 25);
        panel.add(titleLabel);

        titleTextField = new JTextField(20);
        titleTextField.setBounds(100, 20, 165, 25);
        panel.add(titleTextField);

        deleteMovieButton = new JButton("Delete movie");
        deleteMovieButton.setBounds(100, 60, 130, 25);
        deleteMovieButton.addActionListener(this);
        panel.add(deleteMovieButton);

        success = new JLabel("");
        success.setBounds(10, 170, 300, 25);
        panel.add(success);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = titleTextField.getText().trim();
        if (title.isEmpty()) {
            success.setText("Please enter a movie title.");
            return;
        }

        new MovieDatabase();
        if (MovieDatabase.removeMovie(title)) {
            success.setText("Movie removed successfully!");
        } else {
            success.setText("Movie does not exist.");
        }
    }

    public static void main(String[] args) {
        DeleteMovieGUI deleteMovieGUI = new DeleteMovieGUI();
    }
}
