import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    public static boolean isValidName(String name) {
        if (name == null) {
            System.out.println("Name cannot be empty");
            return false;
        }

        if(name.trim().length() < 3) {
            System.out.println("Name should have least 3 characters");
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            System.out.println("Email cannot be null!");
            return false;
        }

        String trimmedEmail = email.trim();
        if (trimmedEmail.isEmpty()) {
            System.out.println("Email cannot be empty!");
            return false;
        }

        if (!EMAIL_PATTERN.matcher(trimmedEmail).matches()) {
            System.out.println("Invalid email format! Example: user@company.com");
            return false;
        }
        return true;
    }

    public static boolean isValidDepartment(String department) {
        if (department == null) {
            System.out.println("Department cannot be null!");
            return false;
        }

        if (department.trim().isEmpty()) {
            System.out.println("Department cannot be empty!");
            return false;
        }

        return true;
    }
    public static boolean isValidSalary(Double salary) {
        if (salary == null) {
            System.out.println("Salary cannot be null!");
            return false;
        }

        if (salary <= 0 || salary > 10000000) {
            System.out.println("Salary must be greater than 0 and less than 1 crore!");
            return false;
        }

        return true;
    }

    /**
     * This method is used to sanitize user input.
     * It ensures that:
     * 1. If the input is null, it is converted to an empty string.
     * 2. Any leading or trailing whitespace is removed using trim().
     * This is important because user input can sometimes be null or contain
     * unnecessary spaces, which can cause errors or inconsistent data
     * when storing or processing it.
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return ""; // Convert null to empty string
        }
        return input.trim(); // Remove leading/trailing spaces
    }

    public static boolean isValidEmployee(Employee employee) {
        if (employee == null) {
            System.out.println("Employee object is null!");
            return false;
        }

        return isValidName(employee.getName()) &&
                isValidEmail(employee.getEmail()) &&
                isValidDepartment(employee.getDepartment()) &&
                isValidSalary(employee.getSalary());
    }

}
