package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(CustomerDto dto) throws SQLException {
        String query = "INSERT INTO customer (customer_id,customer_name,address,NIC,contact_number,email) VALUES (?, ?, ?, ?, ?, ?) ";
        return SQLUtil.execute(query,
        dto.getCustomerId(),dto.getCustomerName(),dto.getCustomerAddress(),dto.getCustomerNic(),
                dto.getCustomerContactNumber(), dto.getCustomerEmail());
    }
    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDto> getAllCustomer=new ArrayList<>();
        while (rst.next()){
            CustomerDto customerDto = new CustomerDto(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),
                    rst.getString(5),rst.getString(6));
            getAllCustomer.add(customerDto);
        }
        return getAllCustomer;
    }
    @Override
    public  CustomerDto search(String txtSearchCustomerContactText) throws SQLException {
        String sql = "SELECT * FROM customer WHERE contact_number = ?";
       ResultSet resultSet = SQLUtil.execute(sql,txtSearchCustomerContactText);
        CustomerDto customerDto = null;
       while (resultSet.next()){
           customerDto= new CustomerDto(
                   resultSet.getString("customer_id"),
                   resultSet.getString("customer_name"),
                   resultSet.getString("address"),
                   resultSet.getString("NIC"),
                   resultSet.getString("contact_number"),
                   resultSet.getString("email")
           );
       }
       return customerDto;
    }
    @Override
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET customer_name=?,address=?,NIC=?,contact_number=? ,email=? WHERE customer_id=? ",
                 dto.getCustomerName(),dto.getCustomerAddress(),dto.getCustomerNic(),
                 dto.getCustomerContactNumber(),dto.getCustomerEmail(),dto.getCustomerId());
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
