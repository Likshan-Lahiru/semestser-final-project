package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer entity) throws SQLException {
        String query = "INSERT INTO customer (customer_id,customer_name,address,NIC,contact_number,email) VALUES (?, ?, ?, ?, ?, ?) ";
        return SQLUtil.execute(query,
        entity.getCustomerId(),entity.getCustomerName(),entity.getCustomerAddress(),entity.getCustomerNic(),
                entity.getCustomerContactNumber(), entity.getCustomerEmail());
    }
    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> getAllCustomer=new ArrayList<>();
        while (rst.next()){
            Customer customer = new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getString(5),rst.getString(6));
            getAllCustomer.add(customer);
        }
        return getAllCustomer;
    }
    @Override
    public  Customer search(String txtSearchCustomerContactText) throws SQLException {
        String sql = "SELECT * FROM customer WHERE contact_number = ?";
       ResultSet resultSet = SQLUtil.execute(sql,txtSearchCustomerContactText);
        Customer customer = null;
       while (resultSet.next()){
           customer= new Customer(
                   resultSet.getString("customer_id"),
                   resultSet.getString("customer_name"),
                   resultSet.getString("address"),
                   resultSet.getString("NIC"),
                   resultSet.getString("contact_number"),
                   resultSet.getString("email")
           );
       }
       return customer;
    }
    @Override
    public  Customer searchCustomerId(String txtSearchCustomerIDText) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customer_id= ?";
        ResultSet resultSet = SQLUtil.execute(sql,txtSearchCustomerIDText);
        Customer dto = null;
        if (resultSet.next()) {
            dto = new Customer(
                    resultSet.getString("customer_id"),
                    resultSet.getString("customer_name"),
                    resultSet.getString("address"),
                    resultSet.getString("NIC"),
                    resultSet.getString("contact_number"),
                    resultSet.getString("email")
            );
        }

        return dto;
    }
    @Override
    public boolean update(Customer Entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET customer_name=?,address=?,NIC=?,contact_number=? ,email=? WHERE customer_id=? ",
                 Entity.getCustomerName(),Entity.getCustomerAddress(),Entity.getCustomerNic(),
                 Entity.getCustomerContactNumber(),Entity.getCustomerEmail(),Entity.getCustomerId());
    }
    @Override
    public boolean delete(String txtCustomerIdText) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE customer_id=?",txtCustomerIdText);
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public String getTotalCustomers() throws SQLException {
        ResultSet resultSet  = SQLUtil.execute("SELECT COUNT(customer_id) FROM customer");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
