import java.time.LocalDate;

public class Education implements Comparable{
    LocalDate startDate;
    LocalDate endDate;
    String institutionName;
    String educationLevel;
    Double gpa;

    public Education(String startDate, String endDate
                     , String institutionName, String educationLevel, Double gpa) throws InvalidDatesException {
        this.startDate = LocalDate.parse(startDate);
        if(endDate != null)
            this.endDate = LocalDate.parse(endDate);
        this.institutionName = institutionName;
        this.educationLevel = educationLevel;

        this.gpa = gpa;
        if(endDate != null)
            if(startDate.compareTo(endDate) > 0)
                throw new InvalidDatesException("Error data input (startDate > endDate)");
    }
    @Override
    public int compareTo(Object o) {
        Education aux = (Education) o;
        if(aux.endDate == null)
            if(endDate == null)
                return startDate.compareTo(aux.startDate);
            else
                return 0;
        if(endDate == null )
            if(aux.endDate != null)
                return -1;
        if(endDate.isEqual(aux.endDate))
            return (int)(aux.gpa - gpa);
        return aux.endDate.compareTo(endDate);
    }
    public String toString(){
        String s = "";
        s = s + startDate+ " " +" "+endDate+ " "+ institutionName+ " "+ educationLevel+" "+ gpa;
        return s;
    }
}
