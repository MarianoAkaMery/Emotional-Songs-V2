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
 * Represents the song page in a GUI application.
 */
public class EditSongPage extends JFrame  implements ActionListener  {
    private JLabel labelName;

    private JLabel Amazement;
    int AmazementPoint;

    private JLabel Tenderness;
    int TendernessPoint=0;

    private JLabel Solemnity;
    int SolemnityPoint=0;

    private JLabel Nostalgia;
    int NostalgiaPoint=0;

    private JLabel Calmness;
    int CalmnessPoint=0;

    private JLabel Power;
    int PowerPoint=0;

    private JLabel Joy;
    int JoyPoint=0;

    private JLabel Tension;
    int TensionPoint=0;

    private JLabel Sadness;
    int SadnessPoint=0;

    private JLabel labelSpace;
    private JButton buttonSubmitEmotion = new JButton("Add Emotions");

    String[] songToEdit;

    Integer[] rankings = {0, 1, 2, 3, 4, 5};


    /**
     * Creates a new instance of SongPage.
     *
     * @param songProfile The song profile information.
     */
    public EditSongPage(String[] songProfile) {
        super("Song");
        songToEdit=songProfile;
        int VotantUser=Integer.valueOf(songProfile[13]);
        int VotantUserOK=VotantUser+1;


     
        labelName = new JLabel();
        labelName.setText("SongName: " + songProfile[1]);
        labelName.setForeground(Color.WHITE);
        labelName.setLayout(new FlowLayout(FlowLayout.CENTER));

        labelName.setPreferredSize(new Dimension(840, 20));

        labelSpace = new JLabel();
        labelSpace.setText(" ");
        labelSpace.setPreferredSize(new Dimension(800, 20));


        JPanel panelAmazement = new JPanel();
        Amazement = new JLabel();
        Amazement.setText("Amazement: ");
        Amazement.setForeground(Color.BLACK);
        Amazement.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBoxx = new JComboBox<>(rankings);
        comboBoxx.addActionListener(e -> {
            // Get the selected ranking
            AmazementPoint = (int) comboBoxx.getSelectedItem();
            Amazement.setText("Selected Ranking: " + AmazementPoint);
            AmazementPoint= (((Integer.valueOf(songProfile[4]) * VotantUser) + AmazementPoint ) / VotantUserOK );
            
            // Update the label with the selected ranking
        });
        panelAmazement.add(comboBoxx);
        panelAmazement.add(Amazement);

        JPanel panelTenderness = new JPanel();
        Tenderness = new JLabel();
        Tenderness.setText("Tenderness: " + songProfile[4]);
        Tenderness.setForeground(Color.BLACK);
        Tenderness.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBox1 = new JComboBox<>(rankings);
        comboBox1.addActionListener(e -> {
            // Get the selected ranking
            TendernessPoint = (int) comboBox1.getSelectedItem();
            Tenderness.setText("Selected Ranking: " + TendernessPoint);

            TendernessPoint= (((Integer.valueOf(songProfile[5]) * VotantUser) + TendernessPoint ) / VotantUserOK );
            
            // Update the label with the selected ranking
        });
        panelTenderness.add(comboBox1);
        panelTenderness.add(Tenderness);

        JPanel panelSolemnity = new JPanel();
        Solemnity = new JLabel();
        Solemnity.setText("Solemnity: " + songProfile[4]);
        Solemnity.setForeground(Color.BLACK);
        Solemnity.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBox2 = new JComboBox<>(rankings);
        comboBox2.addActionListener(e -> {
            // Get the selected ranking
            SolemnityPoint = (int) comboBox2.getSelectedItem();
            Solemnity.setText("Selected Ranking: " + SolemnityPoint);

            SolemnityPoint= (((Integer.valueOf(songProfile[6]) * VotantUser) + SolemnityPoint ) / VotantUserOK );
            
            // Update the label with the selected ranking
        });
        panelSolemnity.add(comboBox2);
        panelSolemnity.add(Solemnity);

        JPanel panelNostalgia= new JPanel();
        Nostalgia = new JLabel();
        Nostalgia.setText("Nostalgia: " + songProfile[4]);
        Nostalgia.setForeground(Color.BLACK);
        Nostalgia.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBox3 = new JComboBox<>(rankings);
        comboBox3.addActionListener(e -> {
            // Get the selected ranking
            NostalgiaPoint = (int) comboBox3.getSelectedItem();
            Nostalgia.setText("Selected Ranking: " + NostalgiaPoint);

            NostalgiaPoint= (((Integer.valueOf(songProfile[7]) * VotantUser) + NostalgiaPoint ) / VotantUserOK );

            
            // Update the label with the selected ranking
        });
        panelNostalgia.add(comboBox3);
        panelNostalgia.add(Nostalgia);

        JPanel panelCalmness= new JPanel();
        Calmness = new JLabel();
        Calmness.setText("Calmness: " + songProfile[4]);
        Calmness.setForeground(Color.BLACK);
        Calmness.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBox4 = new JComboBox<>(rankings);
        comboBox4.addActionListener(e -> {
            // Get the selected ranking
            CalmnessPoint = (int) comboBox4.getSelectedItem();
            Calmness.setText("Selected Ranking: " + CalmnessPoint);

            CalmnessPoint= (((Integer.valueOf(songProfile[8]) * VotantUser) + CalmnessPoint ) / VotantUserOK );

            
            // Update the label with the selected ranking
        });
        panelCalmness.add(comboBox4);
        panelCalmness.add(Calmness);


        JPanel panelPower= new JPanel();
        Power = new JLabel();
        Power.setText("Power: " + songProfile[4]);
        Power.setForeground(Color.BLACK);
        Power.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBox5 = new JComboBox<>(rankings);
        comboBox5.addActionListener(e -> {
            // Get the selected ranking
            PowerPoint = (int) comboBox5.getSelectedItem();
            Power.setText("Selected Ranking: " + PowerPoint);

            PowerPoint= (((Integer.valueOf(songProfile[9]) * VotantUser) + PowerPoint ) / VotantUserOK );
            
            // Update the label with the selected ranking
        });
        panelPower.add(comboBox5);
        panelPower.add(Power);


        JPanel panelJoy= new JPanel();
        Joy = new JLabel();
        Joy.setText("Joy: " + songProfile[4]);
        Joy.setForeground(Color.BLACK);
        Joy.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBox6 = new JComboBox<>(rankings);
        comboBox6.addActionListener(e -> {
            // Get the selected ranking
            JoyPoint = (int) comboBox6.getSelectedItem();
            Joy.setText("Selected Ranking: " + JoyPoint);

            JoyPoint= (((Integer.valueOf(songProfile[10]) * VotantUser) + JoyPoint ) / VotantUserOK );

            
            // Update the label with the selected ranking
        });
        panelJoy.add(comboBox6);
        panelJoy.add(Joy);


        JPanel panelTension= new JPanel();
        Tension = new JLabel();
        Tension.setText("Tension: " + songProfile[4]);
        Tension.setForeground(Color.BLACK);
        Tension.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBox7 = new JComboBox<>(rankings);
        comboBox7.addActionListener(e -> {
            // Get the selected ranking
            TensionPoint = (int) comboBox7.getSelectedItem();
            Tension.setText("Selected Ranking: " + TensionPoint);

            TensionPoint= (((Integer.valueOf(songProfile[11]) * VotantUser) + TensionPoint ) / VotantUserOK );

            
            // Update the label with the selected ranking
        });
        panelTension.add(comboBox7);
        panelTension.add(Tension);

        JPanel panelSadness= new JPanel();
        Sadness = new JLabel();
        Sadness.setText("Sadness: " + songProfile[4]);
        Sadness.setForeground(Color.BLACK);
        Sadness.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBox8 = new JComboBox<>(rankings);
        comboBox8.addActionListener(e -> {
            // Get the selected ranking
            SadnessPoint = (int) comboBox8.getSelectedItem();
            Sadness.setText("Selected Ranking: " + SadnessPoint);

            SadnessPoint= (((Integer.valueOf(songProfile[12]) * VotantUser) + SadnessPoint ) / VotantUserOK );

            
            // Update the label with the selected ranking
        });
        panelSadness.add(comboBox8);
        panelSadness.add(Sadness);


        buttonSubmitEmotion.addActionListener(this);

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidthMaxi, Constant.AppHeightMaxi);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adding Components
        this.add(labelName);
        this.add(labelSpace);
        this.add(panelAmazement);
        this.add(panelTenderness);
        this.add(panelSolemnity);
        this.add(panelNostalgia);
        this.add(panelCalmness);
        this.add(panelPower);
        this.add(panelJoy);
        this.add(panelTension);
        this.add(panelSadness);
        this.add(labelSpace);
        this.add(buttonSubmitEmotion);
        

        

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSubmitEmotion) {
            DbConnector app = new DbConnector();
            int counter=Integer.valueOf(songToEdit[13])+1;
            
            app.updateSongEmotion(AmazementPoint,SolemnityPoint,TendernessPoint,NostalgiaPoint,CalmnessPoint,PowerPoint,JoyPoint,TensionPoint,SadnessPoint,counter, songToEdit[0]);
            dispose();
        }
    }

    
}
