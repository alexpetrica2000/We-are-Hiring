import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Vector;

public class ManagerPage extends JFrame implements ActionListener {
    JComboBox comboBox;
    JLabel label1, label2;
    Vector<Manager> companii = new Vector<>();
    JList requests;
    JScrollPane scrollpane;
    DefaultListModel list = new DefaultListModel();
    JButton button, button2, back, process;
    public ManagerPage() throws IOException {

        ImageIcon icon = new ImageIcon("1087815.png");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(560,500));
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        final Image backgroundImage = javax.imageio.ImageIO.read(new File("14.jpg"));
        this.setContentPane(new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });

        JPanel pane1 = new JPanel();
        pane1.setOpaque(false);
        back = new JButton("Back");
        back.addActionListener(this);
        label1 = new JLabel("Welcome to Manager Page!");
        label1.setForeground(new Color(38, 33, 33));
        label1.setIcon(icon);
        label1.setVerticalTextPosition(JLabel.TOP);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        pane1.add(label1);
        pane1.add(back);


        JPanel pane2 = new JPanel();
        pane2.setOpaque(false);
        for(Company cmp : Application.getInstance().getCompanies()){
            companii.add(cmp.manager);
        }

        label2 = new JLabel("Choose the manager: ");
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label2.setForeground(new Color(38, 33, 33));

        comboBox = new JComboBox(companii);
        comboBox.setSelectedIndex(0);
        String []splited = comboBox.getSelectedItem().toString().split(" ");
        list.addAll(Application.getInstance().getCompany(splited[1]).manager.requests);
        comboBox.addActionListener(this);
        button = new JButton("Accept");
        button.addActionListener(this);
        button2 = new JButton(("Denied"));
        button2.addActionListener(this);
        process = new JButton("Process");
        process.addActionListener(this);
        pane2.add(label2);
        pane2.add(comboBox);
        pane2.add(button);
        pane2.add(button2);
        pane2.add(process);

        requests = new JList(list);
        requests.setSelectedIndex(0);
        scrollpane = new JScrollPane(requests);
        scrollpane.setPreferredSize(new Dimension(550,200));
        JPanel pane3 = new JPanel();
        pane3.add(scrollpane);



        this.add(pane1,BorderLayout.NORTH);
        this.add(pane2,BorderLayout.CENTER);
        this.add(pane3,BorderLayout.SOUTH);


        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == comboBox){
            String []splited =  comboBox.getSelectedItem().toString().split(" ");

            if(splited[1].equals("Google")){
                Company cmp = Application.getInstance().getCompany("Google");
                list.removeAllElements();
                list.addAll(cmp.manager.requests);
                requests.setSelectedIndex(0);
            }
            if(splited[1].equals("Amazon")){
                Company cmp = Application.getInstance().getCompany("Amazon");
                list.removeAllElements();
                list.addAll(cmp.manager.requests);
                requests.setSelectedIndex(0);
            }


        }
        if(e.getSource() == button){
            String []splited = comboBox.getSelectedItem().toString().split(" ");
            Company cmp = Application.getInstance().getCompany(splited[1]);
            User cns = (User)((Request)requests.getSelectedValue()).getValue1();
            Job job = (Job)((Request)requests.getSelectedValue()).getKey();

            if(job.noPositions == 0){
                JOptionPane.showConfirmDialog(null,"Manager, there are no positions left " +
                        "for this job!","Error",JOptionPane.CLOSED_OPTION);
                return;
            }
            for(Department dep : cmp.departments){
                for(Job needed_job : dep.getJobs()){
                    if(job.equals(needed_job)){
                        Employee emp = cns.convert();
                        emp.salary = job.salary;
                        emp.company_name = job.company_name;;
                        dep.add(emp);
                        job.noPositions--;

                        Application.getInstance().getUsers().remove(cns);
                        try {
                            Experience exp = new Experience(LocalDate.now().toString(), null, job.name,
                                    Application.getInstance().getCompany(job.company_name).company_name);
                            emp.add(exp);
                        }
                        catch(InvalidDatesException i){
                            i.printStackTrace();
                        }
                    }
                }
            }



            Iterator<Company> cmp2 = Application.getInstance().getCompanies().iterator();
            while(cmp2.hasNext()){
                Company aux = cmp2.next();
                aux.observers.remove(cns);
                Iterator<Request<Job,Consumer>> req = aux.manager.requests.iterator();
                while(req.hasNext()){
                    Request request = req.next();
                    if(request.getValue1().equals(cns))
                        req.remove();
                }
            }
            if(job.noPositions == 0) {
                Iterator<Department> dep = cmp.departments.iterator();
                while (dep.hasNext()) {
                    Iterator<Job> jobs = dep.next().getJobs().iterator();
                    while (jobs.hasNext()) {
                        Job aux = jobs.next();
                        if (aux.equals(job)) {
                            aux.flag = false;
                            jobs.remove();
                            cmp.notifyAllObservers(new Notification("Job " + job.name +
                                    " of company "+cmp.company_name+" has been closed"));
                        }
                    }
                }
            }
            list.removeAllElements();
            list.addAll(cmp.manager.requests);
            requests.setSelectedIndex(0);

        }
        if(e.getSource() == back){
            MainPage main = new MainPage();
            this.dispose();
        }
        if(e.getSource() == button2){
            String []splited = comboBox.getSelectedItem().toString().split(" ");
            Company cmp = Application.getInstance().getCompany(splited[1]);
            cmp.manager.requests.remove(((Request)requests.getSelectedValue()));
            User cns = (User)((Request)requests.getSelectedValue()).getValue1();
            Job job = (Job)((Request)requests.getSelectedValue()).getKey();
            cns.update(new Notification(cns.resume.getInformation().getLastname()+ " You were rejected" +
                    " for job "+ job.name+" of company "+cmp.company_name+" because of Manager decision"));
            list.removeAllElements();
            list.addAll(cmp.manager.requests);
            requests.setSelectedIndex(0);
        }
        if(e.getSource() == process){
            String []splited = comboBox.getSelectedItem().toString().split(" ");
            Company cmp = Application.getInstance().getCompany(splited[1]);
            for(Job job : cmp.getJobs()){
                cmp.manager.process(job);
            }
            list.removeAllElements();
            list.addAll(cmp.manager.requests);
            requests.setSelectedIndex(0);
        }
    }


}
