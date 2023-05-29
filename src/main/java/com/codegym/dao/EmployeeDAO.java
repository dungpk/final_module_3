package com.codegym.dao;

import com.codegym.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO implements IEmployeeDAO{

    private String jdbcURL = "jdbc:mysql://localhost:3306/quanlynhanvien?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";



    private static final String SELECT_ALL_EMPLOYEES = "SELECT employee.*, department.name AS department_name\n" +
            "FROM employee\n" +
            "JOIN department\n" +
            "ON employee.id_department = department.id;";

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee" +
            " (name, email, address,phone,salary,id_department) VALUES (?,?,?,?,?,?);";


    private static final String SELECT_EMPLOYEE_BY_ID = "select * from employee where id =?";

    private static final String SEARCH_EMPLOYEE_BY_NAME = "SELECT * FROM employee ORDER BY name";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }



    @Override
    public void insertEmployee(String name,String email,String address,String phone,int salary,int salary_department)
            throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, salary);
            preparedStatement.setInt(6, salary_department);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Employee selectEmployee(int id) {
        Employee employee = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int salary = rs.getInt("salary");
                int department_id = rs.getInt("address_id");
                String department;
                if(department_id == 1){
                    department= "Sales";
                }else if(department_id == 2){
                    department= "Marketing";
                }else if(department_id ==3){
                    department= "Engineering";
                }else{
                    department= "Finance";
                }

                employee = new Employee(id, name, email, address,phone,salary,address);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employee;
    }

    @Override
    public List<Employee> selectAllEmployees() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Employee> employees = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES)) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int salary = rs.getInt("salary");
                String department = rs.getString("department_name");

                employees.add(new Employee(id, name, email, address,phone,salary,department));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;

    }

    @Override
    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        String query = "{CALL delete_employee(?)}";
        try (Connection connection = getConnection();
             CallableStatement statement = connection.prepareCall(query);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateEmployee(int id, String name, String email, String address, String phone, int salary, int department) throws SQLException {
        boolean rowUpdated = false;
        String query = "{CALL update_employee(?,?,?,?)}";
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {
            // Step 3: Execute the query or update query
            callableStatement.setInt(1,id);
            callableStatement.setString(2, name);
            callableStatement.setString(3, address);
            callableStatement.setString(4, phone);
            callableStatement.setInt(5, salary);
            callableStatement.setInt(6, department);
            rowUpdated = callableStatement.executeUpdate() > 0;

        }catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public List<Employee> selectEmployee(String name) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPLOYEE_BY_NAME);) {
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int salary = rs.getInt("salary");
                String department = rs.getString("department_name");

                employees.add(new Employee(id, name, email, address,phone,salary,department));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;
    }

}
