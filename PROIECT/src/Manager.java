import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Manager extends Employee {

    ArrayList<Request<Job, Consumer>> requests = new ArrayList<>();

    public void process(Job job) {
        int new_noPositions = job.noPositions;
        ArrayList<Request<Job, Consumer>> goodRequests = new ArrayList<>();

        for (Request<Job, Consumer> request : requests)
            if (request.getKey().equals(job))
                goodRequests.add(request);
        Collections.sort(goodRequests, new Comparator<Request<Job, Consumer>>() {
            @Override
            public int compare(Request<Job, Consumer> o1, Request<Job, Consumer> o2) {
                return (int) (o2.getScore() * 100 - o1.getScore() * 100);
            }
        });
        for (int i = 0; i < job.noPositions; i++) {
            if(goodRequests.size() == 0)
                break;
            User user = (User) goodRequests.get(0).getValue1();
            requests.remove(goodRequests.get(0));
            goodRequests.remove(goodRequests.get(0));

            if (Application.getInstance().getUsers().contains(user)) {
                Application.getInstance().getUsers().remove((user));
                Employee employee = user.convert();
                for (Department department : Application.getInstance().getCompany(company_name).departments)
                    for (Job neededJob : department.getJobs())
                        if (neededJob.equals(job)) {
                            department.add(employee);
                            employee.company_name = neededJob.company_name;
                            employee.salary = neededJob.salary;
                            new_noPositions--;
                            try {
                                Experience exp = new Experience(LocalDate.now().toString(), null, job.name,
                                        company_name);
                                employee.add(exp);
                            }
                            catch(InvalidDatesException e){
                                e.printStackTrace();
                            }

                            Iterator<Company> it = Application.getInstance().getCompanies().iterator();
                            while(it.hasNext()){
                                Company cmp = it.next();
                                if(!cmp.equals(Application.getInstance().getCompany(company_name))){
                                    Iterator<Request<Job,Consumer>> it2 = cmp.manager.requests.iterator();
                                    while(it2.hasNext()){
                                        Request req = it2.next();
                                        if(req.getValue1().equals(user))
                                            it2.remove();
                                    }
                                }
                                Iterator<Observer> obs = cmp.observers.iterator();
                                while(obs.hasNext()){
                                    Observer observer = obs.next();
                                    if(observer.equals(user))
                                        obs.remove();
                                }
                            }
                        }
            }
            else
                job.noPositions++;
        }
            job.noPositions = new_noPositions;
            if(new_noPositions == 0) {
                requests.removeAll(goodRequests);
                Iterator<Department> dep = Application.getInstance().getCompany(company_name).departments.iterator();
                while (dep.hasNext()) {
                    Iterator<Job> jobs = dep.next().getJobs().iterator();
                    while (jobs.hasNext()) {
                        Job aux = jobs.next();
                        if (aux.equals(job)) {
                            aux.flag = false;
                            jobs.remove();
                            Company cmp = Application.getInstance().getCompany(company_name);
                            cmp.notifyAllObservers(new Notification("Job " + job.name +
                                    " of company "+company_name+" has been closed!"));
                        }
                    }
                }
            }

        for(Request<Job, Consumer> aux : goodRequests) {
            for(Observer obs : Application.getInstance().getUsers())
                if(aux.getValue1().equals(obs))
                    obs.update(new Notification(((User)obs).resume.getInformation().getLastname()+" You were rejected for job  "
                            + job.name+" of company " + company_name+" because there were no positions left"));
        }
    }
    public String toString(){
        String s = " ";
        s = s +  company_name + " - " + resume.getInformation().getLastname() + " "
                + resume.getInformation().getFirstname();

        return s;
    }
}
