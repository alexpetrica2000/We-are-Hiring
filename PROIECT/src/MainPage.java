import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Flow;

public class MainPage extends JFrame implements ActionListener {
    JButton adminpage, managerpage, userpage, notificationpage, companypage;
    JLabel label;
    JPanel pane;

    public MainPage(){
        ImageIcon icon = new ImageIcon("abc (3).png");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.setVisible(true);
        this.setLayout(new GridLayout(2,1));
        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("2.jpg"));
            this.setContentPane(new JPanel(new GridLayout(2,1)) {
                @Override
                public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        }

        catch (IOException i){
            i.printStackTrace();
        }

        label = new JLabel("Welcome to the MainPage!");
        label.setForeground(Color.white);
        label.setIcon(icon);
        label.setMaximumSize(new Dimension(100,100));
        JPanel pane1 = new JPanel();
        pane1.setOpaque(false);
        pane1.setLayout(new FlowLayout());
        label.setFont(new Font("Times New Roman",Font.BOLD,15));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        pane1.add(label);

        pane1.setMaximumSize(new Dimension(100,100));
        pane1.setBackground(Color.darkGray);
        this.add(pane1);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new GridLayout(5,1));
        pane2.setSize(new Dimension(400,400));
        adminpage = new JButton("AdminPage");
        adminpage.addActionListener(this);
        managerpage = new JButton("ManagerPage");
        managerpage.addActionListener(this);
        userpage = new JButton("ProfilePage");
        userpage.addActionListener(this);
        notificationpage = new JButton("NotificationPage");
        notificationpage.addActionListener(this);
        companypage = new JButton("CompanyPage");
        companypage.addActionListener(this);
        pane2.add(adminpage);
        pane2.add(managerpage);
        pane2.add(userpage);
        pane2.add(notificationpage);
        pane2.add(companypage);
        this.add(pane2);

        this.pack();
        this.setLocationRelativeTo(null);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == adminpage) {
            JFrame frame = new AdminPage();
            this.dispose();
        }
        if(e.getSource() == userpage) {
            try {
                JFrame frame = new ProfilePage();
                this.dispose();
            }
            catch (IOException i){
                i.printStackTrace();
            }
        }
        if(e.getSource() == managerpage){
            try {
                JFrame frame = new ManagerPage();
                this.dispose();
            }
            catch (IOException i){
                i.printStackTrace();
            }
        }
        if(e.getSource() == notificationpage){
            try {
                JFrame frame = new NotificationPage();
                this.dispose();
            }
            catch(IOException i){
                i.printStackTrace();
            }
        }
        if(e.getSource() == companypage){
            try {
                JFrame frame = new CompanyPage();
                this.dispose();
            }
            catch(Exception i){
                i.printStackTrace();
            }
        }
    }
}
