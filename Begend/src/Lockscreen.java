import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Lockscreen extends JFrame{

    boolean mainscreen_Vis = false;

    private Mainscreen mainscreen = new Mainscreen();

    private boolean setLockscreen_Vis = true;

    FileCreationSystem fileCreation_S = new FileCreationSystem();

    String file;
    public void lockscreenVis(){

        setTitle("Lockscreen");
        setSize(300, 400);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        //Name Register
        JLabel name_Label = new JLabel("Name:");
        name_Label.setBounds(10,20,100,20);
        name_Label.setFont(new Font("", Font.PLAIN, 20));
        add(name_Label);

        JTextField name_Field = new JTextField();
        name_Field.setBounds(10,45,200,30);
        name_Field.setFont(new Font("", Font.PLAIN, 15));
        add(name_Field);


        //Loginbutton
        int button_width = 100;
        int button_X = (getSize().width - button_width)/2;

        JButton login_Button = new JButton("Login");
        login_Button.setBounds(button_X,250,button_width,50);
        login_Button.setBackground(Color.LIGHT_GRAY);
        login_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fileCreation_S.nameFileCreation(name_Field.getText());
                file = fileCreation_S.getUserPathDay();


                mainscreen.mainScreenVis(true, file);
                setLockscreen_Vis = false;
                setVisible(setLockscreen_Vis);

            }
        });
        add(login_Button);

        setVisible(setLockscreen_Vis);
    }
    public void setVisMain(boolean vis){
        mainscreen.mainScreenVis(vis, file);
    }


}
