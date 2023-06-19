package pagesResult;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import resources.Constant;
import resources.DbConnector;

/**
 * Represents the user page.
 */
public class UserPage extends JFrame implements ActionListener {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton buttonDelete = new JButton("Delete User");
    private String emailUserToDelete;

    /**
     * Constructs a UserPage object.
     *
     * @param userProfile the user profile data
     */
    public UserPage(String[] userProfile) {
        super("User");
        emailUserToDelete = userProfile[1];

        // Button Layout
        label1 = new JLabel();
        label1.setText("UserID: " + userProfile[0]);
        label1.setForeground(Color.WHITE);
        label1.setPreferredSize(new Dimension(400, 20));

        label2 = new JLabel();
        label2.setText("UserEmail: " + userProfile[1]);
        label2.setForeground(Color.WHITE);
        label2.setPreferredSize(new Dimension(400, 20));

        label3 = new JLabel();
        label3.setText("UserName: " + userProfile[2]);
        label3.setForeground(Color.WHITE);
        label3.setPreferredSize(new Dimension(400, 20));

        label4 = new JLabel();
        label4.setText("UserLastUse: " + userProfile[3]);
        label4.setForeground(Color.WHITE);
        label4.setPreferredSize(new Dimension(400, 20));

        // Create the button
        buttonDelete.setPreferredSize(new Dimension(400, 20));
        buttonDelete.setFocusable(false);
        buttonDelete.addActionListener(this);

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidth, Constant.AppHeight);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adding Components
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(buttonDelete);

        // Last things to make everything work
        this.setVisible(true); // Make frame visible
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Add a window listener to handle the close button action
        this.addWindowListener(new WindowAdapter() {
            /**
             * Handles the window closing event.
             *
             * @param e The window event.
             */
            @Override
            public void windowClosing(WindowEvent e) {
                // Close the specific page (dispose the JFrame)
                dispose();
            }
        });
    }

    /**
     * Handles the button click event.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDelete) {
            DbConnector app = new DbConnector();
            boolean deleteAction;
            deleteAction = app.deleteUser(emailUserToDelete);
            if (deleteAction) {
                JOptionPane.showMessageDialog(this, "Successfully deleted user", "User-Notification",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Unable to delete user", "User-Notification",
                        JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }
    }
}
