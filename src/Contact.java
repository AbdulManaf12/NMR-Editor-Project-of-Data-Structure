import javax.swing.*;
import java.awt.*;

public class Contact extends JFrame
{
    Contact(){
        super("Contact");
        setSize(600,600);
        setLayout(null);
        setFocusable(true);
        setResizable(false);
        getContentPane().setBackground(new Color(35, 110, 232));
        setType(Type.UTILITY);
        setAlwaysOnTop(true);
        setLocation(400,100);
        _init_();
        setVisible(true);
    }
    private void _init_(){
        Image img = new ImageIcon(getClass().getClassLoader().getResource("icons/Sir_Saif_Hassan.jpg")).getImage().getScaledInstance(140,140,10);
        JLabel Sir = new JLabel(new ImageIcon(img));
        Sir.setBounds(40,20,140,140);
        add(Sir);
        JLabel name = new JLabel("Sir Saif Hassan");
        JLabel teacher = new JLabel("Instructor: Data Structure");

        name.setFont(new Font(Font.SERIF,Font.BOLD,30));
        teacher.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        name.setBounds(200,10,300,100);
        teacher.setBounds(200,40,300,100);
        add(name);add(teacher);


        img = new ImageIcon(getClass().getClassLoader().getResource("icons/Rashid_Abbas.jpg")).getImage().getScaledInstance(120,120,10);
        JLabel rashid = new JLabel(new ImageIcon(img));
        rashid.setBounds(60,190,120,120);
        add(rashid);
        img = new ImageIcon(getClass().getClassLoader().getResource("icons/Abdul_Manaf.jpg")).getImage().getScaledInstance(120,120,10);
        JLabel manaf = new JLabel(new ImageIcon(img));
        manaf.setBounds(220,190,120,120);
        add(manaf);
        img = new ImageIcon(getClass().getClassLoader().getResource("icons/Naveed_Ahmed.jpg")).getImage().getScaledInstance(120,120,10);
        JLabel naveed = new JLabel(new ImageIcon(img));
        naveed.setBounds(380,190,120,120);
        add(naveed);

        JLabel name1 = new JLabel("Rashid Abbas");
        JLabel name2 = new JLabel("Abdul Manaf");
        JLabel name3 = new JLabel("Naveed Ahmed");

        name1.setFont(new Font(Font.SERIF,Font.BOLD,22));
        name2.setFont(new Font(Font.SERIF,Font.BOLD,22));
        name3.setFont(new Font(Font.SERIF,Font.BOLD,22));

        name1.setBounds(55,270,200,120);
        name2.setBounds(215,270,200,120);
        name3.setBounds(375,270,200,120);
        add(name1);add(name2);add(name3);

        JLabel cms1 = new JLabel("023-19-0081");
        JLabel cms2 = new JLabel("023-19-0084");
        JLabel cms3 = new JLabel("023-19-0055");

        cms1.setFont(new Font(Font.SERIF,Font.BOLD,20));
        cms2.setFont(new Font(Font.SERIF,Font.BOLD,20));
        cms3.setFont(new Font(Font.SERIF,Font.BOLD,20));

        cms1.setBounds(65,300,200,120);
        cms2.setBounds(225,300,200,120);
        cms3.setBounds(385,300,200,120);
        add(cms1);add(cms2);add(cms3);

        JLabel email1 = new JLabel("rashid.bscsf19@iba-suk.edu.pk");
        JLabel email2 = new JLabel("amanaf.bscsf19@iba-suk.edu.pk");
        JLabel email3 = new JLabel("naveed.bscsf19@iba-suk.edu.pk");

        email1.setFont(new Font(Font.SERIF,Font.PLAIN,12));
        email2.setFont(new Font(Font.SERIF,Font.PLAIN,12));
        email3.setFont(new Font(Font.SERIF,Font.PLAIN,12));

        email1.setBounds(40,330,200,120);
        email2.setBounds(205,330,200,120);
        email3.setBounds(380,330,200,120);
        add(email1);add(email2);add(email3);

        JLabel number1 = new JLabel("0333-3286448");
        JLabel number2 = new JLabel("0300-3141736");
        JLabel number3 = new JLabel("0346-3382422");

        number1.setFont(new Font(Font.SERIF,Font.BOLD,20));
        number2.setFont(new Font(Font.SERIF,Font.BOLD,20));
        number3.setFont(new Font(Font.SERIF,Font.BOLD,20));

        number1.setBounds(50,360,200,120);
        number2.setBounds(215,360,200,120);
        number3.setBounds(380,360,200,120);
        add(number1);add(number2);add(number3);

        JLabel c = new JLabel("Contact us on: ");
        c.setFont(new Font(Font.SERIF,Font.BOLD,20));
        c.setBounds(100,455,200,120);
        add(c);
        img = new ImageIcon(getClass().getClassLoader().getResource("icons/whatsapp.jpg")).getImage().getScaledInstance(30,30,10);
        JLabel whatsapp = new JLabel(new ImageIcon(img));
        whatsapp.setBounds(280,500,30,30);
        add(whatsapp);
        img = new ImageIcon(getClass().getClassLoader().getResource("icons/call.png")).getImage().getScaledInstance(30,30,10);
        JLabel call = new JLabel(new ImageIcon(img));
        call.setBounds(330,500,30,30);
        add(call);
        img = new ImageIcon(getClass().getClassLoader().getResource("icons/gmail.png")).getImage().getScaledInstance(30,30,10);
        JLabel mail = new JLabel(new ImageIcon(img));
        mail.setBounds(380,500,30,30);
        add(mail);
    }
}
