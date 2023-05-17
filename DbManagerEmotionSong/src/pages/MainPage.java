package pages;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainPage extends JFrame implements ActionListener{

    ImageIcon image = new ImageIcon("./assets/logo.png");
    JButton button1 = new JButton("Page1");
    JButton button2 = new JButton("Page2");
    JButton button3 = new JButton("Page3");
    JButton button4 = new JButton("Page4");

    public MainPage(){


        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Button Layout//

        button1.setBounds(200, 100, 200, 50);
        button1.setFocusable(false);
        button1.addActionListener(this);

        button2.setBounds(200, 100, 200, 50);
        button2.setFocusable(false);
        button2.addActionListener(this);



        button3.setBounds(200, 100, 200, 50);
        button3.setFocusable(false);
        button3.addActionListener(this);



        button4.setBounds(200, 100, 200, 50);
        button4.setFocusable(false);
        button4.addActionListener(this);


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

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);


        //+++++++++++++++++++++++++++++++++++++++++++++++++//
        //Last things to make evrything working//
        this.setVisible(true); //make frame visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1){
            this.dispose();
            new PageOne();}
        if (e.getSource()==button2){
            this.dispose();
            new PageTwo();}
        if (e.getSource()==button3){
            this.dispose();
            new PageThree();}
        if (e.getSource()==button4){
            this.dispose();
            new PageFour();}
        }

}
    
