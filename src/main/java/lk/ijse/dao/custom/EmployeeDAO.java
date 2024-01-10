package lk.ijse.dao.custom;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.EmployeeDto;
import java.sql.SQLException;


public interface EmployeeDAO extends CrudDAO<EmployeeDto> {
    String getTotalEmployees() throws SQLException;

}
