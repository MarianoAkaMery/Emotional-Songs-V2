package pages;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import resources.Constant;

/**
 * Represents the main page of the admin panel.
 */
public class MainGuest extends JFrame implements ActionListener {

    private JButton button1 = new JButton("Users");
    private JButton button2 = new JButton("Songs");
    private JButton button3 = new JButton("Playlists");
    private JButton button4 = new JButton("Custom Commands");

    /**
     * Constructs a MainPage object.
     */
    public MainGuest() {
        super("EmotionalSongs Admin-Panel");

        // Button Layout
        button1.setBounds(200, 100, 200, 50);
        button1.setFocusable(false);
        button1.addActionListener(this);

        button2.setBounds(200, 100, 200, 50);
        button2.setFocusable(false);
        button2.addActionListener(this);

        button3.setBounds(200, 100, 200, 50);
        button3.setFocusable(false);
        button3.addActionListener(this);

        button4.setBounds(200, 100, 200, 50);
        button4.setFocusable(false);
        button4.addActionListener(this);

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidth, Constant.AppHeight);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adding Components
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);

        // Last things to make everything work
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
         * if (e.getSource() == button1) {
         * this.dispose();
         * new PageOne();
         * }
         * if (e.getSource() == button2) {
         * this.dispose();
         * new PageTwo();
         * }
         * if (e.getSource() == button3) {
         * this.dispose();
         * new PageThree();
         * }
         * if (e.getSource() == button4) {
         * this.dispose();
         * new PageFour();
         * }
         */
    }
}
