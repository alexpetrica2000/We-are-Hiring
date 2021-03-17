import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthentificationPage extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField field1;
    JPasswordField field2;
    JPanel pane1, pane2;
    JButton button, back;
    HashMap<String,String> login = new HashMap<>();
    static int tries = 3;

    public AuthentificationPage() throws IOException{
        login.put("admin","admin");
        login.put("manager","manager");
        login.put("user","user");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(230,260));
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        final Image backgroundImage = javax.imageio.ImageIO.read(new File("1900851.png"));
        this.setContentPane(new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });

        pane1 = new JPanel();
        pane1.setOpaque(false);
        label1 = new JLabel("Username: ");
        label1.setForeground(Color.lightGray);
        label1.setFont(new Font("Times New Roman", Font.BOLD,15));
        label2 = new JLabel("Password: ");
        label2.setForeground(Color.lightGray);
        label2.setFont(new Font("Times New Roman", Font.BOLD,15));
        button = new JButton("Login");
        button.addActionListener(this);
        back = new JButton("Exit");
        back.addActionListener(this);
        field1 = new JTextField(15);
        field2 = new JPasswordField(15);
        pane1.add(label1);
        pane1.add(field1);
        pane1.add(label2);
        pane1.add(field2);
        pane1.add(button);
        pane1.add(back);

        this.add(pane1,BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        JOptionPane.showConfirmDialog(null,"Hello, in order to have access" +
                " to the app, you have to login!","Notify",JOptionPane.CLOSED_OPTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == back){
                this.dispose();
                System.exit(0);
            }
            boolean correct_login = false;
            for(Map.Entry<String, String> entry : login.entrySet()){
                if(entry.getValue().equals(field2.getText()) && entry.getKey().equals(field1.getText()))
                    correct_login = true;
            }
            if(e.getSource() == button && correct_login){
                    JFrame frame = new MainPage();
                    this.dispose();
            }
            else {
                tries--;
                if(tries != 0)
                JOptionPane.showConfirmDialog(null,"Incorrect Username / Password. " +
                        "You have "+tries+" attempts left.","Error",JOptionPane.CLOSED_OPTION);
                else {
                    JOptionPane.showConfirmDialog(null,"There are no attempts left. " +
                            "The application will be closed","Error",JOptionPane.CLOSED_OPTION);
                    System.exit(0);
                }
            }
    }
}
