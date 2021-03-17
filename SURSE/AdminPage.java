import javax.swing.*;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AdminPage extends JFrame implements ActionListener, TreeSelectionListener {
    JList users;
    JTextField text1;
    JTextField text2;
    JButton button;
    JScrollPane scrollpane;
    JScrollPane scrollpane2;
    JScrollPane scrollpane3;
    DefaultListModel list;
    JTree jt;
    JTree jt2;
    DefaultMutableTreeNode root;

    public AdminPage() {
        list = new DefaultListModel();
        list.addAll(Application.getInstance().getUsers());

        users = new JList(list);
        JLabel l1 = new JLabel("Department ");
        JLabel l2 = new JLabel("Salary: ");
        JLabel l3 = new JLabel("Welcome, Admin! ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 400));
        this.getContentPane().setBackground(Color.white);
        this.setLayout(new BorderLayout());
        JPanel pane = new JPanel();
        pane.setOpaque(false);
        JPanel pane2 = new JPanel();
        pane2.setOpaque(false);
        ImageIcon icon = new ImageIcon("admin (2).png");

        l3.setIcon(icon);
        l3.setFont(new Font("Times New Roman", Font.BOLD,16));
        l3.setForeground(Color.black);
        l3.setHorizontalTextPosition(JLabel.CENTER);
        l3.setVerticalTextPosition(JLabel.TOP);
        pane2.add(l3);
        pane2.setBackground(Color.white);

        this.add(pane2,BorderLayout.CENTER);
        pane.add(l2);
        button = new JButton("Back");
        button.addActionListener(this);
        text1 = new JTextField(15);
        text2 = new JTextField(15);

        pane.add(text1);

        pane.add(l1);
        pane.add(text2);
        pane.add(button);
        this.add(pane,BorderLayout.NORTH);
        users.setSelectedIndex(0);
        users.setFont(new Font("Times New Roman",Font.ITALIC,15));
        scrollpane = new JScrollPane(users);

        button = new JButton("Sterge ce ai selectat");
        button.addActionListener(this);
        scrollpane.setPreferredSize(new Dimension(100, 100));

        this.add(scrollpane,BorderLayout.SOUTH);

        root = new DefaultMutableTreeNode("Amazon");
        root = recursive_tree(root, Application.getInstance().getCompanies().get(0));
        jt = new JTree(root);
        jt.addTreeSelectionListener(this);
        scrollpane3 = new JScrollPane(jt,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane3.setPreferredSize(new Dimension(250, 100));
        this.add(scrollpane3,BorderLayout.WEST);
        root = new DefaultMutableTreeNode("Google");
        root = recursive_tree(root, Application.getInstance().getCompanies().get(1));
        jt2 = new JTree(root);
        jt2.addTreeSelectionListener(this);
        scrollpane2 = new JScrollPane(jt2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.
                HORIZONTAL_SCROLLBAR_AS_NEEDED);


        scrollpane2.setPreferredSize(new Dimension(250,100));
        this.add(scrollpane2,BorderLayout.EAST);

        JLabel label = new JLabel("Welcome, Admin!");

        this.pack();
        this.show();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainPage main = new MainPage();
        this.dispose();
    }

    static DefaultMutableTreeNode recursive_tree(DefaultMutableTreeNode node, Object obj){
        if(obj instanceof Company){
            Company cmp = (Company) obj;
            for(Department dep : cmp.departments) {
                DefaultMutableTreeNode aux_node = new DefaultMutableTreeNode(dep.getClass().getName());
                aux_node = recursive_tree(aux_node, dep);
                node.add(aux_node);
            }

        }
        if(obj instanceof Department){
            Department dep = (Department) obj;
            DefaultMutableTreeNode aux2_node = new DefaultMutableTreeNode("Jobs");
            DefaultMutableTreeNode aux3_node = new DefaultMutableTreeNode("Employees");
            node.add(aux2_node);
            node.add(aux3_node);
            for(Job job : dep.getJobs()){
                DefaultMutableTreeNode aux_node = new DefaultMutableTreeNode(job.name);
                aux2_node.add(aux_node);
            }
            for(Employee emp : dep.getEmployees()){
                DefaultMutableTreeNode aux_node = new DefaultMutableTreeNode(emp.resume.getInformation().getLastname()+ " "+
                      emp.resume.getInformation().getFirstname() + " - " + emp.resume.getExperiences().get(0).position);
                aux3_node.add(aux_node);
            }
            if(aux2_node.getChildCount() == 0)
                aux2_node.add(new DefaultMutableTreeNode("NO OPEN JOBS"));
        }
        return node;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        if(e.getPath().getLastPathComponent() == e.getPath().getPathComponent(0))
            return ;
      if(e.getPath().getLastPathComponent() == e.getPath().getPathComponent(1) ){
          text2.setText(e.getPath().getLastPathComponent().toString());
          String company_name = e.getPath().getPathComponent(0).toString();
          for(Company cmp : Application.getInstance().getCompanies()){
              if(cmp.company_name.equals(company_name)){
                  for(Department dep : cmp.departments){
                      if(dep.getClass().getName().equals(text2.getText()))
                          text1.setText(""+ dep.getTotalSalaryBudget());
                  }
              }
          }
      }

    }

}
