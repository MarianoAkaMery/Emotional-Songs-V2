package pages;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import resources.Constant;
import resources.DbConnector;
import resources.Functions;
import resources.User;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;

public class CreatePlaylist extends JFrame implements ActionListener {
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> searchOptions;
    private JList<String> elementList;
    private JSlider slider;
    private JLabel labelSpace;
    private JButton buttonCreatePlaylist = new JButton("Submit Playlist");
    String DummyDataName[];
    String DummyDataYear[];
    String DummyDataAuthor[];
    String SongList[];
    String YearList[];
    String AuthorList[];
    List<String> PlaylistInCreation = new ArrayList<>();

    int Length;
    User loggedUser;
    DbConnector app = new DbConnector();


    public CreatePlaylist(User LoggedUser) {
        loggedUser=LoggedUser;
        setTitle("EmotionalSongs Playlist-Creation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Add to my playlist");
        searchOptions = new JComboBox<>(new String[] { "All-Author", "Title", "All-Year" });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(searchOptions);

        //Playlist Panel
   
        JPanel playlistPanel = new JPanel();
        playlistPanel.setLayout(new BoxLayout(playlistPanel, BoxLayout.Y_AXIS));
        playlistPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        labelSpace = new JLabel();
        labelSpace.setText(" ");
        labelSpace.setPreferredSize(new Dimension(100, 40));

        buttonCreatePlaylist.addActionListener(this);

        
        playlistPanel.add(labelSpace);
        playlistPanel.add(buttonCreatePlaylist);

      

        // Song List Panel
        JPanel songListPanel = new JPanel(new BorderLayout());

        DbConnector app = new DbConnector();
        DummyDataName = app.getSongsInfo();
        DummyDataYear = app.getSongsInfoYear();
        DummyDataAuthor = app.getSongsInfoAuthor();
        SongList = Functions.refactorData(DummyDataName);
        YearList = Functions.refactorData(DummyDataYear);
        AuthorList = Functions.refactorData(DummyDataAuthor);

        elementList = new JList<>(SongList);
        elementList.setCellRenderer(new ButtonRenderer());

        slider = new JSlider(JSlider.HORIZONTAL, 1, SongList.length, 1);
        slider.setMajorTickSpacing(300);
        slider.setMinorTickSpacing(150);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        songListPanel.add(new JScrollPane(elementList), BorderLayout.CENTER);
        songListPanel.add(slider, BorderLayout.SOUTH);

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidthMaxi, Constant.AppHeightMaxi);

        // Add panels to the frame
        add(searchPanel, BorderLayout.NORTH);
        add(songListPanel, BorderLayout.CENTER);
        add(playlistPanel, BorderLayout.EAST);


        // Last things to make everything working
        this.setVisible(true); // Make frame visible
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new LoginPage();
                dispose();
            }
        });

        // Adding Listener change and selection listener
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                String[] displayedElements = new String[value];
                System.arraycopy(SongList, 0, displayedElements, 0, value);
                elementList.setListData(displayedElements);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                String selectedOption = (String) searchOptions.getSelectedItem();
                String[] filteredSongs = filterSongs(searchText, selectedOption);
                elementList.setListData(filteredSongs);
            }
        });

        elementList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedElement = elementList.getSelectedValue();
                    if (selectedElement != null) {
                        String[] Song = new String[13];
                        Song = app.getSingleSongInfoWithEmotion(selectedElement);
                        String songID = Song[0];
                        PlaylistInCreation.add(songID);

                        System.out.println("Element selected: " + selectedElement);
                        JOptionPane.showMessageDialog(songListPanel, selectedElement + " Successfully added to your new playlist", "Playlist Notification",
                        JOptionPane.INFORMATION_MESSAGE);


                    }
                }
            }
        });
    }

    private String[] filterSongs(String searchText, String selectedOption) {

        java.util.List<String> filteredSongsList = new java.util.ArrayList<>();

        if (selectedOption.equals("Title")) {
            for (int i = 0; i < SongList.length; i++) {
                if (SongList[i].equalsIgnoreCase(searchText)) {
                    filteredSongsList.add(SongList[i]);
                }
            }
        }

        if (selectedOption.equals("All-Year")) {
            for (int i = 0; i < YearList.length; i++) {
                if (YearList[i].equalsIgnoreCase(searchText)) {
                    filteredSongsList.add(SongList[i]);
                    String[] Song = new String[13];
                    Song = app.getSingleSongInfoWithEmotion(SongList[i]);
                    String songID = Song[0];
                    PlaylistInCreation.add(songID);
                }
            }
            JOptionPane.showMessageDialog(this, "Successfully added all " + searchText + " songs to your new playlist", "Playlist Notification",
                        JOptionPane.INFORMATION_MESSAGE);
        }

        if (selectedOption.equals("All-Author")) {
            for (int i = 0; i < AuthorList.length; i++) {
                if (AuthorList[i].equalsIgnoreCase(searchText)) {
                    filteredSongsList.add(SongList[i]);
                    String[] Song = new String[13];
                    Song = app.getSingleSongInfoWithEmotion(SongList[i]);
                    String songID = Song[0];
                    PlaylistInCreation.add(songID);
                }
            }
            JOptionPane.showMessageDialog(this, "Successfully added all " + searchText + " songs to your new playlist", "Playlist Notification",
                        JOptionPane.INFORMATION_MESSAGE);
        }

        String[] filteredSongs = new String[filteredSongsList.size()];
        filteredSongsList.toArray(filteredSongs);

        return filteredSongs;
    }

    private class ButtonRenderer extends JButton implements ListCellRenderer<String> {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                boolean isSelected, boolean cellHasFocus) {
            setText(value);

            // Set the background and foreground colors based on the selection
            if (isSelected) {
                setBackground(Color.BLUE); // Set your desired background color
                setForeground(Color.WHITE); // Set your desired foreground color
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCreatePlaylist) {
            
            app.registerPlaylist(loggedUser.getUserID(), "Playlist Test", PlaylistInCreation);
            dispose();
        }
    }

}
