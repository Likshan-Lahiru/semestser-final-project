package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    String getTotalCustomers() throws SQLException;
    Customer searchCustomerId(String txtSearchCustomerIDText) throws SQLException;


}
