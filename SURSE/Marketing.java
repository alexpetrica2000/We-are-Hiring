public class Marketing extends Department{
    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0.0;
        for (Employee aux : employees) {
            if (aux.salary > 5000)
                totalSalary += 1.1* aux.salary;
            else if (aux.salary < 3000)
                totalSalary += aux.salary;
            else totalSalary += 1.16 * aux.salary;
        }
        return totalSalary;
    }
}
