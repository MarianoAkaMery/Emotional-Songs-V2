package pages;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pagesResult.SongPage;
import resources.Constant;
import resources.DbConnector;
import resources.Functions;

import java.awt.*;
import java.awt.event.*;

/**
 * The MainGuest class represents the main page for guest users in the EmotionalSongs application.
 */
public class MainGuest extends JFrame implements ActionListener {
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> searchOptions;
    private JList<String> elementList;
    private JSlider slider;
    private String[] dummyDataName;
    private String[] dummyDataYear;
    private String[] dummyDataAuthor;

    private String[] songList;
    private String[] yearList;
    private String[] authorList;

    /**
     * Constructor for the MainGuest class.
     * Initializes and configures the graphical elements of the window.
     */
    public MainGuest() {
        setTitle("EmotionalSongs GuestPage");
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

        // Song List Panel
        JPanel songListPanel = new JPanel(new BorderLayout());

        DbConnector app = new DbConnector();
        dummyDataName = app.getSongsInfo();
        dummyDataYear = app.getSongsInfoYear();
        dummyDataAuthor = app.getSongsInfoAuthor();
        songList = Functions.refactorData(dummyDataName);
        yearList = Functions.refactorData(dummyDataYear);
        authorList = Functions.refactorData(dummyDataAuthor);

        elementList = new JList<>(songList);
        elementList.setCellRenderer(new ButtonRenderer());

        slider = new JSlider(JSlider.HORIZONTAL, 1, songList.length, 1);
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

        // Last things to make everything work
        this.setVisible(true);
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
                System.arraycopy(songList, 0, displayedElements, 0, value);
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
                        String[] song = new String[13];
                        DbConnector app = new DbConnector();
                        song = app.getSingleSongInfoWithEmotion(selectedElement);

                        new SongPage(song);

                        System.out.println("Element selected: " + selectedElement);
                    }
                }
            }
        });
    }

    /**
     * Filters songs based on the search text and selected option.
     *
     * @param searchText     the search text
     * @param selectedOption the selected option (Author, Title, or Year)
     * @return an array of filtered songs
     */
    private String[] filterSongs(String searchText, String selectedOption) {
        java.util.List<String> filteredSongsList = new java.util.ArrayList<>();

        if (selectedOption.equals("Title")) {
            for (int i = 0; i < songList.length; i++) {
                if (songList[i].equalsIgnoreCase(searchText)) {
                    filteredSongsList.add(songList[i]);
                }
            }
        }

        if (selectedOption.equals("Year")) {
            for (int i = 0; i < yearList.length; i++) {
                if (yearList[i].equalsIgnoreCase(searchText)) {
                    filteredSongsList.add(songList[i]);
                }
            }
        }

        if (selectedOption.equals("Author")) {
            for (int i = 0; i < authorList.length; i++) {
                if (authorList[i].equalsIgnoreCase(searchText)) {
                    filteredSongsList.add(songList[i]);
                }
            }
        }

        String[] filteredSongs = new String[filteredSongsList.size()];
        filteredSongsList.toArray(filteredSongs);

        return filteredSongs;
    }

    /**
     * Custom renderer for buttons in the song list.
     */
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
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
