import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Vector;

public class CompanyPage extends JFrame implements ActionListener, ListSelectionListener {
    JButton back, move, make;
    JLabel label,label1, label2, label3, label4, label5, label6, label7, label8;
    JComboBox company;
    JTextField field, field2, field3, field4;
    JPanel pane1, pane2, pane3, pane4, pane5, pane6, pane7, pane8;
    Vector<Company> companies = new Vector<>();
    ImageIcon icon = new ImageIcon("amazon.png");
    ImageIcon icon2 = new ImageIcon("google.png");
    JScrollPane scrollPane1, scrollPane2, scrollPane3, scrollPane4, scrollpane5, scrollpane6;
    DefaultListModel list1, list2, list3, list4, list5, list6, list7;
    JList departments_list, recruiters_list, observers_list, employees_list, jobs_list;

    public CompanyPage() throws Exception{
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1100,500));
        this.setVisible(true);
        this.setLayout(new FlowLayout());


        final Image backgroundImage = javax.imageio.ImageIO.read(new File("29.jpeg"));
        this.setContentPane(new JPanel(new FlowLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });


        pane1 = new JPanel();
        pane1.setPreferredSize(new Dimension(1100,100));
        label1 = new JLabel("Welcome to CompanyPage!");
        label1.setForeground(Color.white);
        label1.setIcon(icon);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.TOP);
        label2 = new JLabel("Select the company: ");
        label3 = new JLabel("Manager of the Company: ");
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        field = new JTextField(12);
        field.setEditable(false);
        field.setBackground(Color.white);
        back = new JButton("Back");
        back.addActionListener(this);
        companies.addAll(Application.getInstance().getCompanies());
        company = new JComboBox(companies);
        company.addActionListener(this);


        pane1.add(label1);
        pane1.add(label2);
        pane1.add(company);
        pane1.add(label3);
        pane1.add(field);
        pane1.add(back);




        pane2 = new JPanel();
        pane2.setPreferredSize(new Dimension(200,100));
        pane2.setLayout(new BorderLayout());
        pane3 = new JPanel();
        pane3.setPreferredSize(new Dimension(200,100));
        pane3.setLayout(new BorderLayout());
        pane4 = new JPanel();
        pane4.setPreferredSize(new Dimension(210,100));
        pane4.setLayout(new BorderLayout());
        pane5 = new JPanel();
        pane5.setPreferredSize(new Dimension(200,100));
        pane5.setLayout(new BorderLayout());
        pane6 = new JPanel();
        pane6.setPreferredSize(new Dimension(300,100));
        pane6.setLayout(new BorderLayout());


        list1 = new DefaultListModel();
        departments_list = new JList(list1);
        label = new JLabel("              Departments");
        label2 = new JLabel("                 Recruiters");
         label3 = new JLabel("               Observers");
        label4 = new JLabel("                             Employees");
        label5 = new JLabel("                    Jobs");
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        label4.setForeground(Color.white);
        label5.setForeground(Color.white);
        label.setForeground(Color.white);
        scrollPane1 = new JScrollPane(departments_list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.
                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        list2 = new DefaultListModel();
        recruiters_list = new JList(list2);
        scrollPane2 = new JScrollPane(recruiters_list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.
                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        list3 = new DefaultListModel();
        observers_list = new JList(list3);
        scrollPane3 = new JScrollPane(observers_list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.
                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        list4 = new DefaultListModel();
        jobs_list = new JList(list4);
        scrollPane4 = new JScrollPane(jobs_list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.
                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        list5 = new DefaultListModel();
        employees_list = new JList(list5);
        employees_list.addListSelectionListener(this);
        scrollpane5 = new JScrollPane(employees_list,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.
                HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane2.add(label,BorderLayout.PAGE_START);
        pane2.add(scrollPane1);
        pane3.add(label2,BorderLayout.PAGE_START);
        pane3.add(scrollPane2);
        pane4.add(label3,BorderLayout.PAGE_START);
        pane4.add(scrollPane3);
        pane5.add(label5,BorderLayout.PAGE_START);
        pane5.add(scrollPane4);
        pane6.add(label4,BorderLayout.PAGE_START);
        pane6.add(scrollpane5);

        pane7 = new JPanel();
        label6 = new JLabel("Employee name & Department: ");
        label6.setForeground(Color.white);
        field2 = new JTextField(15);
        field2.setEditable(false);
        field2.setBackground(Color.white);
        label7 = new JLabel("Move to department: ");
        label7.setForeground(Color.white);
        field3 = new JTextField(15);
        move = new JButton("Move");
        move.addActionListener(this);
        pane7.setPreferredSize(new Dimension(1100,80));
        pane7.add(label6);
        pane7.add(field2);
        pane7.add(label7);
        pane7.add(field3);
        pane7.add(move);

        pane8 = new JPanel();
        pane8.setPreferredSize(new Dimension(1100,80));
        label8 = new JLabel("Make Recruiter: ");
        label8.setForeground(Color.white);
        field4 = new JTextField(15);
        field4.setEditable(false);
        field4.setBackground(Color.white);
        make = new JButton("Make");
        make.addActionListener(this);
        pane8.add(label8);
        pane8.add(field4);
        pane8.add(make);


        company.setSelectedIndex(0);
        pane1.setOpaque(false);
        pane2.setOpaque(false);
        pane3.setOpaque(false);
        pane4.setOpaque(false);
        pane5.setOpaque(false);
        pane6.setOpaque(false);
        pane7.setOpaque(false);
        pane8.setOpaque(false);
        this.add(pane1);
        this.add(pane2);
        this.add(pane3);
        this.add(pane4);
        this.add(pane5);
        this.add(pane6);
        this.add(pane7);
        this.add(pane8);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            MainPage main = new MainPage();
            this.dispose();
        }
        if(e.getSource() == company){
            if(company.getSelectedItem().toString().equals("Google")) {
                label1.setIcon(icon2);
                field.setText(Application.getInstance()
                        .getCompany("Google").manager.resume
                        .getInformation().getFirstname()+" "+Application.getInstance()
                        .getCompany("Google").manager.resume
                        .getInformation().getLastname());
                list1.removeAllElements();
                list2.removeAllElements();
                list3.removeAllElements();
                list4.removeAllElements();
                list5.removeAllElements();
                list1.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).departments);
                list2.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).recruiters);
                list3.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).observers);
                list4.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).getJobs());
                list5.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).getEmployees());
            }
            else {
                label1.setIcon(icon);
                field.setText(Application.getInstance()
                        .getCompany("Amazon").manager.resume
                        .getInformation().getFirstname()+" "+Application.getInstance()
                        .getCompany("Amazon").manager.resume
                        .getInformation().getLastname());
                list1.removeAllElements();
                list2.removeAllElements();
                list3.removeAllElements();
                list4.removeAllElements();
                list5.removeAllElements();
                list1.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).departments);
                list2.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).recruiters);
                list3.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).observers);
                list4.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).getJobs());
                list5.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).getEmployees());
            }
        }
        if(e.getSource() == move){
            Employee emp = (Employee) employees_list.getSelectedValue();
            for(Department dep : Application.getInstance()
                    .getCompany(company.getSelectedItem().toString()).departments)
                if(dep.getClass().getName().equals(field3.getText()))
                    Application.getInstance().getCompany(company.getSelectedItem().toString())
                    .move(emp,dep);
                field2.setText("");
                field3.setText("");
                field4.setText("");
        }
        if(e.getSource() == make){
            Recruiter rec = new Recruiter();
            Employee emp = (Employee)employees_list.getSelectedValue();
            if(emp instanceof  Recruiter){
                JOptionPane.showConfirmDialog(null,"Already a Recruiter!",
                        "Error",JOptionPane.CLOSED_OPTION);
                return;
            }
            rec.friends = emp.friends;
            rec.resume = emp.resume;
            rec.resume.getExperiences().get(0).end_date = LocalDate.now();

            try {
                Experience exp = new Experience(LocalDate.now().toString(), null, "Recruiter",
                        company.getSelectedItem().toString());
                rec.add(exp);
            }catch (InvalidDatesException i){
                i.printStackTrace();
            }

            for(Department dep : Application.getInstance()
                    .getCompany(company.getSelectedItem().toString()).departments)
                if(dep.employees.contains(emp)){
                    dep.remove(emp);
                    dep.add(rec);

                    Application.getInstance()
                            .getCompany(company.getSelectedItem().toString()).recruiters.add(rec);
                }

            list2.removeAllElements();
                list5.removeAllElements();
            list2.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).recruiters);
            list5.addAll(Application.getInstance().getCompany(company.getSelectedItem().toString()).getEmployees());
            field2.setText("");
            field4.setText("");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == employees_list){
            Employee emp = (Employee)employees_list.getSelectedValue();
            if(emp == null)
                return;
            String s = emp.resume.getInformation().getLastname() + " "+
                    emp.resume.getInformation().getFirstname();
            String s2 = new String();
            for(Department dep : Application.getInstance()
                                .getCompany(company.getSelectedItem().toString()).departments){
                if(dep.employees.contains(emp))
                    s2 = s + " - " + dep.getClass().getName();
            }
            field2.setText(s2);
            field4.setText(s);
        }
    }
}
