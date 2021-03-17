public class Employee extends Consumer{
    String company_name;
    double salary;

    public Employee(){
        this(null,0.0);
    }
    public Employee(String company_name, double salary){
        this.company_name = company_name;
        this.salary = salary;
    }
    public void setCompany(String company_name){
        this.company_name = company_name;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public String toString(){
        String s = " ";
        s =  " Lastname: " + resume.getInformation().getLastname()
                + " " + " Firstname: " + resume.getInformation().getFirstname() + " Job: " +
                resume.getExperiences().get(0).position;
//                " Email: " + resume.getInformation().getEmail()
//            + " Telefon: "+ resume.getInformation().getPhone() +" Data Nasterii: " + resume.getInformation().getBirthday()
//            + " Sex: " + resume.getInformation().getSex() + " Limbi Cunoscute: "+ resume.getInformation().getKnownLanguages()+
//        " Educatie: " + resume.getEducations()+ "Experienta: "+ resume.getExperiences();
        return s;
    }
}
