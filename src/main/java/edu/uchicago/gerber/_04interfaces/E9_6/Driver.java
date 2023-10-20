package edu.uchicago.gerber._04interfaces.E9_6;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create a new appointment");
            System.out.println("2. Check appointments on a certain day");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createAppointment(scanner);
                    break;
                case 2:
                    checkAppointments(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAppointment(Scanner scanner) {
        System.out.println("Choose appointment type:");
        System.out.println("1. Onetime");
        System.out.println("2. Daily");
        System.out.println("3. Monthly");
        int type = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.println("Enter description for the appointment:");
        String description = scanner.nextLine();

        switch (type) {
            case 1:
                System.out.println("Enter date (yyyy mm dd):");
                int year = scanner.nextInt();
                int month = scanner.nextInt();
                int day = scanner.nextInt();
                appointments.add(new Onetime(description, year, month, day));
                System.out.println("Onetime appointment added.");
                break;
            case 2:
                appointments.add(new Daily(description));
                System.out.println("Daily appointment added.");
                break;
            case 3:
                System.out.println("Enter day of the month:");
                day = scanner.nextInt();
                appointments.add(new Monthly(description, day));
                System.out.println("Monthly appointment added.");
                break;
            default:
                System.out.println("Invalid type. Appointment not added.");
        }
    }

    private static void checkAppointments(Scanner scanner) {

    }
}
