import java.util.*;

public class Application {

    private ArrayList<Company> companies;
    private ArrayList<User> users;
    private static Application instance = null;

    private Application(){
        companies = new ArrayList<>();
        users = new ArrayList<>();
    }
    public ArrayList<Company> getCompanies(){
        return companies;
    }
    public Company getCompany(String name){
        for(Company obj : companies)
            if(obj.company_name.equals(name))
                return obj;

        return null;
    }
    public ArrayList<User> getUsers(){ return users;}
    public User getUser(String firstname, String lastname){
        for(User user : users)
            if(user.resume.getInformation().getFirstname().equals(firstname))
                if(user.resume.getInformation().getLastname().equals((lastname)))
                    return user;
        return null;
    }
    public static Application getInstance(){
        if(instance == null)
            instance = new Application();
        return instance;
    }
    public void add(Company company){
        companies.add(company);
    }
    public void add(User user){
        users.add(user);
    }
    public boolean remove(Company company){
        if(!companies.contains(company))
            return false;
        companies.remove(company);
        return true;
    }
    public boolean remove(User user){
        if(!users.contains(user))
            return false;
        users.remove(user);
        return true;
    }


     public ArrayList<Job> getJobs(List<String> companies){
        ArrayList<Job> jobs = new ArrayList<>();
        for(String company_name : companies)
            for(Company aux : this.companies){
                if(aux.company_name.equals(company_name))
                    jobs.addAll(aux.getJobs());
            }
        return jobs;
     }
     public Vector<Consumer> getAllUsers() {

         Vector<Consumer> consumers = new Vector<>();
         consumers.addAll(getUsers());
         for (Company cmp : companies)
             for (Department dep : cmp.departments)
                    consumers.addAll(dep.getEmployees());

         return consumers;
     }
}

