package pages;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import resources.Costant;
import resources.DbConnector;

public class LoginPage extends JFrame implements ActionListener {

    JTextField emailField = new JTextField();
    JTextField passwordField = new JTextField();
    JButton buttonSubmit = new JButton("Submit");
    JLabel imageLabel = new JLabel(Costant.Applogo);
    JPanel contentPanel = new JPanel();


    public LoginPage() {
        super("EmotionalSongs Admin-Panel");

      
        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        // Button/TextField/Images Layout/
        emailField.setPreferredSize(new Dimension(200, 25));
        emailField.setForeground(Costant.TextColor);
        //
        passwordField.setPreferredSize(new Dimension(200, 25));
        passwordField.setForeground(Costant.TextColor);
        //
        buttonSubmit.addActionListener(this);
        //
        imageLabel.setPreferredSize(new Dimension(120, 230));

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        // Window Structure
        this.setIconImage(Costant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Costant.WidthtApp, Costant.HeightApp);
        this.getContentPane().setBackground(Costant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Adding Components//
        this.add(imageLabel);
        this.add(emailField);
        this.add(passwordField);
        this.add(buttonSubmit);

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        // Last things to make everything working
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSubmit) {
            
            DbConnector app= new DbConnector();
            Boolean test;
            test= app.getAdminInfo(emailField.getText(),passwordField.getText());
            System.out.println(test);
            
            if(test==true){

                /*You can customize the appearance and behavior of the popup notification by using different JOptionPane methods and constants. 
                For example, you can change the message type 
                (e.g., JOptionPane.INFORMATION_MESSAGE, JOptionPane.WARNING_MESSAGE, JOptionPane.ERROR_MESSAGE), 
                add custom icons, include buttons for user interaction, and more. */

                JOptionPane.showMessageDialog(this, "Sucesfully logged-In", "Login-Notification", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new MainPage();
            }
            else{
                JOptionPane.showMessageDialog(this, "Wrong Login Credentials", "Login-Notification", JOptionPane.ERROR_MESSAGE);
                

            }

        }
    }
}
