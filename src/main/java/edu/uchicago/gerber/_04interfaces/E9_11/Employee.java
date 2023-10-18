package edu.uchicago.gerber._04interfaces.E9_11;

public class Employee extends Person{

    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString()+ "{" +
                "salary=" + salary +
                '}';
    }
}
