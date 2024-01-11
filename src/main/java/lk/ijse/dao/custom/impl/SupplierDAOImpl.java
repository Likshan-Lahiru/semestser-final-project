package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SupplierDto;
import lk.ijse.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier ";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<Supplier> supplierDtoList = new ArrayList<>();
        while (resultSet.next()) {
            String Supplier_id = resultSet.getString(1);
            String Supplier_name = resultSet.getString(2);
            String Supplier_nic = resultSet.getString(3);
            String Supplier_address = resultSet.getString(4);
            String Supplier_contact = resultSet.getString(5);

            Supplier entity = new Supplier(Supplier_id, Supplier_name, Supplier_address, Supplier_nic, Supplier_contact);
            supplierDtoList.add(entity);
        }

        return supplierDtoList;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException {
        String sql = "INSERT INTO supplier VALUES (?, ?, ?, ?, ?)";
        return  SQLUtil.execute(sql,
                entity.getSupplierId(),
                entity.getSupplierName(),
                entity.getSupplierNIC(),
                entity.getSupplierAddress(),
                entity.getSupplierContactNumber()
                );

    }

    @Override
    public Supplier search(String searchSupplierIDText) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplier_id= ?";
        ResultSet resultSet = SQLUtil.execute(sql,searchSupplierIDText);
        Supplier entity = null;
        if (resultSet.next()) {
            entity = new Supplier(
                    resultSet.getString("supplier_id"),
                    resultSet.getString("supplier_name"),
                    resultSet.getString("NIC"),
                    resultSet.getString("address"),
                    resultSet.getString("contact_number")
            );
        }
        return entity;
    }

    @Override
    public boolean update(Supplier entity) throws SQLException {
        String sql = "UPDATE supplier SET supplier_name=?, NIC=?, address=?, contact_number=? WHERE supplier_id=?";
        return  SQLUtil.execute(sql,
                entity.getSupplierName(),
                entity.getSupplierNIC(),
                entity.getSupplierAddress(),
                entity.getSupplierContactNumber(),
                entity.getSupplierId()
                );
    }

    @Override
    public boolean delete(String supplierId) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplier_id=?";
        return SQLUtil.execute(sql,supplierId);
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }


}
