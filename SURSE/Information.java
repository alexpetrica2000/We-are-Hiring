import java.util.HashMap;

public class Information {
    private String lastname, firstname, email;
    private String phone;
    private String birthday, sex;
    private HashMap<String,String> languages = new HashMap<>();


    public Information(String lastname, String firstname, String email, String phone, String birthday, String sex) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.sex = sex;
    }
    public String getLastname(){
        return lastname;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public String getBirthday(){
        return birthday;
    }
    public HashMap<String, String> getLanguages() {
        return languages;
    }
    public String getKnownLanguages(){
        return languages.toString();
    }
    public void addLanguage(String language, String level){
        languages.put(language, level);
    }
    public String getSex(){
        return sex;
    }
    public String getLanguageLevel(String language){
        return languages.get(language);
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public void setLanguageLevel(String language, String new_language_level){
       languages.replace(language, new_language_level);
    }



}
