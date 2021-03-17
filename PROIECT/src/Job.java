import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Job {
    String name;
    String company_name;
    boolean flag;
    Constraint graduationYear;
    Constraint experienceYears;
    Constraint GPA;
    ArrayList<Consumer> candidadates = new ArrayList<>();
    int noPositions;
    double salary;

    public Job(){
        this(null,null,false,null,
                null,null,0,0.0);
    }
    public Job(String name, String company_name, boolean flag, Constraint graduationYear,
                Constraint experienceYears, Constraint GPA, int noPositions, double salary){
        this.name = name;
        this.company_name = company_name;
        this.flag = flag;
        this.graduationYear = graduationYear;
        this.experienceYears = experienceYears;
        this.GPA = GPA;
        this.noPositions = noPositions;
        this.salary = salary;
    }

    public void apply(User user){

        Company company = Application.getInstance().getCompany(company_name);
        if(!company.observers.contains(user))
            company.addObserver(user);
        Recruiter recruiter = company.getRecruiter(user);
        Manager manager = company.manager;
        Request<Job,Consumer> request = new Request<Job,Consumer>(this,user,recruiter,recruiter.evaluate(this, user)*1.0);
        if(meetsRequirments(user)) {
            manager.requests.add(request);
            candidadates.add(user);
        }
        else
        {
            user.update(new Notification(user.resume.getInformation().getLastname()+ " You were rejected for job "+
                    this.name+" of company "+company_name+ " because you did not meet the minimum requirements"));
        }
    }
    public boolean meetsRequirments(User user){

        if(user.getGraduationYear() < graduationYear.inferior_limit || user.getGraduationYear() > graduationYear.superior_limit)
            return false;
        if(user.getExperience() < experienceYears.inferior_limit || user.getExperience() > experienceYears.superior_limit)
            return false;
        if(user.meanGPA() < GPA.inferior_limit || user.meanGPA() > GPA.superior_limit)
            return false;

        return true;
    }
    public String toString(){
//        return name + " " + company_name+ " " + graduationYear.inferior_limit + " "+ graduationYear.superior_limit + " " + experienceYears.inferior_limit
//                + " " + experienceYears.superior_limit + " " + GPA.inferior_limit + " " + GPA.superior_limit;
        return name;
    }

}
