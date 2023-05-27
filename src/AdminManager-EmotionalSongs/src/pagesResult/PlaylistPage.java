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
 * Represents the playlist page in a GUI application.
 */
public class PlaylistPage extends JFrame implements ActionListener {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JButton buttonDelete = new JButton("Delete Playlist");
    private String emailUserToDelete;

    /**
     * Creates a new instance of PlaylistPage.
     *
     * @param playlistProfile The playlist profile information.
     */
    public PlaylistPage(String[] playlistProfile) {
        super("Playlist");
        emailUserToDelete = playlistProfile[1];

        // Label Layout
        label1 = new JLabel();
        label1.setText("PlaylistId: " + playlistProfile[0]);
        label1.setForeground(Color.WHITE);
        label1.setPreferredSize(new Dimension(400, 20));

        label2 = new JLabel();
        label2.setText("PlaylistAuthor: " + playlistProfile[1]);
        label2.setForeground(Color.WHITE);
        label2.setPreferredSize(new Dimension(400, 20));

        label3 = new JLabel();
        label3.setText("SongList: " + playlistProfile[2]);
        label3.setForeground(Color.WHITE);
        label3.setPreferredSize(new Dimension(400, 20));

        label4 = new JLabel();
        label4.setText("PlaylistName: " + playlistProfile[3]);
        label4.setForeground(Color.WHITE);
        label4.setPreferredSize(new Dimension(400, 20));

        label5 = new JLabel();
        label5.setText("PlaylistCreation: " + playlistProfile[4]);
        label5.setForeground(Color.WHITE);
        label5.setPreferredSize(new Dimension(400, 20));

        // Create the delete button
        buttonDelete.setPreferredSize(new Dimension(400, 20));
        buttonDelete.setFocusable(false);
        buttonDelete.addActionListener(this);

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidth, Constant.AppHeight);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Add components
        this.add(label1);
        this.add(label2);
        this.add(label4);
        this.add(label5);
        this.add(label3);
        this.add(buttonDelete);

        // Make frame visible
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
     * Handles the action performed event for the delete button.
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
                JOptionPane.showMessageDialog(this, "Playlist successfully deleted", "User-Notification",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Unable to delete selected Playlist", "User-Notification",
                        JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        }
    }
}
