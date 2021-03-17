import java.util.ArrayList;

public abstract class Department {
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Job> jobs = new ArrayList<>();

    public abstract double getTotalSalaryBudget();

    public ArrayList<Job> getJobs(){
       return jobs;
    }
    public void add(Employee employee){
        employees.add(employee);
    }
    public void remove(Employee employee){
        employees.remove(employee);
    }
    public void add(Job job){
        jobs.add(job);
        for(Company cmp : Application.getInstance().getCompanies())
            for(Department dep : cmp.departments){
                if(dep.equals(this))
                    cmp.notifyAllObservers(new Notification("Job "+job.name+" of company " +cmp.company_name +
                            " is open!"));
            }
    }
    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    public String toString(){
        return getClass().getName();
    }
}
