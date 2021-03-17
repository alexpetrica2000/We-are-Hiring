import java.time.LocalDate;

public class Experience implements Comparable{
    LocalDate start_date;
    LocalDate end_date;
    String position;
    String company;

    public Experience(String start_date_of, String end_date_of,
                      String position, String company) throws InvalidDatesException {
        start_date = LocalDate.parse(start_date_of);
        if(end_date_of != null)
            end_date = LocalDate.parse(end_date_of);
        this.position = position;
        this.company = company;
        if(end_date_of != null)
            if(start_date.compareTo(end_date) >= 0)
                throw new InvalidDatesException("Error data input (startDate > endDate)");
    }

    @Override
    public int compareTo(Object o) {
        Experience aux = (Experience) o;
        if(aux.end_date == null)
            if(end_date == null)
                return company.compareTo(aux.company);
            else
                return 0;
        if(end_date == null )
            if(aux.end_date != null)
                return -1;
        if(end_date.isEqual(aux.end_date))
            return company.compareTo(aux.company);
        return aux.end_date.compareTo(end_date);
    }
    public String toString(){
       String s = "";
       s = s + start_date+ " " +end_date+ " "+position+ " "+company;

       return s;
    }
}
