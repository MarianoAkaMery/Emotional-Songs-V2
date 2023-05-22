package pages;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import resources.Costant;
import resources.DbConnector;
import resources.Functions;


public class PageOne extends JFrame implements ActionListener {

    private JList<String> elementList;
    private JSlider slider;
    String DummyData[];
    String EmailList[];
    int Length;



    public PageOne() {
        super("Element List with Button");

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Adding data to our list + DB//            
        DbConnector app= new DbConnector();
        DummyData= app.getUserInfo();
        EmailList= Functions.refactorData(DummyData);

        

        elementList = new JList<>(EmailList);
        elementList.setCellRenderer(new ButtonRenderer());

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Button Layout//        
        slider = new JSlider(JSlider.HORIZONTAL, 1, EmailList.length, 1);
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Window Strucuture//
        this.setIconImage(Costant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Costant.WidthtApp, Costant.HeightApp);
        this.getContentPane().setBackground(Costant.BackgroundColor);

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Adding Components//
        this.add(new JScrollPane(elementList), BorderLayout.CENTER);
        this.add(slider, BorderLayout.SOUTH);

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Last things to make everything working//
        this.setVisible(true); //make frame visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Adding Listener change and selection listener//
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
                        System.out.println("Element selected: " + selectedElement);
                        new MainPage();
                    }
                }
            }
        });

        
    }

    private class ButtonRenderer extends JButton implements ListCellRenderer<String> {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,boolean isSelected, boolean cellHasFocus) {
            setText(value);
            return this;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
    

    

}