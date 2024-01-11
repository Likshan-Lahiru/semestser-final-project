package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean save(CustomerDto dto) throws SQLException;
    ArrayList<CustomerDto> getAll() throws SQLException;
    CustomerDto search(String txtSearchCustomerContactText) throws SQLException;
    CustomerDto searchCustomerId(String txtSearchCustomerIDText) throws SQLException;
    boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException;
    boolean delete(String txtCustomerIdText) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    String getTotalCustomers() throws SQLException;
}
