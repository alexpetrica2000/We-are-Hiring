import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Company implements Subject{
    String company_name;
    Manager manager;
    ArrayList<Department> departments = new ArrayList<>();
    ArrayList<Recruiter> recruiters = new ArrayList<>();
    ArrayList<Observer> observers = new ArrayList<>();

    public Company(){
        this(null,null);
    }
    public Company(String company_name, Manager manager){
        this.company_name = company_name;
        this.manager = manager;
    }

    public void add(Department department){
        departments.add(department);
    }
    public void add(Recruiter recruiter){
        recruiters.add(recruiter);
        Collections.sort(recruiters, new Comparator<Recruiter>() {
            @Override
            public int compare(Recruiter o1, Recruiter o2) {
                return (int)((o2.rating - o1.rating)*100);
            }
        });
    }
    public void add(Employee employee, Department department){
        for(Department aux : departments)
            if(aux.equals(department))
                aux.add(employee);
    }
    public void remove(Employee employee){
        for(Department department : departments)
            if(department.employees.contains(employee))
                department.employees.remove(department);
    }
    public void remove(Department department){
        departments.remove(department);
    }
    public void remove(Recruiter recruiter){
        recruiters.remove(recruiter);
        this.remove(recruiter);
    }

     public void move(Department source, Department destination){
        Department aux = source;
        departments.remove(source);
        for(Job job : aux.getJobs())
            destination.add(job);
        for(Employee emp : aux.getEmployees())
            destination.add(emp);
    }
    public void move(Employee employee, Department newDepartment){
        for(Department aux : departments) {
            if (aux.employees.contains(employee))
                aux.employees.remove(employee);
            if (aux.equals(newDepartment))
                aux.employees.add(employee);
        }
    }
    public boolean contains(Department department){
        return departments.contains(department);
    }
    public boolean contains(Employee employee){
        for(Department department : departments)
            if(department.employees.contains(employee))
                return true;
        return false;
    }
    public boolean contains(Recruiter recruiter){
        return recruiters.contains(recruiter);
    }
    public Recruiter getRecruiter(User user){
        Company company = Application.getInstance().getCompany(company_name);
        ArrayList<Recruiter> availableRecruiters = new ArrayList<Recruiter>();
        int max = 1;
        boolean recruiter_flag = false;
        for(Recruiter recruiter : company.recruiters){
            if(recruiter.getDegreeInFriendship(user) == 0)
                recruiter_flag = true;
            else {
                if(recruiter.getDegreeInFriendship(user) > max)
                    max = recruiter.getDegreeInFriendship(user);
            }
        }
        if(recruiter_flag)
            max = 0;
        for(Recruiter recruiter : company.recruiters)
            if(recruiter.getDegreeInFriendship(user) == max)
                availableRecruiters.add(recruiter);
        Collections.sort(availableRecruiters, new Comparator<Recruiter>() {
            @Override
            public int compare(Recruiter o1, Recruiter o2) {
                return (int)(o2.rating*100 - o1.rating*100);
            }
        });

        return availableRecruiters.get(0);
    }

    public String toString(){
        return company_name;
    }



    public ArrayList<Job> getJobs(){
        ArrayList<Job> available_jobs = new ArrayList<>();
        for(Department dep : departments){
            available_jobs.addAll(dep.jobs);
        }
        return available_jobs;
    }

    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        for(Department dep : departments){
            employees.addAll(dep.employees);
        }
        return employees;
    }

    @Override
    public void addObserver(User user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(User c) {
        observers.remove(c);
    }

    @Override
    public void notifyAllObservers(Notification notification) {
        for(Observer obs : observers)
            obs.update(notification);
    }
}
