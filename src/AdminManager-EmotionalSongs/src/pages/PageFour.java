package pages;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import resources.Constant;

/**
 * Represents the page for executing custom queries.
 */
public class PageFour extends JFrame implements ActionListener {

    private JButton buttonBack = new JButton("Return Home");
    private JButton buttonExecute = new JButton("Execute Query");
    private JTextField queryField = new JTextField();

    /**
     * Constructs a PageFour object.
     */
    public PageFour() {
        super("EmotionalSongs Custom-Query");

        // Button Layout
        queryField.setPreferredSize(new Dimension(400, 200));
        queryField.setForeground(Constant.TextColor);

        buttonBack.setFocusable(false);
        buttonBack.addActionListener(this);

        buttonExecute.setFocusable(false);
        buttonExecute.addActionListener(this);

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidth, Constant.AppHeight);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adding Components
        this.add(queryField);
        this.add(buttonExecute);
        this.add(buttonBack);

        // Last things to make everything work
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            /**
             * Handles the window closing event.
             *
             * @param e The window event.
             */
            @Override
            public void windowClosing(WindowEvent e) {
                // Perform your specific action here, such as returning to a specific page
                // For example, you could create a new instance of the desired page and display it
                // Replace "SpecificPage" with the actual class name of the specific page you want to return to
                new MainPage();

                // Dispose the current JFrame
                dispose();
            }
        });
    }

    /**
     * Handles the action performed event.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonBack) {
            this.dispose();
            new MainPage();
        }
        if (e.getSource() == buttonExecute) {
            String queryMadeByUser = queryField.getText();

            if (queryMadeByUser.contains("SELECT")) {
                // Perform SELECT operation
            }
            if (queryMadeByUser.contains("INSERT")) {
                // Perform INSERT operation
            }
            if (queryMadeByUser.contains("UPDATE")) {
                // Perform UPDATE operation
            }
            if (queryMadeByUser.contains("DELETE")) {
                // Perform DELETE operation
            } else {
                JOptionPane.showMessageDialog(this, "You are not able to perform this action", "CustomQuery - info", JOptionPane.ERROR_MESSAGE);
            }

            this.dispose();
            new MainPage();
        }
    }
}
