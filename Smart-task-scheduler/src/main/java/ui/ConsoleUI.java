package ui;

import service.SchedulerService;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUI {

    private SchedulerService scheduler = new SchedulerService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {

        while (true) {

            System.out.println("\n1. Add Task");
            System.out.println("2. Execute Next Task");
            System.out.println("3. Show All Tasks");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter priority: ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter deadline (yyyy-MM-dd): ");
                    LocalDate deadline = LocalDate.parse(scanner.nextLine());

                    scheduler.addTask(title, priority, deadline);
                    break;

                case 2:
                    scheduler.executeTask();
                    break;

                case 3:
                    scheduler.showTasks();
                    break;

                case 4:
                    scheduler.showTasks();
                    System.out.print("Enter task number to delete: ");
                    int index = scanner.nextInt();
                    scheduler.deleteTask(index);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}