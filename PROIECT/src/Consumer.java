
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.*;

public abstract class Consumer {
    Resume resume;
    ArrayList<Consumer> friends;

    public Consumer(){
        resume = null;
        friends = new ArrayList<>();
    }
    public Consumer(Resume resume){
        this.resume = resume;
    }
    public static class Resume{
        private Information information;
        private ArrayList<Education> educations  = new ArrayList<>();
        private ArrayList<Experience> experiences = new ArrayList<>();

        private Resume(ResumeBuilder builder){
            this.information = builder.information;
            this.educations = builder.educations;
            this.experiences = builder.experiences;
        }
        public Information getInformation() { return information; }
        public ArrayList<Education> getEducations() { return educations; }
        public ArrayList<Experience> getExperiences() { return experiences; }

        public static class ResumeBuilder{
            private Information information;
            private ArrayList<Education> educations;
            private ArrayList<Experience> experiences;

            public ResumeBuilder(Information information){
                this.information = information;
            }
            public ResumeBuilder experience(ArrayList<Experience> experiences){
                this.experiences = experiences;
                Collections.sort(experiences);
                return this;
            }
            public ResumeBuilder education(ArrayList<Education> educations){
                this.educations = educations;
                Collections.sort(educations);
                return this;
            }
            public Resume build() throws  ResumeIncompleteException{
                if(information == null || educations.size() == 0)
                    throw new ResumeIncompleteException("There are no valid information or studies");
                return new Resume(this);
            }
        }
    }
    public void add(Education education){
        resume.educations.add(education);
        Collections.sort(resume.educations);
    }
    public void add(Experience experience){
        resume.experiences.add(experience);
        Collections.sort(resume.experiences);
    }
    public void set(Information information, Resume resume){
        this.resume = resume;
        this.resume.information = information;
    }
    public void add(Consumer consumer){
        friends.add(consumer);
        consumer.friends.add(this);
    }
//    TODO
    public int getDegreeInFriendship(Consumer consumer){
        Queue<Consumer> queue = new LinkedList<Consumer>();
        Vector<Consumer> visited = new Vector<>();
        HashMap<Consumer,Integer> level = new HashMap<>();
        Consumer var = this;
        visited.add(var);
        queue.add(var);
        level.put(var,0);

        while(queue.size() != 0) {
            var = queue.poll();
            for (Consumer aux : var.friends) {
                if (!visited.contains(aux)) {
                    visited.add(aux);
                    queue.add(aux);
                    level.put(aux,level.get(var)+1);
                }
                if(aux.equals(consumer))
                    return level.get(aux);
            }
        }
        return 0;
    }
    public void remove(Consumer consumer){
        friends.remove(consumer);
    }

    public Integer getGraduationYear(){
        for(Education aux : resume.educations){
            if(aux.educationLevel.compareTo("college") == 0)
                if(aux.endDate != null)
                    return aux.endDate.getYear();
                else
                    return 0;
        }
        return null;
    }

    public Double meanGPA() {
        Double gpa = 0.0;
        int count = 0;
        for (Education aux : resume.educations) {
                gpa += aux.gpa;
                count++;
        }
        return gpa/count;
    }

    public int getExperience(){
        long months_of_work = 0;
        int year_of_experience = 0;
        for(Experience aux : resume.experiences){
            LocalDate date = aux.end_date;
            if(date == null)
                date = LocalDate.now();
            months_of_work = ChronoUnit.MONTHS.between(aux.start_date,date);
            year_of_experience += months_of_work / 12;
            long rest = months_of_work % 12;
            if(rest >= 3)
                year_of_experience++;
        }
        return year_of_experience;
    }

}
