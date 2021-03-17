import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Flow;

public class ProfilePage extends JFrame implements ActionListener {
    JTextField field1, field2, field3, field4, field5, field6;
    JLabel label1, label2, label3, label4, label5, label6,label7, label8, label9;
    JPanel pane1,pane2,pane3, pane4, pane5, pane6;
    JButton button, button2;
    JComboBox comboBox, comboBox2, combobox3;
    Vector<Consumer> consumers = Application.getInstance().getAllUsers();
    public ProfilePage() throws IOException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1100,500));
        this.setVisible(true);
        this.setLayout(new FlowLayout());

        ImageIcon icon = new ImageIcon("iconfinder.png");

        final Image backgroundImage = javax.imageio.ImageIO.read(new File("9.jpg"));
        this.setContentPane(new JPanel(new FlowLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });


        button = new JButton("Find");
        button.addActionListener(this);
        button2 = new JButton("Back");
        button2.addActionListener(this);

        pane4 = new JPanel();
        pane4.setOpaque(false);
        label4 = new JLabel("Welcome, User!");
        field4 = new JTextField(15);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setIcon(icon);
        label4.setHorizontalTextPosition(JLabel.CENTER);
        label4.setVerticalTextPosition(JLabel.TOP);
        label4.setForeground(Color.LIGHT_GRAY);
        pane4.add(label4);




        pane1 = new JPanel();
        pane1.setOpaque(false);
        pane1.setPreferredSize(new Dimension(500,100));
        label1 = new JLabel("Lastname: ");
        label1.setForeground(Color.LIGHT_GRAY);
        field1 = new JTextField(15);

        pane1.add(label1);
        pane1.add(field1);


        label2 = new JLabel("Firstname : ");
        label2.setForeground(Color.LIGHT_GRAY);
        field2 = new JTextField(15);

        pane1.add(label2);
        pane1.add(field2);
        pane1.add(button);
        pane1.add(button2);


        pane2 = new JPanel();
        pane2.setOpaque(false);
        pane2.setPreferredSize(new Dimension(500,100));
        label3 = new JLabel("Email : ");
        label3.setForeground(Color.LIGHT_GRAY);
        field3 = new JTextField(15);
        field3.setEditable(false);
        field3.setBackground(Color.white);
        pane2.add(label3);
        pane2.add(field3);


        label4 = new JLabel("Phone: ");
        label4.setForeground(Color.LIGHT_GRAY);
        field4 = new JTextField(15);
        field4.setEditable(false);
        field4.setBackground(Color.white);
        pane2.add(label4);
        pane2.add(field4);

        pane3 = new JPanel();
        pane3.setOpaque(false);
        pane3.setPreferredSize(new Dimension(500,100));
        field5 = new JTextField(15);
        field5.setEditable(false);
        field5.setBackground(Color.white);
        label5 = new JLabel("Birthday: ");
        label5.setForeground(Color.LIGHT_GRAY);
        pane3.add(label5);
        pane3.add(field5);

        field6 = new JTextField(15);
        field6.setBackground(Color.white);
        field6.setEditable(false);
        label6 = new JLabel("Sex: ");
        label6.setForeground(Color.LIGHT_GRAY);
        pane3.add(label6);
        pane3.add(field6);


        pane6 = new JPanel();
        pane6.setOpaque(false);
        pane6.setPreferredSize(new Dimension(1100,100));
        combobox3 = new JComboBox();
        label9 = new JLabel("Known Languages: ");
        label9.setForeground(Color.LIGHT_GRAY);
        pane6.add(label9);
        pane6.add(combobox3);



        pane5 = new JPanel();
        pane5.setOpaque(false);
        label7 = new JLabel("Experiences: ");
        label7.setForeground(Color.LIGHT_GRAY);
        label8 = new JLabel("Educations: ");
        label8.setForeground(Color.LIGHT_GRAY);
        comboBox = new JComboBox();
        comboBox2 = new JComboBox();
        pane5.add(label7);
        pane5.add(comboBox);
        pane5.add(label8);
        pane5.add(comboBox2);


        this.add(pane4);
        this.add(pane1);
        this.add(pane2);
        this.add(pane3);
        this.add(pane6);
        this.add(pane5);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button2){
            MainPage main = new MainPage();
            this.dispose();
            return;
        }
        comboBox.removeAllItems();
        comboBox2.removeAllItems();
        combobox3.removeAllItems();

        for(Consumer cns : consumers)
            if(cns.resume.getInformation().getFirstname().equals(field2.getText()))
                if(cns.resume.getInformation().getLastname().equals(field1.getText())) {
                    for (Experience exp : cns.resume.getExperiences())
                        comboBox.addItem(exp);
                    for (Education edc : cns.resume.getEducations())
                        comboBox2.addItem(edc);
                    for(Map.Entry<String, String> entry : cns.resume.getInformation().getLanguages().entrySet())
                        combobox3.addItem(entry.getKey() + " - " + entry.getValue());
                    field3.setText(cns.resume.getInformation().getEmail());
                    field4.setText(cns.resume.getInformation().getPhone());
                    field5.setText(cns.resume.getInformation().getBirthday());
                    field6.setText(cns.resume.getInformation().getSex());
                    return;
                }

        JOptionPane.showConfirmDialog(null,"There's is no person with this name in our " +
                "database. Try again!","Error",JOptionPane.CLOSED_OPTION);
                field1.setText("");
                field2.setText("");
                field3.setText("");
                field4.setText("");
                field5.setText("");
                field6.setText("");
    }
}
