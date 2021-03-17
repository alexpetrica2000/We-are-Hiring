public class IT extends Department {
    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0.0;
        for(Employee aux : employees)
            totalSalary += aux.salary;
        return totalSalary;
    }
}
