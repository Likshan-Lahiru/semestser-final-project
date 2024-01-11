package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    @Override
    public boolean save(CustomerDto dto) throws SQLException {
        return new CustomerDAOImpl().save(new Customer(
                dto.getCustomerId(),
                dto.getCustomerName(),
                dto.getCustomerAddress(),
                dto.getCustomerNic(),
                dto.getCustomerContactNumber(),
                dto.getCustomerEmail()
                ));
    }

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public CustomerDto search(String txtSearchCustomerContactText) throws SQLException {
        return null;
    }

    @Override
    public CustomerDto searchCustomerId(String txtSearchCustomerIDText) throws SQLException {
        return null;
    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String txtCustomerIdText) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getTotalCustomers() throws SQLException {
        return null;
    }
}
