package pagesResult;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



import javax.swing.*;

import resources.Costant;
import resources.DbConnector;

public class UserPage extends JFrame implements ActionListener{
    String[] User= new String[4];
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JButton buttonDelete = new JButton("Delete User");
    String emailUserToDelete;

    public UserPage(String[] userProfile) {


        super("User");
        emailUserToDelete=userProfile[1];

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Adding data to our list + DB//            
       

         
        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Button Layout//
        label1 = new JLabel();
        label1.setText("UserID: " + userProfile[0]);
        label1.setForeground(Color.WHITE);
        label1.setPreferredSize(new Dimension(400, 20));


        label2 = new JLabel();
        label2.setText("UserEmail: " + userProfile[1]);
        label2.setForeground(Color.WHITE);
        label2.setPreferredSize(new Dimension(400, 20));



        label3 = new JLabel();
        label3.setText("UserName: " + userProfile[2]);
        label3.setForeground(Color.WHITE);
        label3.setPreferredSize(new Dimension(400, 20));



        label4 = new JLabel();
        label4.setText("UserLastUse: " + userProfile[3]);
        label4.setForeground(Color.WHITE);
        label4.setPreferredSize(new Dimension(400, 20));



        // Create the button
        buttonDelete.setPreferredSize(new Dimension(400, 20));
        buttonDelete.setFocusable(false);
        buttonDelete.addActionListener(this);


        

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Window Strucuture//
        this.setIconImage(Costant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Costant.WidthtApp, Costant.HeightApp);
        this.getContentPane().setBackground(Costant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Adding Components//
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(buttonDelete);
        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Last things to make everything working//
        this.setVisible(true); //make frame visible
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
        if (e.getSource()==buttonDelete){
            DbConnector app= new DbConnector();
            Boolean deleteAction;
            deleteAction= app.deleteUser(emailUserToDelete);
            if (deleteAction==true){

                JOptionPane.showMessageDialog(this, "Sucesfully deleted user", "User-Notification", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                
                JOptionPane.showMessageDialog(this, "Unable to delete user", "User-Notification", JOptionPane.ERROR_MESSAGE);

            }
            this.dispose();
        }
    }



    
}
