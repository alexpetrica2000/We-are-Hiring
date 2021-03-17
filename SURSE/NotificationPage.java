import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class NotificationPage extends JFrame implements ActionListener {
    JComboBox comboBox;
    JScrollPane scrollPane;
    DefaultListModel list;
    JList users;
    JLabel label, label1;
    ImageIcon icon = new ImageIcon("notification.png");
    JPanel pane1, pane2, pane3;
    JButton back;
    Vector<Notification> notifications = new Vector<>();
    public NotificationPage() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        this.setVisible(true);
        this.setLayout(new FlowLayout());

        final Image backgroundImage = javax.imageio.ImageIO.read(new File("8.jpg"));
        this.setContentPane(new JPanel(new FlowLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });

        pane1 = new JPanel();
        pane1.setOpaque(false);
        back = new JButton("Back");
        back.addActionListener(this);
        label = new JLabel("Welcome to NotificationPage!");
        label.setForeground(new Color(255, 255, 255));

        label.setIcon(icon);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setHorizontalTextPosition(JLabel.CENTER);
        pane1.add(label);
        pane1.add(back);


        comboBox = new JComboBox();
        for(Consumer cns : Application.getInstance().getUsers())
            comboBox.addItem(cns);
        comboBox.addActionListener(this);
        pane2 = new JPanel();
        pane2.setOpaque(false);
        label1 = new JLabel("Please select the user: ");
        label1.setForeground(new Color(255, 255, 255));
        label1.setVerticalTextPosition(JLabel.CENTER);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        pane2.add(label1);
        pane2.add(comboBox);

        pane3 = new JPanel();
        pane3.setOpaque(false);
        list = new DefaultListModel();
        users = new JList(list);
        scrollPane = new JScrollPane(users);
        scrollPane.setPreferredSize(new Dimension(490,250));
        list.addAll(((User)comboBox.getSelectedItem()).notifications);
        pane3.add(scrollPane);
        this.add(pane1);
        this.add(pane2);
        this.add(pane3);
        this.pack();
        this.setLocationRelativeTo(null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == comboBox) {
            list.removeAllElements();
            list.addAll(((User) comboBox.getSelectedItem()).notifications);
        }
        if(e.getSource() == back){
            JFrame frame = new MainPage();
            this.dispose();
        }
    }
}
