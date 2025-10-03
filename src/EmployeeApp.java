import java.util.Map;
import java.util.Scanner;

public class EmployeeApp {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        while (true) {
            System.out.println("---- Employee Database JDBC App ----");
            System.out.println("1. CREATE/ADD Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. View Employee By ID");
            System.out.println("4. UPDATE Employee");
            System.out.println("5. DELETE Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice : ");
            int choice;

            try {
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        MenuOperations.addEmployee();
                        break;

                    case 2:
                        MenuOperations.viewAllEmployees();
                        break;
                    case 3:
                        MenuOperations.viewEmployeeById();
                        break;
                    case 4:
                        MenuOperations.updateEmployee();
                        break;
                    case 5:
                        MenuOperations.deleteEmployee();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Error: " + e.getMessage());
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("\nPress Enter 2 times to continue...");
            try {
                System.in.read();
                input.nextLine();
            } catch (Exception ignored) {}

        }

    }
}
