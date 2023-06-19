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
import resources.*;

/**
 * Represents the registration page.
 */
public class RegisterPage extends JFrame implements ActionListener {

    // Regular expression pattern for email validation
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private final Pattern emailRegex = Pattern.compile(EMAIL_PATTERN);
    private Matcher emailMatcher;

    private JTextField nameField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton buttonRegister = new JButton("Register");
    private JLabel insertName = new JLabel();
    private JLabel insertEmail = new JLabel();
    private JLabel insertPassword = new JLabel();
    private Dimension buttonSize = new Dimension(200, 25);

    /**
     * Constructs a new RegisterPage object.
     */
    public RegisterPage() {
        super("EmotionalSongs - Register");

        // Set the dimensions and colors of components
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

        // Configure the window properties
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidth, Constant.AppHeight);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Add components to the window
        this.add(insertName); // Add label for name field
        this.add(nameField); // Add text field for name input

        this.add(insertEmail); // Add label for email field
        this.add(emailField); // Add text field for email input

        this.add(insertPassword); // Add label for password field
        this.add(passwordField); // Add text field for password input

        this.add(buttonRegister); // Add register button

        // Set the window to be visible and handle the close button action
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    /**
     * Handles the button click event.
     *
     * @param e the ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonRegister) {
            DbConnector dbConnector = new DbConnector();
            boolean registrationSuccess = true;
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
                    try {
                        String serverAddress = "localhost";
                        int serverPort = 1234;
                        Client client = new Client(serverAddress, serverPort);
                        String action = "Register";
                        client.startSendLogin(name, email, password, action);
                        client.close();
                    } catch (Exception eee) {
                        System.out.println("Unable to connect, make sure to start the server");
                        this.dispose();
                    }

                    if (registrationSuccess) {
                        JOptionPane.showMessageDialog(this, "Successfully registered", "Registration Notification",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Registration failed", "Registration Notification",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User already exists. Please log in",
                            "Registration Notification",
                            JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                }
            }
        }
    }
}
