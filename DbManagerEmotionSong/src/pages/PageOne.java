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
import pagesResult.UserPage;

/**
 * This class represents PageOne of the application.
 */
public class PageOne extends JFrame implements ActionListener {

    private JList<String> elementList;
    private JSlider slider;
    String DummyData[];
    String EmailList[];
    int Length;


    /**
     * Creates an instance of PageOne.
     */
    public PageOne() {
        super("EmotionalSongs Users");

        // Adding data to our list + DB
        DbConnector app = new DbConnector();
        DummyData = app.getUserInfo();
        EmailList = Functions.refactorData(DummyData);

        elementList = new JList<>(EmailList);
        elementList.setCellRenderer(new ButtonRenderer());

        // Button Layout
        slider = new JSlider(JSlider.HORIZONTAL, 1, EmailList.length, 1);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
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

        // Last things to make everything work
        this.setVisible(true); // Make frame visible
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
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
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                String[] displayedElements = new String[value];
                System.arraycopy(EmailList, 0, displayedElements, 0, value);
                elementList.setListData(displayedElements);
            }
        });

        // Add a list selection listener to the elementList
        elementList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedElement = elementList.getSelectedValue();
                    if (selectedElement != null) {
                        String[] User = new String[4];
                        DbConnector app = new DbConnector();
                        User = app.getSingleUserInfo(selectedElement);

                        new UserPage(User);
                        
                        System.out.println("Element selected: " + selectedElement);
                    }
                }
            }
        });
    }

    /**
     * This class represents a custom button renderer for the list cell.
     */
    private class ButtonRenderer extends JButton implements ListCellRenderer<String> {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);
            return this;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
