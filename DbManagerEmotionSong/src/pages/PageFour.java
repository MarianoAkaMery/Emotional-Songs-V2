package pages;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import resources.Costant;

public class PageFour extends JFrame implements ActionListener{

    JButton buttonBack = new JButton("Return Home");
    JButton buttonExecute = new JButton("Execute Query");

    JTextField QueryField = new JTextField();

  

    public PageFour(){


        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Button Layout//
        QueryField.setPreferredSize(new Dimension(400, 200));
        QueryField.setForeground(Costant.TextColor);
        //
        buttonBack.setBounds(200, 100, 200, 50);
        buttonBack.setFocusable(false);
        buttonBack.addActionListener(this);
        //
        buttonExecute.setBounds(200, 100, 200, 50);
        buttonExecute.setFocusable(false);
        buttonExecute.addActionListener(this);

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Window Strucuture//
        this.setIconImage(Costant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Costant.WidthtApp, Costant.HeightApp);
        this.getContentPane().setBackground(Costant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Adding Components//
        this.add(QueryField);
        this.add(buttonExecute);
        this.add(buttonBack);
      
        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Last things to make evrything working//
        this.setVisible(true); //make frame visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buttonBack){
            this.dispose();
            new MainPage();
        }
        if (e.getSource()==buttonExecute){
            String queryMadeByUser= QueryField.getText();

            if (queryMadeByUser.contains("SELECT")){

            }
            if (queryMadeByUser.contains("INSERT")){
                
            }
            if (queryMadeByUser.contains("UPDATE")){
                
            }
            if (queryMadeByUser.contains("DELETE")){
                
            }
            else{
                
                JOptionPane.showMessageDialog(this, "You are not able to perform this action", "CustomQuery - info", JOptionPane.ERROR_MESSAGE);

            }


            this.dispose();
            new MainPage();
        }
    }

}
    
