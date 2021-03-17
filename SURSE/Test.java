
import java.io.*;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test {
    public static void main(String[] args) throws Exception {
        Vector<String> companies_name = new Vector<>();
        Scanner scanner = new Scanner(new File("companies.txt"));
        while(scanner.hasNextLine()){
            companies_name.add(scanner.nextLine());
        }

        Company Amazon = new Company(companies_name.get(0),null);
        Department IT = DepartmentFactory.createDepartment("IT");
        Department Finance = DepartmentFactory.createDepartment("Finance");
        Department Marketing = DepartmentFactory.createDepartment("Marketing");
        Department Management = DepartmentFactory.createDepartment("Management");
        Amazon.add(IT);
        Amazon.add(Finance);
        Amazon.add(Marketing);
        Amazon.add(Management);

        Company Google = new Company(companies_name.get(1),null);
        IT = DepartmentFactory.createDepartment("IT");
        Finance = DepartmentFactory.createDepartment("Finance");
        Marketing = DepartmentFactory.createDepartment("Marketing");
        Management = DepartmentFactory.createDepartment("Management");

        Google.add(IT);
        Google.add(Finance);
        Google.add(Management);
        Google.add(Marketing);
        JSONParser parser = new JSONParser();
        Object obj = new JSONParser().parse(new FileReader("consumers.json"));
        JSONObject jo = (JSONObject) obj;
        JSONArray employees = (JSONArray) jo.get("employees");
        Application.getInstance().add(Amazon);
        Application.getInstance().add(Google);
        Iterator<JSONObject> employee = employees.iterator();

        while(employee.hasNext()){

            JSONObject jo2 = employee.next();
            String fullname  = (String)jo2.get("name");
            String []splited = fullname.split(" ");
            String email = (String)jo2.get("email");
            String phone = (String)jo2.get("phone");
            String date_of_birth = getDate((String)jo2.get("date_of_birth"));
            String sex = (String)jo2.get("genre");
            double salary = (double)((long)jo2.get("salary"));
            JSONArray languages = (JSONArray) jo2.get("languages");
            JSONArray languages_level = (JSONArray) jo2.get("languages_level");
            Information information = new Information(splited[1],splited[0],email,phone,date_of_birth,sex);
            Iterator<String> language = languages.iterator();
            Iterator<String> language_level = languages_level.iterator();
            Employee emp = new Employee();
            emp.setSalary(salary);

            while(language.hasNext() && language_level.hasNext()){
                String lng = language.next();
                String lng_level = language_level.next();
                information.addLanguage(lng,lng_level);
            }
            JSONArray educations = (JSONArray) jo2.get("education");
            Iterator<JSONObject> obj_educ = educations.iterator();
            JSONArray experiences = (JSONArray) jo2.get("experience");
            Iterator<JSONObject> obj_exp = experiences.iterator();

            ArrayList<Education> aux_educ = new ArrayList<>();
            ArrayList<Experience> aux_exp = new ArrayList<>();
            while(obj_educ.hasNext()){
                JSONObject education = obj_educ.next();
                String level = (String)education.get("level");
                String name = (String)education.get("name");
                String start_date = getDate((String)education.get("start_date"));
                String end_data = getDate((String)education.get("end_date"));
                Double grade = ((Number)education.get("grade")).doubleValue();
                Education educ = new Education(start_date,end_data,name,level,grade);
                aux_educ.add(educ);
            }
            while(obj_exp.hasNext()){
                JSONObject experience = obj_exp.next();
                String company = (String)experience.get("company");
                String position = (String)experience.get("position");
                String department = (String)experience.get("department");
                String start_date = getDate((String)experience.get("start_date"));
                String end_date = getDate((String)experience.get("end_date"));
                Experience exp = new Experience(start_date,end_date,position,company);
                aux_exp.add(exp);

                if(end_date == null) {
                    if (company.equals("Google"))
                        for (Department dep : Google.departments)
                            if (dep.getClass().getName().equals(department)) {
                                dep.add(emp);
                                emp.setCompany(company);
                            }
                    if (company.equals("Amazon"))
                        for (Department dep : Amazon.departments)
                            if (dep.getClass().getName().equals(department)) {
                                dep.add(emp);
                                emp.setCompany(company);
                            }
                }
            }
            Consumer.Resume  resume = new Consumer.Resume.ResumeBuilder(information)
                    .education(aux_educ).experience(aux_exp).build();
            emp.set(information, resume);

        }

        JSONArray recruiters = (JSONArray) jo.get("recruiters");

        Iterator<JSONObject> recruiter = recruiters.iterator();

        while(recruiter.hasNext()){
            Recruiter rec = new Recruiter();
            JSONObject jo2 = recruiter.next();
            String fullname  = (String)jo2.get("name");
            String email = (String)jo2.get("email");
            String []splited = fullname.split(" ");
            String phone = (String)jo2.get("phone");
            String date_of_birth = getDate((String)jo2.get("date_of_birth"));
            String sex = (String)jo2.get("genre");
            double salary = (double)((long)jo2.get("salary"));
            JSONArray languages = (JSONArray) jo2.get("languages");
            JSONArray languages_level = (JSONArray) jo2.get("languages_level");
            Information information = new Information(splited[1],splited[0],email,phone,date_of_birth,sex);

            rec.setSalary(salary);
            Iterator<String> language = languages.iterator();
            Iterator<String> language_level = languages_level.iterator();
            while(language.hasNext() && language_level.hasNext()){
                String lng = language.next();
                String lng_level = language_level.next();
                information.addLanguage(lng,lng_level);
            }

            JSONArray educations = (JSONArray) jo2.get("education");
            Iterator<JSONObject> obj_educ = educations.iterator();
            JSONArray experiences = (JSONArray) jo2.get("experience");
            Iterator<JSONObject> obj_exp = experiences.iterator();

            ArrayList<Education> aux_educ = new ArrayList<>();
            ArrayList<Experience> aux_exp = new ArrayList<>();

            while(obj_educ.hasNext()){
                JSONObject education = obj_educ.next();
                String level = (String)education.get("level");
                String name = (String)education.get("name");
                String start_date = getDate((String)education.get("start_date"));
                String end_data = getDate((String)education.get("end_date"));
                Double grade = ((Number)education.get("grade")).doubleValue();
                Education educ = new Education(start_date,end_data,name,level,grade);
                aux_educ.add(educ);
            }
            while(obj_exp.hasNext()){
                JSONObject experience = obj_exp.next();
                String company = (String)experience.get("company");
                String position = (String)experience.get("position");

                String start_date = getDate((String)experience.get("start_date"));
                String end_date = getDate((String)experience.get("end_date"));
                Experience exp = new Experience(start_date,end_date,position,company);
                aux_exp.add(exp);

                if(end_date == null) {
                    if (company.equals("Google")){
                        rec.setCompany(company);
                        Google.add(rec);
                        for(Department dep : Google.departments){
                            if(dep.getClass().getName().equals("IT"))
                                dep.add(rec);
                        }
                    }
                    if (company.equals("Amazon")){
                        rec.setCompany(company);
                        Amazon.add(rec);
                        for(Department dep : Amazon.departments){
                            if(dep.getClass().getName().equals("IT"))
                                dep.add(rec);
                        }
                    }
                }
            }
            Consumer.Resume  resume = new Consumer.Resume.ResumeBuilder(information).
                    education(aux_educ).experience(aux_exp).build();
            rec.set(information, resume);

        }

        JSONArray users = (JSONArray) jo.get("users");

        Iterator<JSONObject> user = users.iterator();

        while(user.hasNext()){
            JSONObject jo2 = user.next();
            User usr = new User();
            String fullname  = (String)jo2.get("name");
            String []splited = fullname.split(" ");
            String email = (String)jo2.get("email");
            String phone = (String)jo2.get("phone");
            String date_of_birth = getDate((String)jo2.get("date_of_birth"));
            String sex = (String)jo2.get("genre");
            Information information = new Information(splited[1],splited[0],email,phone,date_of_birth,sex);

            ArrayList<String> interested_companies = new ArrayList<>();
            JSONArray languages = (JSONArray) jo2.get("languages");
            JSONArray languages_level = (JSONArray) jo2.get("languages_level");
            JSONArray companies = (JSONArray) jo2.get("interested_companies");
            Iterator<String> aux_company = companies.iterator();
            while(aux_company.hasNext()){
                interested_companies.add(aux_company.next());
            }
            usr.jobList.addAll(interested_companies);
            Iterator<String> language = languages.iterator();
            Iterator<String> language_level = languages_level.iterator();
            while(language.hasNext() && language_level.hasNext()){
                String lng = language.next();
                String lng_level = language_level.next();
                information.addLanguage(lng,lng_level);
            }
            JSONArray educations = (JSONArray) jo2.get("education");
            Iterator<JSONObject> obj_educ = educations.iterator();
            JSONArray experiences = (JSONArray) jo2.get("experience");
            Iterator<JSONObject> obj_exp = experiences.iterator();

            ArrayList<Education> aux_educ = new ArrayList<>();
            ArrayList<Experience> aux_exp = new ArrayList<>();
            while(obj_educ.hasNext()){
                JSONObject education = obj_educ.next();
                String level = (String)education.get("level");
                String name = (String)education.get("name");
                String start_date = getDate((String)education.get("start_date"));
                String end_data = getDate((String)education.get("end_date"));
                Double grade = ((Number)education.get("grade")).doubleValue();
                Education educ = new Education(start_date,end_data,name,level,grade);
                aux_educ.add(educ);

            }
            while(obj_exp.hasNext()){
                JSONObject experience = obj_exp.next();
                String company = (String)experience.get("company");
                String position = (String)experience.get("position");

                String start_date = getDate((String)experience.get("start_date"));
                String end_date = getDate((String)experience.get("end_date"));
                Experience exp = new Experience(start_date,end_date,position,company);
                aux_exp.add(exp);
            }
            Application.getInstance().add(usr);
            Consumer.Resume  resume = new Consumer.Resume.ResumeBuilder(information).
                    education(aux_educ).experience(aux_exp).build();
            usr.set(information, resume);

        }
        JSONArray managers = (JSONArray) jo.get("managers");

        Iterator<JSONObject> manager = managers.iterator();

        while(manager.hasNext()){
            JSONObject jo2 = manager.next();
            Manager mng = new Manager();
            String fullname  = (String)jo2.get("name");
            String []splited = fullname.split(" ");
            String email = (String)jo2.get("email");
            String phone = (String)jo2.get("phone");
            String date_of_birth = getDate((String)jo2.get("date_of_birth"));
            String sex = (String)jo2.get("genre");
            double salary = (double)((long)jo2.get("salary"));
            Information information = new Information(splited[1],splited[0],email,phone,date_of_birth,sex);
            mng.setSalary(salary);
            JSONArray languages = (JSONArray) jo2.get("languages");
            JSONArray languages_level = (JSONArray) jo2.get("languages_level");


            Iterator<String> language = languages.iterator();
            Iterator<String> language_level = languages_level.iterator();
            while(language.hasNext() && language_level.hasNext()){
                String lng = language.next();
                String lng_level = language_level.next();
                information.addLanguage(lng, lng_level);
            }

            JSONArray educations = (JSONArray) jo2.get("education");
            Iterator<JSONObject> obj_educ = educations.iterator();
            JSONArray experiences = (JSONArray) jo2.get("experience");
            Iterator<JSONObject> obj_exp = experiences.iterator();

            ArrayList<Education> aux_educ = new ArrayList<>();
            ArrayList<Experience> aux_exp = new ArrayList<>();
            while(obj_educ.hasNext()){
                JSONObject education = obj_educ.next();
                String level = (String)education.get("level");
                String name = (String)education.get("name");
                String start_date = getDate((String)education.get("start_date"));
                String end_data = getDate((String)education.get("end_date"));
                Double grade = ((Number)education.get("grade")).doubleValue();
                Education educ = new Education(start_date,end_data,name,level,grade);
                aux_educ.add(educ);
            }
            while(obj_exp.hasNext()){
                JSONObject experience = obj_exp.next();
                String company = (String)experience.get("company");
                String position = (String)experience.get("position");

                String start_date = getDate((String)experience.get("start_date"));
                String end_date = getDate((String)experience.get("end_date"));
                Experience exp = new Experience(start_date,end_date,position,company);
                aux_exp.add(exp);
                if(end_date == null) {
                    if (company.equals("Google")){
                        mng.setCompany(company);
                        Google.manager = mng;
                    }
                    if (company.equals("Amazon")){
                        mng.setCompany(company);
                        Amazon.manager = mng;
                    }
                }
            }
            Consumer.Resume  resume = new Consumer.Resume.ResumeBuilder(information)
                    .education(aux_educ).experience(aux_exp).build();
            mng.set(information, resume);



        }
        Object obj4 = new JSONParser().parse(new FileReader("jobs.json"));
        JSONObject jo4 = (JSONObject) obj4;
        JSONArray jobs = (JSONArray)jo4.get("jobs");
        Iterator<JSONObject> obj_jobs = jobs.iterator();
        while(obj_jobs.hasNext()){
            JSONObject jo5 = obj_jobs.next();
            String company_name = (String)jo5.get("company_name");
            String job_name = (String)jo5.get("job_name");
            String department = (String)jo5.get("department");
            int noPositions = (int)(long)jo5.get("noPositions");
            double salary = ((Number)jo5.get("salary")).doubleValue();
            double inferior_year_constraint;
            if(jo5.get("inferior_year_constraint") != null)
                inferior_year_constraint = ((Number)jo5.get("inferior_year_constraint")).doubleValue();
            else
                inferior_year_constraint = 0.0;
            double superior_year_constraint;
            if(jo5.get("superior_year_constraint") != null)
                 superior_year_constraint = ((Number)jo5.get("superior_year_constraint")).doubleValue();
            else
                superior_year_constraint = Double.MAX_VALUE;
            Constraint year = new Constraint(inferior_year_constraint, superior_year_constraint);
            double inferior_experience_constraint;
            if(jo5.get("inferior_experience_constraint") != null)
                inferior_experience_constraint = ((Number)jo5.get("inferior_experience_constraint")).doubleValue();
            else
                inferior_experience_constraint = 0;
            double superior_experience_constraint;
            if(jo5.get("superior_experience_constraint") != null)
                 superior_experience_constraint = ((Number)jo5.get("superior_experience_constraint")).doubleValue();
            else
                superior_experience_constraint = Double.MAX_VALUE;
            Constraint experience = new Constraint(inferior_experience_constraint, superior_experience_constraint);
            double inferior_average;
            if(jo5.get("inferior_average") != null)
                inferior_average = ((Number)jo5.get("inferior_average")).doubleValue();
            else
                inferior_average = 0;
            double superior_average;
            if(jo5.get("superior_average") != null)
                superior_average = ((Number)jo5.get("superior_average")).doubleValue();
            else
                superior_average = 10.0;
            Constraint average = new Constraint(inferior_average, superior_average);
            Job newjob = new Job(job_name, company_name,true,year,experience,average,noPositions,salary);
            if(newjob.company_name.equals("Google")){
                for(Department dep : Google.departments)
                    if(dep.getClass().getName().equals(department))
                        dep.add(newjob);
            }
            if(newjob.company_name.equals("Amazon")){
                for(Department dep : Amazon.departments)
                    if(dep.getClass().getName().equals(department))
                        dep.add(newjob);
            }
        }

        Object obj5 = new JSONParser().parse(new FileReader("friends.json"));
        JSONObject jo5 = (JSONObject) obj5;
        JSONArray consumers = (JSONArray)jo5.get("consumers");
        Iterator<JSONObject> obj_consumers = consumers.iterator();

        while(obj_consumers.hasNext()){
            JSONObject consum = obj_consumers.next();

            String firstname = (String)consum.get("firstname");

            String lastname = (String)consum.get("lastname");
            String type = (String)consum.get("type");

            Consumer aux = null;
            if(type.equals("U")){
                aux = Application.getInstance().getUser(firstname, lastname);
            }
            if(type.equals("E")){
                for(Company cmp : Application.getInstance().getCompanies())
                    for(Department dep : cmp.departments)
                        for(Employee emp : dep.employees)
                            if(emp.resume.getInformation().getFirstname().equals(firstname))
                                if(emp.resume.getInformation().getLastname().equals(lastname))
                                    aux = emp;

            }
            if(type.equals("R")){
                for(Company cmp : Application.getInstance().getCompanies())
                    for(Recruiter rec : cmp.recruiters){
                        if(rec.resume.getInformation().getLastname().equals(lastname))
                            if(rec.resume.getInformation().getFirstname().equals(firstname))
                                aux =  rec;
                    }
            }

            JSONArray friends = (JSONArray) consum.get("friends");
            Iterator<JSONObject> friend = friends.iterator();
            while(friend.hasNext()){
                JSONObject frd = friend.next();
                String frd_firstname = (String)frd.get("firstname");
                String frd_lastname = (String)frd.get("lastname");
                String frd_type = (String)frd.get("type");

                Consumer aux2 = null;
                if(frd_type.equals("U")){
                    aux2 = Application.getInstance().getUser(frd_firstname, frd_lastname);
                }
                if(frd_type.equals("E")){
                    for(Company cmp : Application.getInstance().getCompanies())
                        for(Department dep : cmp.departments)
                            for(Employee emp : dep.employees)
                                if(emp.resume.getInformation().getFirstname().equals(frd_firstname))
                                    if(emp.resume.getInformation().getLastname().equals(frd_lastname))
                                        aux2 = emp;

                }
                if(frd_type.equals("R")){
                    for(Company cmp : Application.getInstance().getCompanies())
                        for(Recruiter rec : cmp.recruiters){
                            if(rec.resume.getInformation().getLastname().equals(frd_lastname))
                                if(rec.resume.getInformation().getFirstname().equals(frd_firstname))
                                    aux2 =  rec;
                        }
                }
                aux.add(aux2);
            }

        }

       for(User usr : Application.getInstance().getUsers()){
           ArrayList<Job> preferredJob = Application.getInstance().getJobs(usr.jobList);
           for(Job needed_job : preferredJob){
               needed_job.apply(usr);
           }
       }
       // TODO PARTE TEST

//       System.out.println(Google.departments.get(0).employees);
//       System.out.println(Google.manager.requests);
//       System.out.println(Amazon.manager.requests);
//        System.out.println(Google.departments.get(0).getEmployees().get(0).friends);
//        System.out.println(Google.recruiters.get(1).getDegreeInFriendship(Application.getInstance().getUser("Tamara","Haci")));
//
//        Google.manager.process(Google.departments.get(0).jobs.get(0));
//       Google.manager.process(Google.departments.get(0).jobs.get(0));
//        Amazon.manager.process(Amazon.departments.get(0).jobs.get(0));
//        Amazon.manager.process(Amazon.departments.get(0).jobs.get(0));

//       System.out.println(Google.departments.get(0).employees);
//       System.out.println(Amazon.departments.get(0).employees);
//       System.out.println(Application.getInstance().getUsers());

        AuthentificationPage page = new AuthentificationPage();

    }
    public static String getDate(String date){
        if(date == null)
            return null;
        String buffer = "";
        int j = date.length();
        int i;

        for(i = date.length()-1; i >= 0; i--){
            char c = date.charAt(i);
            if(c == '.'){
                buffer = buffer.concat(date.substring(i+1,j));
                buffer = buffer.concat("-");
                j = i;
            }

        }
        buffer = buffer.concat(date.substring(i+1,j));
        return buffer;
    }

}

