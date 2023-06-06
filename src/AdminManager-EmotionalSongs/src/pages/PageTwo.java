package pages;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import resources.Constant;
import resources.DbConnector;
import resources.Functions;
import pagesResult.SongPage;

/**
 * This class represents PageTwo of the application.
 */
public class PageTwo extends JFrame implements ActionListener {

    private JList<String> elementList;
    private JSlider slider;
    String[] dummyData;
    String[] songList;
    int length;

    /**
     * Creates an instance of PageTwo.
     */
    public PageTwo() {
        super("EmotionalSongs Songs");

        // Adding data to our list + DB
        DbConnector app = new DbConnector();
        dummyData = app.getSongsInfo();
        songList = Functions.refactorData(dummyData);

        elementList = new JList<>(songList);
        elementList.setCellRenderer(new ButtonRenderer());

        // Button Layout
        slider = new JSlider(JSlider.HORIZONTAL, 1, songList.length, 1);
        slider.setMajorTickSpacing(500);
        slider.setMinorTickSpacing(100);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // Window Structure
        this.setIconImage(Constant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Constant.AppWidth, Constant.AppHeight);
        this.getContentPane().setBackground(Constant.BackgroundColor);

        // Adding Components
        this.add(new JScrollPane(elementList), BorderLayout.CENTER);
        this.add(slider, BorderLayout.SOUTH);

        // Last things to make everything working
        this.setVisible(true); // Make frame visible
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

        // Adding Listener change and selection listener
        slider.addChangeListener(new ChangeListener() {
            /**
             * Handles the state change event of the slider.
             *
             * @param e The change event.
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                String[] displayedElements = new String[value];
                System.arraycopy(songList, 0, displayedElements, 0, value);
                elementList.setListData(displayedElements);
            }
        });

        // Add a list selection listener to the elementList
        elementList.addListSelectionListener(new ListSelectionListener() {
            /**
             * Handles the value change event of the elementList.
             *
             * @param e The list selection event.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedElement = elementList.getSelectedValue();
                    if (selectedElement != null) {
                        String[] song = new String[4];
                        DbConnector app = new DbConnector();
                        song = app.getSingleSongInfo(selectedElement);

                        new SongPage(song);

                        System.out.println("Element selected: " + selectedElement);
                    }
                }
            }
        });
    }

    /**
     * This class represents a custom button renderer for the elementList.
     */
    private class ButtonRenderer extends JButton implements ListCellRenderer<String> {
        public ButtonRenderer() {
            setOpaque(true);
        }

        /**
         * Returns a component that can be used to draw the specified value in the list.
         *
         * @param list         The JList we're painting.
         * @param value        The value returned by list.getModel().getElementAt(index).
         * @param index        The cells index.
         * @param isSelected   True if the specified cell was selected.
         * @param cellHasFocus True if the specified cell has the focus.
         * @return A component used to draw the value.
         */
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
