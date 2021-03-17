public class Recruiter extends Employee{
    double rating = 5;
    public int evaluate(Job job, User user){
        rating += 0.1;
        return (int)(rating * user.getTotalScore()*100);
    }
    public String toString(){
        String s = " ";
        s =  " Lastname: " + resume.getInformation().getLastname()
                + " " + " Firstname: " + resume.getInformation().getFirstname() + " Job: " +
                resume.getExperiences().get(0).position + " Rating: " + String.format("%.2f",rating);
        return s;
    }
}
