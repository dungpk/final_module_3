package com.codegym.dao;

import com.codegym.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO {
    public void  insertEmployee
            (String name,String email,String address,String phone,int salary,int salary_department) throws SQLException;

    public Employee selectEmployee(int id);

    public List<Employee> selectAllEmployees();
    public boolean deleteEmployee(int id) throws SQLException;

    public boolean updateEmployee(int id, String name, String email, String address, String phone, int salary, int department) throws SQLException;
    public List<Employee> selectEmployee(String name);
}
