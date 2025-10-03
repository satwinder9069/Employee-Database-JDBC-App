public class Employee {
    private int id;
    private String name;
    private String email;
    private String department;
    private Double salary;

    public Employee() {
        this.name = "";
        this.email = "";
        this.department = "";
        this.salary = 0.0;
    }

    public Employee(String name, String email, String department, double salary) {
        setName(name);
        setEmail(email);
        setDepartment(department);
        setSalary(salary);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name != null ? name : "";
    }

    public void setName(String name) {
        this.name = name != null ? name.trim() : "";
    }

    public String getEmail() {
        return email != null ? email : "";
    }

    public void setEmail(String email) {
        this.email = email != null ? email.trim() : "";
    }

    public String getDepartment() {
        return department != null ? department : "";
    }

    public void setDepartment(String department) {
        this.department = department != null ? department.trim() : "";
    }

    public Double getSalary() {
        return salary != null ? salary : 0.0;
    }

    public void setSalary(Double salary) {
        this.salary = salary != null ? salary : 0.0;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, " +
                "Email: %s, Department: %s, Salary: %.2f",
                id,
                getName(), //Null safe
                getEmail(),
                getDepartment(),
                getSalary());

    }

    public boolean isValid() {
        return InputValidator.isValidEmployee(this);
    }
}
