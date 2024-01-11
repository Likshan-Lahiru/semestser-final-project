package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    @Override
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        new EmployeeDAOImpl().save(
                new Employee(
                        dto.getEmployeeid(),
                        dto.getEmployeeName(),
                        dto.getEmployeeNIC(),
                        dto.getEmployeeAddress()
                ));
        return false;
    }

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException {
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        ArrayList<Employee> employees = new EmployeeDAOImpl().getAll();
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
    public EmployeeDto search(String employeeIDText) throws SQLException {
        Employee employee = new EmployeeDAOImpl().search(employeeIDText);
       return new EmployeeDto(
                employee.getEmployeeid(),
                employee.getEmployeeName(),
                employee.getEmployeeNIC(),
                employee.getEmployeeAddress()
        );

    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException {
        return new EmployeeDAOImpl().update(new Employee(
                dto.getEmployeeid(),
                dto.getEmployeeName(),
                dto.getEmployeeNIC(),
                dto.getEmployeeAddress()
        ));

    }

    @Override
    public boolean delete(String employeeId) throws SQLException {
        return new EmployeeDAOImpl().delete(employeeId);

    }

    @Override
    public String getTotalEmployees() throws SQLException {
        return new EmployeeDAOImpl().getTotalEmployees();

    }
}
