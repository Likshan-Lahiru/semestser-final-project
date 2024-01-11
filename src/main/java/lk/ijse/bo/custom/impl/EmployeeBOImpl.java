package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.dao.factory.DAOFactory;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Employee;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
       return employeeDAO.save(
                new Employee(
                        dto.getEmployeeid(),
                        dto.getEmployeeName(),
                        dto.getEmployeeNIC(),
                        dto.getEmployeeAddress()
                ));

    }

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        ArrayList<Employee> employees = employeeDAO.getAll();
        for (Employee employee: employees){
            EmployeeDto dto = new EmployeeDto(
            employee.getEmployeeid(),
            employee.getEmployeeName(),
            employee.getEmployeeNIC(),
            employee.getEmployeeAddress()
            );
            employeeDtos.add(dto);
        }
        return employeeDtos;
    }

    @Override
    public EmployeeDto search(String employeeIDText) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(employeeIDText);
       return new EmployeeDto(
                employee.getEmployeeid(),
                employee.getEmployeeName(),
                employee.getEmployeeNIC(),
                employee.getEmployeeAddress()
        );

    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                dto.getEmployeeid(),
                dto.getEmployeeName(),
                dto.getEmployeeNIC(),
                dto.getEmployeeAddress()
        ));

    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employeeId);

    }

    @Override
    public String getTotalEmployees() throws SQLException {
        return employeeDAO.getTotalEmployees();

    }
}
