package pages;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import resources.Costant;

public class LoginPage extends JFrame implements ActionListener {

    JTextField emailField = new JTextField();
    JTextField passwordField = new JTextField();
    JButton buttonSubmit = new JButton("Submit");
    JLabel imageLabel = new JLabel(Costant.Applogo);
    JPanel contentPanel = new JPanel();


    public LoginPage() {
      
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
            //String emailInfo = emailField.getText();
            //String passwordInfo = passwordField.getText();


            if (emailField.getText().equals("admin") && passwordField.getText().equals("admin")) {
                System.out.println("Login Done");
                this.dispose();
                new MainPage();
            } else {
                System.out.println("Not good");
            }
        }
    }
}
