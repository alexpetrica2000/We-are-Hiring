public class Finance extends Department{
    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0.0;
        for(Employee aux : employees)
            if(aux.getExperience() < 1)
                totalSalary += 1.1 * aux.salary;
            else
                totalSalary += 1.16 * aux.salary;
        return totalSalary;
    }
}
