package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException;
    EmployeeDto search(String employeeIDText) throws SQLException, ClassNotFoundException;
    boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean delete(String employeeId) throws SQLException, ClassNotFoundException;
    String getTotalEmployees() throws SQLException;
}
