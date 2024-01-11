package lk.ijse.bo.custom;

import lk.ijse.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO {
    boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    ArrayList<EmployeeDto> getAll() throws SQLException;
    EmployeeDto search(String employeeIDText) throws SQLException;
    boolean update(EmployeeDto dto) throws SQLException;
    boolean delete(String employeeId) throws SQLException;
    String getTotalEmployees() throws SQLException;
}
