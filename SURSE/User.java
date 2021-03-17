import java.util.ArrayList;

public class User extends Consumer implements Observer{
    ArrayList<String> jobList = new ArrayList<>();
    ArrayList<Notification> notifications = new ArrayList<>();
    public Employee convert(){
        Employee employee = new Employee();
        employee.resume = this.resume;
        employee.friends = this.friends;

        return employee;
    }
     public Double getTotalScore(){
         return this.getExperience()*1.5 + this.meanGPA();
     }
    public String toString(){
        String s = " ";
        s = s + "Lastname: " + resume.getInformation().getLastname() + " Firstname: "+resume.getInformation().getFirstname();
//        s =     " Lastname: " + resume.getInformation().getLastname()
//                + " " + " Firstname: " + resume.getInformation().getFirstname() + " Email: " + resume.getInformation().getEmail()
//                + " Phone: "+ resume.getInformation().getPhone() +" Birthday: " + resume.getInformation().getBirthday()
//                + " Sex: " + resume.getInformation().getSex() + " Languages: "+ resume.getInformation().getKnownLanguages()+
//                " Educations: " + resume.getEducations() + " Experieces: "+ resume.getExperiences();
        return s;
    }

    @Override
    public void update(Notification notification) {
        notifications.add(notification);
    }
}
