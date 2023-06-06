package pages;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import resources.Constant;
import resources.DbConnector;

/**
 * Represents the login page.
 */
public class LoginPage extends JFrame implements ActionListener {

    private JTextField emailField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton buttonSubmit = new JButton("Submit");
    private JLabel imageLabel = new JLabel(Constant.AppLogo);

    /**
     * Constructs a LoginPage object.
     */
    public LoginPage() {
        super("EmotionalSongs Admin-Panel");

        // Button/TextField/Images Layout
        emailField.setPreferredSize(new Dimension(200, 25));
        emailField.setForeground(Constant.TextColor);

        passwordField.setPreferredSize(new Dimension(200, 25));
        passwordField.setForeground(Constant.TextColor);

        buttonSubmit.addActionListener(this);

        imageLabel.setPreferredSize(new Dimension(120, 230));

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidth, Constant.AppHeight);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adding Components
        this.add(imageLabel);
        this.add(emailField);
        this.add(passwordField);
        this.add(buttonSubmit);

        // Last things to make everything work
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Handles the action performed event.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSubmit) {
            DbConnector app = new DbConnector();
            boolean success = app.getAdminInfo(emailField.getText(), passwordField.getText());

            if (success) {

                JOptionPane.showMessageDialog(this, "Successfully logged in", "Login Notification",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new MainPage();
            } else {
                JOptionPane.showMessageDialog(this, "Wrong login credentials", "Login Notification",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
