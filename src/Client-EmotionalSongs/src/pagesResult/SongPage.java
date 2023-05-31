package pagesResult;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import resources.Constant;

/**
 * Represents the song page in a GUI application.
 */
public class SongPage extends JFrame {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel labelSpace;

    /**
     * Creates a new instance of SongPage.
     *
     * @param songProfile The song profile information.
     */
    public SongPage(String[] songProfile) {
        super("Song");

        // Label Layout
        label1 = new JLabel();
        label1.setText("SongID: " + songProfile[0]);
        label1.setForeground(Color.WHITE);
        label1.setPreferredSize(new Dimension(400, 20));

        label2 = new JLabel();
        label2.setText("SongName: " + songProfile[1]);
        label2.setForeground(Color.WHITE);
        label2.setPreferredSize(new Dimension(400, 20));

        label11 = new JLabel();
        label11.setText("SongYear: " + songProfile[3]);
        label11.setForeground(Color.WHITE);
        label11.setPreferredSize(new Dimension(400, 20));

        labelSpace = new JLabel();
        labelSpace.setText("");
        labelSpace.setPreferredSize(new Dimension(700, 20));

        label3 = new JLabel();
        label3.setText("Author: " + songProfile[2]);
        label3.setForeground(Color.WHITE);
        label3.setPreferredSize(new Dimension(400, 20));

        label4 = new JLabel();
        label4.setText("Amazement: " + songProfile[4]);
        label4.setForeground(Color.WHITE);
        label4.setPreferredSize(new Dimension(100, 20));

        label12 = new JLabel();
        label12.setText("Tenderness: " + songProfile[6]);
        label12.setForeground(Color.WHITE);
        label12.setPreferredSize(new Dimension(100, 20));

        label5 = new JLabel();
        label5.setText("Solemnity: " + songProfile[5]);
        label5.setForeground(Color.WHITE);
        label5.setPreferredSize(new Dimension(100, 20));

        label6 = new JLabel();
        label6.setText("Nostalgia: " + songProfile[7]);
        label6.setForeground(Color.WHITE);
        label6.setPreferredSize(new Dimension(100, 20));

        label13 = new JLabel();
        label13.setText("Calmness: " + songProfile[8]);
        label13.setForeground(Color.WHITE);
        label13.setPreferredSize(new Dimension(100, 20));

        label7 = new JLabel();
        label7.setText("Power: " + songProfile[9]);
        label7.setForeground(Color.WHITE);
        label7.setPreferredSize(new Dimension(100, 20));

        label8 = new JLabel();
        label8.setText("Joy: " + songProfile[10]);
        label8.setForeground(Color.WHITE);
        label8.setPreferredSize(new Dimension(100, 20));

        label9 = new JLabel();
        label9.setText("Tension: " + songProfile[11]);
        label9.setForeground(Color.WHITE);
        label9.setPreferredSize(new Dimension(100, 20));

        label10 = new JLabel();
        label10.setText("Sadness: " + songProfile[12]);
        label10.setForeground(Color.WHITE);
        label10.setPreferredSize(new Dimension(100, 20));


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
        this.add(label11);
        this.add(labelSpace);
        this.add(label4);
        this.add(label5);
        this.add(label6);
        this.add(label7);
        this.add(label8);
        this.add(label9);
        this.add(label10);
        this.add(label12);
        this.add(label13);

        // Last things to make everything work
        this.setVisible(true); // Make frame visible
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

    
}
