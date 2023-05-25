package pagesResult;

import java.util.regex.*;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import resources.Constant;
import resources.DbConnector;

/**
 * Represents the registration page.
 */
public class RegisterPage extends JFrame implements ActionListener {

    // Regular expression pattern for email validation
    String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    Pattern emailRegex = Pattern.compile(emailPattern);
    Matcher emailMatcher;

    private JTextField nameField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton buttonRegister = new JButton("Register");
    private JLabel insertName = new JLabel();
    private JLabel insertEmail = new JLabel();
    private JLabel insertPassword = new JLabel();
    Dimension buttonSize = new Dimension(200, 25);

    /**
     * Constructs a RegisterPage object.
     */
    public RegisterPage() {
        super("EmotionalSongs - Register");

        // Button/TextField/Images Layout
        nameField.setPreferredSize(new Dimension(500, 25));
        nameField.setForeground(Constant.TextColor);

        emailField.setPreferredSize(new Dimension(500, 25));
        emailField.setForeground(Constant.TextColor);

        passwordField.setPreferredSize(new Dimension(500, 25));
        passwordField.setForeground(Constant.TextColor);

        insertName.setText("Username: ");
        insertName.setForeground(Color.WHITE);
        insertName.setPreferredSize(new Dimension(500, 20));

        insertEmail.setText("Email: ");
        insertEmail.setForeground(Color.WHITE);
        insertEmail.setPreferredSize(new Dimension(500, 20));

        insertPassword.setText("Password: ");
        insertPassword.setForeground(Color.WHITE);
        insertPassword.setPreferredSize(new Dimension(500, 20));

        buttonRegister.addActionListener(this);
        buttonRegister.setPreferredSize(buttonSize);

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidth, Constant.AppHeight);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adding Components
        this.add(insertName);
        this.add(nameField);

        this.add(insertEmail);
        this.add(emailField);

        this.add(insertPassword);
        this.add(passwordField);

        this.add(buttonRegister);

        // Last things to make everything work
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add a window listener to handle the close button action
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Close the specific page (dispose the JFrame)
                dispose();
            }
        });
    }

    /**
     * Handles the button click event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonRegister) {

            DbConnector dbConnector = new DbConnector();
            boolean registrationSuccess;
            boolean userExists;
            String email = emailField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            emailMatcher = emailRegex.matcher(email);

            if (!emailMatcher.matches() || password.isBlank() || name.isBlank()) {
                JOptionPane.showMessageDialog(this, "Please check login information", "Registration Notification",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                userExists = dbConnector.checkUserExist(emailField.getText());

                if (!userExists) {
                    registrationSuccess = dbConnector.registerUser(email, password, name);

                    if (registrationSuccess) {
                        JOptionPane.showMessageDialog(this, "Successfully registered", "Registration Notification",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Registration failed", "Registration Notification",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User already exists. Please log in", "Registration Notification",
                            JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                }
            }
        }
    }
}
