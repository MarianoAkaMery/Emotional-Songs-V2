package pages;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import resources.Costant;
import javax.swing.JFrame;

public class MainPage extends JFrame implements ActionListener{

    JButton button1 = new JButton("Users");
    JButton button2 = new JButton("Songs");
    JButton button3 = new JButton("Playlists");
    JButton button4 = new JButton("Custom Commands");

    public MainPage(){
        super("EmotionalSongs Admin-Panel");



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
        this.setIconImage(Costant.AppIcon.getImage());
        this.setResizable(false); //prevent for bein resized
        this.setSize(Costant.WidthtApp, Costant.HeightApp);
        this.getContentPane().setBackground(Costant.BackgroundColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

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
    
