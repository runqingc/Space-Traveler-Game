package edu.uchicago.gerber._04interfaces.E9_11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {

    private static List<Person> people = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        boolean continueRunning = true;
        while (continueRunning) {
            System.out.println("\nSelect an option:");
            System.out.println("1: Create a Person");
            System.out.println("2: Create an Employee");
            System.out.println("3: Create a Student");
            System.out.println("4: Display all entries");
            System.out.println("5: Modify an entry");
            System.out.println("6: Exit");
            int choice = in.nextInt();
            in.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createPerson(in);
                    break;
                case 2:
                    createEmployee(in);
                    break;
                case 3:
                    createStudent(in);
                    break;
                case 4:
                    displayAll();
                    break;
                case 5:
                    modifyEntry(in);
                    break;
                case 6:
                    continueRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void createPerson(Scanner in) {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        System.out.print("Enter year of birth: ");
        int yearOfBirth = in.nextInt();
        Person person = new Person(name, yearOfBirth);
        people.add(person);
        System.out.println("Person created.");
    }

    private static void createEmployee(Scanner in) {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        System.out.print("Enter year of birth: ");
        int yearOfBirth = in.nextInt();
        System.out.print("Enter salary: ");
        double salary = in.nextDouble();
        Employee employee = new Employee();
        employee.setName(name);
        employee.setYearOfBirth(yearOfBirth);
        employee.setSalary(salary);
        people.add(employee);
        System.out.println("Employee created.");
    }

    private static void createStudent(Scanner in) {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        System.out.print("Enter year of birth: ");
        int yearOfBirth = in.nextInt();
        in.nextLine();  // Consume newline
        System.out.print("Enter major: ");
        String major = in.nextLine();
        Student student = new Student();
        student.setName(name);
        student.setYearOfBirth(yearOfBirth);
        student.setMajor(major);
        people.add(student);
        System.out.println("Student created.");
    }

    private static void displayAll() {
        for (Person person : people) {
            System.out.println(person);
        }
    }

    private static void modifyEntry(Scanner in) {
        System.out.println("Select an entry to modify by index:");
        for (int i = 0; i < people.size(); i++) {
            System.out.println((i + 1) + ". " + people.get(i).toString());
        }

        int index = in.nextInt() - 1; // Convert to 0-based index
        if (index < 0 || index >= people.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Person selectedPerson = people.get(index);

        System.out.println("\nSelected: " + selectedPerson.toString());
        in.nextLine();
        if (selectedPerson instanceof Employee) {
            System.out.print("Enter new salary: ");
            double newSalary = in.nextDouble();
            ((Employee) selectedPerson).setSalary(newSalary);
        } else if (selectedPerson instanceof Student) {
            System.out.print("Enter new major: ");

            String newMajor = in.nextLine();
            ((Student) selectedPerson).setMajor(newMajor);
        } else {
            System.out.print("Enter new name: ");
            String newName = in.nextLine();
            selectedPerson.setName(newName);
        }

        System.out.println("Modified: " + selectedPerson.toString());
    }
}