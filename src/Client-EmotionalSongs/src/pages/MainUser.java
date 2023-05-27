package pages;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pagesResult.SongPageLogged;
import resources.Constant;
import resources.DbConnector;
import resources.Functions;
import resources.User;

import java.awt.*;
import java.awt.event.*;

public class MainUser extends JFrame implements ActionListener {
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> searchOptions;
    private JList<String> elementList;
    private JLabel profileImageLabel;
    private JLabel insertName = new JLabel();
    private JLabel Name = new JLabel();
    private JLabel insertId = new JLabel();
    private JLabel Id = new JLabel();
    private JLabel insertEmail = new JLabel();
    private JLabel Email = new JLabel();
    private JLabel labelSpace;
    private JButton buttonCreatePlaylist = new JButton("Create Playlist");

    private JSlider slider;
    String DummyDataName[];
    String DummyDataYear[];
    String DummyDataAuthor[];

    String SongList[];
    String YearList[];
    String AuthorList[];
    int Length;
    public User userlogged;

    Image avatarImage = Constant.UserProfile.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon resizedAvatarIcon = new ImageIcon(avatarImage);

    public MainUser(User LoggedUser) {
        userlogged=LoggedUser;
        setTitle("EmotionalSongs MainPage");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchOptions = new JComboBox<>(new String[] { "Author", "Title", "Year" });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(searchOptions);

        // User Panel
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        profileImageLabel = new JLabel();
        profileImageLabel.setIcon(resizedAvatarIcon);
        profilePanel.add(profileImageLabel);

        insertName.setText("Username: ");
        insertName.setPreferredSize(new Dimension(50, 20));
        insertName.setForeground(Color.black);

        Name.setText(LoggedUser.getUserName());
        Name.setPreferredSize(new Dimension(50, 20));
        Name.setForeground(Color.black);

        insertId.setText("Id: ");
        insertId.setPreferredSize(new Dimension(50, 20));
        insertId.setForeground(Color.black);

        Id.setText(LoggedUser.getUserID());
        Id.setPreferredSize(new Dimension(50, 20));
        Id.setForeground(Color.black);

        insertEmail.setText("Email: ");
        insertEmail.setPreferredSize(new Dimension(50, 20));
        insertEmail.setForeground(Color.black);

        Email.setText(LoggedUser.getUserEmail());
        Email.setPreferredSize(new Dimension(50, 20));
        Email.setForeground(Color.black);

        labelSpace = new JLabel();
        labelSpace.setText(" ");
        labelSpace.setPreferredSize(new Dimension(100, 40));

        buttonCreatePlaylist.addActionListener(this);

        profilePanel.add(insertName);
        profilePanel.add(Name);
        profilePanel.add(insertId);
        profilePanel.add(Id);
        profilePanel.add(insertEmail);
        profilePanel.add(Email);
        profilePanel.add(labelSpace);
        profilePanel.add(buttonCreatePlaylist);

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
        add(profilePanel, BorderLayout.EAST);

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
                        String[] Song = new String[14];
                        DbConnector app = new DbConnector();
                        Song = app.getSingleSongInfoWithEmotion(selectedElement);

                        new SongPageLogged(Song);

                        System.out.println("Element selected: " + selectedElement);
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

        if (selectedOption.equals("Year")) {
            for (int i = 0; i < YearList.length; i++) {
                if (YearList[i].equalsIgnoreCase(searchText)) {
                    filteredSongsList.add(SongList[i]);
                }
            }
        }

        if (selectedOption.equals("Author")) {
            for (int i = 0; i < AuthorList.length; i++) {
                if (AuthorList[i].equalsIgnoreCase(searchText)) {
                    filteredSongsList.add(SongList[i]);
                }
            }
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
            return this;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCreatePlaylist) {
            new CreatePlaylist(userlogged);
        }
    }

}
