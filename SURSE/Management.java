public class Management extends Department{

    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0.0;
        for(Employee aux : employees)
            totalSalary += 1.16*aux.salary;
        return totalSalary;
    }
}
