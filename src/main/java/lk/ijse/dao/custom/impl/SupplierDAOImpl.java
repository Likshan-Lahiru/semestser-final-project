package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SupplierDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<SupplierDto> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier ";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<SupplierDto> supplierDtoList = new ArrayList<>();
        while (resultSet.next()) {
            String Supplier_id = resultSet.getString(1);
            String Supplier_name = resultSet.getString(2);
            String Supplier_nic = resultSet.getString(3);
            String Supplier_address = resultSet.getString(4);
            String Supplier_contact = resultSet.getString(5);

            SupplierDto dto = new SupplierDto(Supplier_id, Supplier_name, Supplier_address, Supplier_nic, Supplier_contact);
            supplierDtoList.add(dto);
        }

        return supplierDtoList;
    }

    @Override
    public boolean save(SupplierDto dto) throws SQLException {
        String sql = "INSERT INTO supplier VALUES (?, ?, ?, ?, ?)";
        return  SQLUtil.execute(sql,
                dto.getSupplierId(),
                dto.getSupplierName(),
                dto.getSupplierNIC(),
                dto.getSupplierAddress(),
                dto.getSupplierContactNumber()
                );

    }

    @Override
    public SupplierDto search(String searchSupplierIDText) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplier_id= ?";
        ResultSet resultSet = SQLUtil.execute(sql,searchSupplierIDText);
        SupplierDto dto = null;
        if (resultSet.next()) {
            dto = new SupplierDto(
                    resultSet.getString("supplier_id"),
                    resultSet.getString("supplier_name"),
                    resultSet.getString("NIC"),
                    resultSet.getString("address"),
                    resultSet.getString("contact_number")
            );
        }
        return dto;
    }

    @Override
    public boolean update(SupplierDto dto) throws SQLException {
        String sql = "UPDATE supplier SET supplier_name=?, NIC=?, address=?, contact_number=? WHERE supplier_id=?";
      return  SQLUtil.execute(sql,
                dto.getSupplierName(),
                dto.getSupplierNIC(),
                dto.getSupplierAddress(),
                dto.getSupplierContactNumber(),
                dto.getSupplierId()
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
