package lk.ijse.dao.custom;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Employee;

import java.sql.SQLException;


public interface EmployeeDAO extends CrudDAO<Employee> {
    String getTotalEmployees() throws SQLException;

}
