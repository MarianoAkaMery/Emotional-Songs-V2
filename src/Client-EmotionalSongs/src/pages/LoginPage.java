package pages;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import pagesResult.RegisterPage;
import resources.Constant;
import resources.DbConnector;
import resources.User;

/**
 * Represents the login page of the application.
 */
public class LoginPage extends JFrame implements ActionListener {

    private JTextField emailField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton buttonSubmit = new JButton("Submit");
    private JButton buttonRegister = new JButton("Register");
    private JButton buttonGuest = new JButton("Guest-Login");
    private JLabel imageLabel = new JLabel(Constant.AppLogo);
    private JLabel spacer = new JLabel();

    /**
     * Constructs a LoginPage object.
     */
    public LoginPage() {
        super("EmotionalSongs");

        // Button/TextField/Images Layout
        emailField.setPreferredSize(new Dimension(180, 25));
        emailField.setForeground(Constant.TextColor);

        passwordField.setPreferredSize(new Dimension(180, 25));
        passwordField.setForeground(Constant.TextColor);

        spacer.setText("");
        spacer.setPreferredSize(new Dimension(700, 10));

        buttonSubmit.addActionListener(this);
        buttonRegister.addActionListener(this);
        buttonGuest.addActionListener(this);

        imageLabel.setPreferredSize(new Dimension(600, 230));

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidth, Constant.AppHeight);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 5));

        // Adding Components
        this.add(imageLabel);
        this.add(spacer);
        this.add(emailField);
        this.add(passwordField);
        this.add(buttonSubmit);
        this.add(spacer);
        this.add(buttonGuest);
        this.add(buttonRegister);

        // Last things to make everything work
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Handles the button click events and performs the corresponding actions.
     *
     * @param e The ActionEvent object representing the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSubmit) {
            DbConnector app = new DbConnector();
            boolean success = app.getLoginStatus(emailField.getText(), passwordField.getText());

            System.out.println(success);

            if (success) {
                JOptionPane.showMessageDialog(this, "Successfully logged in", "Login Notification",
                        JOptionPane.INFORMATION_MESSAGE);

                app.updateLastUse(emailField.getText());

                LocalDate currentDate = LocalDate.now();
                Date date = Date.valueOf(currentDate);

                String[] userInfo = new String[4];
                userInfo = app.getSingleUserInfo(emailField.getText());

                User loggedUser = new User(userInfo[0], userInfo[1], userInfo[2], userInfo[3], date);

                this.dispose();
                new MainUser(loggedUser);

            } else {
                JOptionPane.showMessageDialog(this, "Wrong login credentials", "Login Notification",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == buttonGuest) {
            this.dispose();
            new MainGuest();
        }
        if (e.getSource() == buttonRegister) {
            new RegisterPage();
        }
    }
}
