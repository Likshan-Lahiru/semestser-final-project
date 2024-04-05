package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.factory.DAOFactory;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean save(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(
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
    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
         ArrayList<CustomerDto> customerDto = new ArrayList<>();
        ArrayList<Customer> customerEntity = customerDAO.getAll();
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
    public CustomerDto search(String txtSearchCustomerContactText) throws SQLException, ClassNotFoundException {
        Customer customer  = customerDAO.search(txtSearchCustomerContactText);
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
        Customer customer = customerDAO.searchCustomerId(txtSearchCustomerIDText);
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
      return customerDAO.update(
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
        return customerDAO.delete(txtCustomerIdText);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getTotalCustomers() throws SQLException {
        return customerDAO.getTotalCustomers();
    }
}
