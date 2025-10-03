import java.util.List;
import java.util.Scanner;

public class MenuOperations {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static Scanner scan = new Scanner(System.in);

    //Null safe input methods
    public static int getValidId(String prompt) {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print(prompt);
                String input = scan.nextLine();

                if (input == null || input.trim().isEmpty()) {
                    System.out.println("ID can't be null or empty!");
                    attempts++;
                    continue;
                }

                int id = Integer.parseInt(input.trim());

                if (id <= 0) {
                    System.out.println("ID must be a positive number!");
                    attempts++;
                    continue;
                }

                return id;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                attempts++;
            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
                attempts++;
            }

            if (attempts < MAX_ATTEMPTS) {
                System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }

        throw new RuntimeException("Max attempts reached for ID input!");
    }

    public static String getValidName() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 5;

        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print("Name (minimum 3 characters): ");
                String input = scan.nextLine();

                if (input == null) {
                    System.out.println("Can't be null, try again");
                    attempts++;
                    continue;
                }
                String name = InputValidator.sanitizeInput(input);
                if (InputValidator.isValidName(name)) {
                    return name;
                }

                attempts++;
                if (attempts < MAX_ATTEMPTS) {
                    System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));
                }
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
                System.out.print("Email (format: user@company.com): ");
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


    public static String getValidDepartment() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 5;

        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print("Department (IT/HR/Finance etc.): ");
                String input = scan.nextLine();

                if (input == null) {
                    System.out.println("Department name can't be null, Please try again");
                    attempts++;
                    continue;
                }
                String department = InputValidator.sanitizeInput(input);
                if (InputValidator.isValidDepartment(department)) {
                    return department;
                }
                attempts++;
                System.out.println("Attempts left : " + (MAX_ATTEMPTS - attempts));
            } catch (Exception e) {
                System.out.println("Input error " + e.getMessage());
                attempts++;
            }
        }

        throw new RuntimeException("Max Attempts reached for department input!");
    }

    private static Double getValidSalary() {
        int attempts = 0;
        final int MAX_ATTEMPTS = 5;

        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print("Salary (in between 1 to 1 crore): ");
                String input = scan.nextLine();

                if (input == null || input.trim().isEmpty()) {
                    System.out.println("Salary can't be null or empty empty!");
                    attempts++;
                    continue;
                }

                Double salary = Double.parseDouble(input.trim());

                if (InputValidator.isValidSalary(salary)) {
                    return salary;
                }

                attempts++;
                System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));

            } catch (NumberFormatException e) {
                System.out.println("Only numbers are allowed!");
                attempts++;
            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
                attempts++;
            }
        }

        throw new RuntimeException("Max attempts reached for salary input!");
    }

    public static String getConfirmation(String message) {
        int attempts = 0;
        final int MAX_ATTEMPTS = 5;

        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print(message + " (y/yes or n/no): ");
                String input = scan.nextLine();

                if (input == null) {
                    System.out.println("Input cannot be null!");
                    attempts++;
                    continue;
                }

                String confirm = input.trim().toLowerCase();

                if (confirm.equals("y") || confirm.equals("yes") ||
                        confirm.equals("n") || confirm.equals("no")) {
                    return confirm;
                } else {
                    System.out.println("Please enter only y/yes or n/no!");
                    attempts++;
                }

            } catch (Exception e) {
                System.out.println("Input error: " + e.getMessage());
                attempts++;
            }

            if (attempts < MAX_ATTEMPTS) {
                System.out.println("Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }

        System.out.println("Maximum attempts reached. Defaulting to 'no'.");
        return "no";
    }

    // 1.
    public static void addEmployee() {
        System.out.println("==== Add new Employees ====");
        try {
            String name = getValidName();
            String email = getValidEmail();
            String department = getValidDepartment();
            Double salary = getValidSalary();
            Employee employee = new Employee(name, email, department, salary);
            if (!employee.isValid()) {
                System.out.println("Invalid employee data");
                return;
            }
            if (employeeDAO.addEmployee(employee)) {
                System.out.println("Employee added successfully!");
            } else {
                System.out.println("Failed to add employee , Database Error!");
            }
        } catch (Exception e) {
            System.out.println("Error while adding employee: " + e.getMessage());
        }
    }

    // 2.
    public static void viewAllEmployees() {
        System.out.println("==== View All Employees ====");
        try {
            List<Employee> employees = employeeDAO.getAllEmployees();
            if (employees == null) {
                System.out.println("Failed to fetch data from the database!");
                return;
            }
            if (employees.isEmpty()) {
                System.out.println("No employee found!");
            } else {
                System.out.println("Total Employees: " + employees.size());
                System.out.println("-".repeat(80));
                employees.forEach(employee -> {
                    if (employee != null) {
                        System.out.println(employee);
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Error in fetching the employees: " + e.getMessage());
        }
    }

    // 3.
    public static void viewEmployeeById() {
        System.out.println("==== View By Employee ID ====");
        try {
            int id = getValidId("Enter Employee ID: ");

            Employee employee = employeeDAO.getEmployeeById(id);
            if (employee != null) {
                System.out.println("Employee found: " + employee);
            } else {
                System.out.println("No Employee found with this id!");
            }
        } catch (Exception e) {
            System.out.println("Error while searching employee: " + e.getMessage());
        }
    }

    // 4.
    public static void updateEmployee() {
        System.out.println("==== Update Employee ====");
        try {
            int id = getValidId("Enter Employee ID: ");

            Employee employee = employeeDAO.getEmployeeById(id);
            if (employee == null) {
                System.out.println("No Employee found with this id ");
                return;
            }
            System.out.println("Current Details: " + employee);
            System.out.println("Enter new details: ");
            String name = getValidName();
            String email = getValidEmail();
            String department = getValidDepartment();
            Double salary = getValidSalary();

            employee.setName(name);
            employee.setEmail(email);
            employee.setDepartment(department);
            employee.setSalary(salary);

            if (!employee.isValid()) {
                System.out.println("Updated employee data is invalid!");
                return;
            }
            if (employeeDAO.updateEmployee(employee)) {
                System.out.println("Employee Updated Successfully!!");
            } else {
                System.out.println("Error in Updating Employee!! ");
            }
        } catch (Exception e) {
            System.out.println("Error while updating employee" + e.getMessage());
        }
    }

    //5
    public static void deleteEmployee() {
        System.out.println("==== Delete Employee ====");
        try {

            int id = getValidId("Enter Employee ID to delete: ");

            Employee employee = employeeDAO.getEmployeeById(id);
            if (employee == null) {
                System.out.println("No Employee found with this ID!");
                return;
            }

            System.out.println("Employee with id: " + employee.getId() + " will be deleted. ");
            String confirm = getConfirmation("Confirm");

            if (confirm.equalsIgnoreCase("y") || confirm.equalsIgnoreCase("yes")) {
                if (employeeDAO.deleteEmployeeById(id)) {
                    System.out.println("Employee deleted successfully!!");
                } else {
                    System.out.println("Error in deleting Employee");
                }
            } else {
                System.out.println("Cancelled! ");
            }
        } catch (Exception e) {
            System.out.println("Error while deleting employee: " + e.getMessage());
        }
    }
}