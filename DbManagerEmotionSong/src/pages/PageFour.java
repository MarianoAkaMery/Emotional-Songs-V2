package pages;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import resources.Costant;

public class PageFour extends JFrame implements ActionListener{

    JButton buttonBack = new JButton("Return Home");
  

    public PageFour(){


        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Button Layout//
        buttonBack.setBounds(200, 100, 200, 50);
        buttonBack.setFocusable(false);
        buttonBack.addActionListener(this);

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Window Strucuture//
        this.setIconImage(Costant.AppIcon.getImage());
        this.setResizable(false);
        this.setSize(Costant.WidthtApp, Costant.HeightApp);
        this.getContentPane().setBackground(Costant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Adding Components//
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
        }

}
    
