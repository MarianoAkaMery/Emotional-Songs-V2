package pages;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class PageTwo extends JFrame implements ActionListener{

    ImageIcon image = new ImageIcon("./assets/logo.png");
    JButton buttonBack = new JButton("Return Home");
  

    public PageTwo(){


        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Button Layout//

        buttonBack.setBounds(200, 100, 200, 50);
        buttonBack.setFocusable(false);
        buttonBack.addActionListener(this);


        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Window Strucuture//

        this.setIconImage(image.getImage());
        this.setResizable(false); //prevent for bein resized
        this.setSize(700, 350);
        // this.setLayout(new BorderLayout(10,10)); //set the gap
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        //this.setLayout(new GridLayout());
        this.getContentPane().setBackground(new Color(45,52,54));

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
    
