package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee entity) throws SQLException {

        String sql = "INSERT INTO employee values (?,?,?,?)";
       return SQLUtil.execute(sql,
                entity.getEmployeeid(),
                entity.getEmployeeName(),
                entity.getEmployeeNIC(),
                entity.getEmployeeAddress()
                );
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employee ";
        ArrayList<Employee> employee = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            Employee dto = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            employee.add(dto);
        }
        return employee;
    }

    @Override
    public Employee search(String employeeIDText) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,employeeIDText);
        Employee entity = null;
        if (resultSet.next()) {
            entity = new Employee(
                    resultSet.getString("employee_id"),
                    resultSet.getString("employee_name"),
                    resultSet.getString("NIC"),
                    resultSet.getString("address")

            );
            System.out.println(entity.getEmployeeid());
            System.out.println(entity.getEmployeeName());
            System.out.println(entity.getEmployeeNIC());
            System.out.println(entity.getEmployeeAddress());

        }
        return entity;
    }

    @Override
    public boolean update(Employee entity) throws SQLException {
        String sql = "UPDATE employee SET employee_name=?,NIC=?,address=? WHERE employee_id=?";
       return SQLUtil.execute(sql,
                entity.getEmployeeName(),
                entity.getEmployeeNIC(),
                entity.getEmployeeAddress(),
                entity.getEmployeeid()
        );
    }

    @Override
    public boolean delete(String employeeId) throws SQLException {
        String sql = "DELETE FROM employee WHERE employee_id=?";
       return SQLUtil.execute(sql,employeeId);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public String getTotalEmployees() throws SQLException {
        String sql = "SELECT COUNT(employee_id) FROM employee";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}

