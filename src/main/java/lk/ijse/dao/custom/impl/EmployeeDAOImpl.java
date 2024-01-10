package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.EmployeeDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(EmployeeDto dto) throws SQLException {

        String sql = "INSERT INTO employee values (?,?,?,?)";
       return SQLUtil.execute(sql,
                dto.getEmployeeid(),
                dto.getEmployeeName(),
                dto.getEmployeeNIC(),
                dto.getEmployeeAddress()
                );
    }

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException {
        String sql = "SELECT * FROM employee ";
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            EmployeeDto dto = new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            employeeDtos.add(dto);
        }
        return employeeDtos;
    }

    @Override
    public EmployeeDto search(String employeeIDText) throws SQLException {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,employeeIDText);
        EmployeeDto dto = null;
        if (resultSet.next()) {
            dto = new EmployeeDto(
                    resultSet.getString("employee_id"),
                    resultSet.getString("employee_name"),
                    resultSet.getString("NIC"),
                    resultSet.getString("address")

            );
            System.out.println(dto.getEmployeeid());
            System.out.println(dto.getEmployeeName());
            System.out.println(dto.getEmployeeNIC());
            System.out.println(dto.getEmployeeAddress());

        }
        return dto;
    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException {
        String sql = "UPDATE employee SET employee_name=?,NIC=?,address=? WHERE employee_id=?";
       return SQLUtil.execute(sql,
                dto.getEmployeeName(),
                dto.getEmployeeNIC(),
                dto.getEmployeeAddress(),
                dto.getEmployeeid()
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

