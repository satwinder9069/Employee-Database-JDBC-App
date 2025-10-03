import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    // CREATE - add the employee
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, email, department, salary) VALUES( ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement prepStat = conn.prepareStatement(sql);
        ) {
            prepStat.setString(1, employee.getName());
            prepStat.setString(2, employee.getEmail());
            prepStat.setString(3, employee.getDepartment());
            prepStat.setDouble(4, employee.getSalary());

            int rowsAffected = prepStat.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error in adding employee" + e.getMessage());
            return false;
        }
    }

    // READ - getting all the employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DBConnection.getConnection();
             Statement stat = conn.createStatement();
             ResultSet resultSet = stat.executeQuery(sql);

        ) {
            while (resultSet.next()) {
                Employee emp = new Employee();
                emp.setId(resultSet.getInt("id"));
                emp.setName(resultSet.getString("name"));
                emp.setEmail(resultSet.getString("email"));
                emp.setDepartment(resultSet.getString("department"));
                emp.setSalary(resultSet.getDouble("salary"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Error in fetching the employees" + e.getMessage());
        }
        return employees;
    }

    // READ - ID - get employee by id
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        Employee employee = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement prepStat = conn.prepareStatement(sql)
        ) {
            prepStat.setInt(1, id);
            ResultSet resultSet = prepStat.executeQuery();

            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setSalary(resultSet.getDouble("salary"));
            }
        } catch (SQLException e) {
            System.out.println("Error in fetching the employee" + e.getMessage());
        }
        return employee;
    }

    // UPDATE
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, email = ?, department = ?, salary = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement prepStat = conn.prepareStatement(sql);
        ) {
            prepStat.setString(1, employee.getName());
            prepStat.setString(2, employee.getEmail());
            prepStat.setString(3, employee.getDepartment());
            prepStat.setDouble(4, employee.getSalary());
            prepStat.setInt(5, employee.getId());

            int rowsAffected = prepStat.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error in updating Employees" + e.getMessage());
            return false;
        }
    }

    //DELETE
    public boolean deleteEmployeeById(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement prepStat = conn.prepareStatement(sql);
        ) {
            prepStat.setInt(1, id);
            int rowsAffected = prepStat.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error in deleting employees" + e.getMessage());
            return false;
        }
    }
}
