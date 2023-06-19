package pagesResult;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import resources.Client;
import resources.Constant;

/**
 * Represents the song page in a GUI application.
 */
public class EditSongPage extends JFrame implements ActionListener {
    private JLabel labelName;

    int AmazementPoint;

    int TendernessPoint = 0;

    int SolemnityPoint = 0;

    int NostalgiaPoint = 0;

    int CalmnessPoint = 0;

    int PowerPoint = 0;

    int JoyPoint = 0;

    int TensionPoint = 0;

    int SadnessPoint = 0;

    private JButton buttonSubmitEmotion = new JButton("Add Emotions");

    String[] songToEdit;

    Integer[] rankings = { 0, 1, 2, 3, 4, 5 };

    /**
     * Creates a new instance of SongPage.
     *
     * @param songProfile The song profile information.
     */
    public EditSongPage(String[] songProfile) {
        super("Song");
        songToEdit = songProfile;
        int VotantUser = Integer.valueOf(songProfile[13]);
        int VotantUserOK = VotantUser + 1;

        labelName = new JLabel();
        labelName.setText("SongName: " + songProfile[1]);
        labelName.setForeground(Color.WHITE);
        labelName.setLayout(new FlowLayout(FlowLayout.CENTER));

        labelName.setPreferredSize(new Dimension(840, 20));

        JPanel panelAmazement = createEmotionPanel("Amazement", songProfile[4], rankings,
                e -> AmazementPoint = updateEmotionPoint(songProfile[4], (int) ((JComboBox<?>) e.getSource()).getSelectedItem(), VotantUser, VotantUserOK));

        JPanel panelTenderness = createEmotionPanel("Tenderness", songProfile[5], rankings,
                e -> TendernessPoint = updateEmotionPoint(songProfile[5], (int) ((JComboBox<?>) e.getSource()).getSelectedItem(), VotantUser, VotantUserOK));

        JPanel panelSolemnity = createEmotionPanel("Solemnity", songProfile[6], rankings,
                e -> SolemnityPoint = updateEmotionPoint(songProfile[6], (int) ((JComboBox<?>) e.getSource()).getSelectedItem(), VotantUser, VotantUserOK));

        JPanel panelNostalgia = createEmotionPanel("Nostalgia", songProfile[7], rankings,
                e -> NostalgiaPoint = updateEmotionPoint(songProfile[7], (int) ((JComboBox<?>) e.getSource()).getSelectedItem(), VotantUser, VotantUserOK));

        JPanel panelCalmness = createEmotionPanel("Calmness", songProfile[8], rankings,
                e -> CalmnessPoint = updateEmotionPoint(songProfile[8], (int) ((JComboBox<?>) e.getSource()).getSelectedItem(), VotantUser, VotantUserOK));

        JPanel panelPower = createEmotionPanel("Power", songProfile[9], rankings,
                e -> PowerPoint = updateEmotionPoint(songProfile[9], (int) ((JComboBox<?>) e.getSource()).getSelectedItem(), VotantUser, VotantUserOK));

        JPanel panelJoy = createEmotionPanel("Joy", songProfile[10], rankings,
                e -> JoyPoint = updateEmotionPoint(songProfile[10], (int) ((JComboBox<?>) e.getSource()).getSelectedItem(), VotantUser, VotantUserOK));

        JPanel panelTension = createEmotionPanel("Tension", songProfile[11], rankings,
                e -> TensionPoint = updateEmotionPoint(songProfile[11], (int) ((JComboBox<?>) e.getSource()).getSelectedItem(), VotantUser, VotantUserOK));

        JPanel panelSadness = createEmotionPanel("Sadness", songProfile[12], rankings,
                e -> SadnessPoint = updateEmotionPoint(songProfile[12], (int) ((JComboBox<?>) e.getSource()).getSelectedItem(), VotantUser, VotantUserOK));

        buttonSubmitEmotion.addActionListener(this);

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidthMaxi, Constant.AppHeightMaxi);
        this.getContentPane().setBackground(Constant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Adding Components
        this.add(labelName);
        this.add(panelAmazement);
        this.add(panelTenderness);
        this.add(panelSolemnity);
        this.add(panelNostalgia);
        this.add(panelCalmness);
        this.add(panelPower);
        this.add(panelJoy);
        this.add(panelTension);
        this.add(panelSadness);
        this.add(buttonSubmitEmotion);

        // Last things to make everything work
        this.setVisible(true); // Make frame visible
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Creates a panel for an emotion with a label and a combo box.
     *
     * @param emotionName   The name of the emotion.
     * @param initialValue The initial value of the emotion.
     * @param rankings      The available rankings.
     * @param listener      The listener for the combo box selection.
     * @return The created panel.
     */
    private JPanel createEmotionPanel(String emotionName, String initialValue, Integer[] rankings, ActionListener listener) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        label.setText(emotionName + ": " + initialValue);
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(150, 20));
        JComboBox<Integer> comboBox = new JComboBox<>(rankings);
        comboBox.addActionListener(listener);
        panel.add(comboBox);
        panel.add(label);
        return panel;
    }

    /**
     * Updates the emotion point based on the selected ranking and the current value.
     *
     * @param currentValue The current value of the emotion.
     * @param selectedRanking The selected ranking.
     * @param votantUser The number of voters.
     * @param votantUserOK The updated number of voters.
     * @return The updated emotion point.
     */
    private int updateEmotionPoint(String currentValue, int selectedRanking, int votantUser, int votantUserOK) {
        int currentPoint = Integer.parseInt(currentValue);
        int updatedPoint = ((currentPoint * votantUser) + selectedRanking) / votantUserOK;
        return updatedPoint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSubmitEmotion) {
            int counter = Integer.valueOf(songToEdit[13]) + 1;
            try {
                String serverAddress = "localhost";
                int serverPort = 1234;
                Client client = new Client(serverAddress, serverPort);
                String action = "Emotion";
                client.startSendEmotionSong(AmazementPoint, SolemnityPoint, TendernessPoint, NostalgiaPoint,
                        CalmnessPoint, PowerPoint, JoyPoint, TensionPoint, SadnessPoint, counter, songToEdit[0],
                        action);
                dispose();

            } catch (Exception eee) {
                System.out.println("Unable to connect, make sure to start server");
                this.dispose();
            }
        }
    }
}
