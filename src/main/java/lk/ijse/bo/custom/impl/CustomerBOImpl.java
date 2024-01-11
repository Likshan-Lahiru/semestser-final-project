package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
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
         ArrayList<CustomerDto> customerDto = new ArrayList<>();
        ArrayList<Customer> customerEntity = new CustomerDAOImpl().getAll();
        for (Customer customer:customerEntity){
            customerDto.add(new CustomerDto(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerNic(),
                    customer.getCustomerContactNumber(),
                    customer.getCustomerEmail()
                    ));
        }
        return customerDto;
    }

    @Override
    public CustomerDto search(String txtSearchCustomerContactText) throws SQLException {
        Customer customer  = new CustomerDAOImpl().search(txtSearchCustomerContactText);
         return new CustomerDto(
         customer.getCustomerId(),
         customer.getCustomerName(),
         customer.getCustomerAddress(),
         customer.getCustomerNic(),
         customer.getCustomerContactNumber(),
         customer.getCustomerEmail()
        );

    }

    @Override
    public CustomerDto searchCustomerId(String txtSearchCustomerIDText) throws SQLException {
        Customer customer = new CustomerDAOImpl().searchCustomerId(txtSearchCustomerIDText);
       return new CustomerDto(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getCustomerNic(),
                customer.getCustomerContactNumber(),
                customer.getCustomerEmail()
        );

    }

    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
      return new CustomerDAOImpl().update(
                new Customer(
                        dto.getCustomerId(),
                        dto.getCustomerName(),
                        dto.getCustomerAddress(),
                        dto.getCustomerNic(),
                        dto.getCustomerContactNumber(),
                        dto.getCustomerEmail()
                ));
    }

    @Override
    public boolean delete(String txtCustomerIdText) throws SQLException, ClassNotFoundException {
        return new CustomerDAOImpl().delete(txtCustomerIdText);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getTotalCustomers() throws SQLException {
        return new CustomerDAOImpl().getTotalCustomers();
    }
}
