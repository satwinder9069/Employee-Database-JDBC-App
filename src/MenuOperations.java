import java.util.List;
import java.util.Scanner;

public class MenuOperations {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static Scanner scan = new Scanner(System.in);

    //Null safe input methods TODO: Add input validation (null checks, email format, salary > 0, etc.)
    public static String getValidName() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 5;

        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.println("Name (minimum 3 characters): ");
                String input = scan.nextLine();

                if(input == null) {
                    System.out.println("Can't be null, try again");
                    attempts++;
                    continue;
                }
                String name = InputValidator.sanitizeInput(input);
                if(InputValidator.isValidName(name)){
                    return name;
                }

                attempts++;
                System.out.println("Attempts left : " + (MAX_ATTEMPTS - attempts));

            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
                attempts++;
            }
        }
        throw new RuntimeException("Max attempts reached for name input!");
    }

    public static String getValidEmail() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 5;

        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print("Email (In proper Format): ");
                String input = scan.nextLine();

                if (input == null) {
                    System.out.println("Input is null, Please try again!");
                    attempts++;
                    continue;
                }

                String email = InputValidator.sanitizeInput(input);

                if (InputValidator.isValidEmail(email)) {
                    return email;
                }

                attempts++;
                System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));

            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
                attempts++;
            }
        }

        throw new RuntimeException("Max attempts reached for email input!");
    }


    // NOTE: This file currently works but does not have null checks or input validation yet.
    // 1.
    public static void addEmployee() {
        System.out.println("==== Add new Employees ====");
        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Department: ");
        String department = scan.nextLine();
        System.out.print("Salary: ");
        double salary = scan.nextDouble();
        scan.nextLine();

        Employee employee = new Employee(name, email, department, salary);

        if (employeeDAO.addEmployee(employee)) {
            System.out.println("Employee Added ");
        } else {
            System.out.println("Error");
        }
    }
    // 2.
    public static void viewAllEmployees() {
        System.out.println("==== View All Employees ====");
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employee found!");
        } else {
            employees.forEach(System.out::println);
        }
    }

    // 3.
    public static void viewEmployeeById() {
        System.out.println("==== View By Employee ID ====");
        System.out.print("Enter Employee ID: ");
        int id = scan.nextInt();

        Employee employee = employeeDAO.getEmployeeById(id);
        if(employee != null) {
            System.out.println("Employee found: " + employee);
        } else {
            System.out.println("No Employee found with this id!");
        }
    }

    // 4.
    public static void updateEmployee() {
        System.out.println("==== Update Employee ====");
        System.out.print("Enter Employee ID: ");
        int id = scan.nextInt();
        scan.nextLine();

        Employee employee = employeeDAO.getEmployeeById(id);
        if(employee == null) {
            System.out.println("No Employee found with this id: ");
            return;
        }
        System.out.println("Current Details: " + employee);
        System.out.println("Enter new details: ");

        System.out.print("Name: ");
        employee.setName(scan.nextLine());
        System.out.print("Email: ");
        employee.setEmail(scan.nextLine());
        System.out.print("Department: ");
        employee.setDepartment(scan.nextLine());
        System.out.print("Salary: ");
        employee.setSalary(scan.nextDouble());

        if (employeeDAO.updateEmployee(employee)) {
            System.out.println("Employee Updated Successfully!!");
        } else {
            System.out.println("Error in Updating Employee!! ");
        }

    }

    public static void deleteEmployee() {
        System.out.println("==== Delete Employee ====");
        System.out.print("Enter Employee ID to delete: ");
        int id = scan.nextInt();

        Employee employee = employeeDAO.getEmployeeById(id);
        if (employee == null) {
            System.out.println("No Employee found with this ID!");
            return;
        }

        System.out.println("Employee with id: " + employee.getId() + " will be deleted. ");
        System.out.print("Confirm (y / n): ");
        String confirm = scan.next();
        if (confirm.equalsIgnoreCase("y")) {
            if(employeeDAO.deleteEmployeeById(id)) {
                System.out.println("Employee deleted successfully!!");
            } else {
                System.out.println("Error in deleting Employee");
            }

        } else {
            System.out.println("Cancelled! ");
        }
    }
}