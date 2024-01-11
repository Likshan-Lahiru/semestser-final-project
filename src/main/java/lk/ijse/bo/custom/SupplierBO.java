package lk.ijse.bo.custom;

import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO {
    ArrayList<SupplierDto> getAll() throws SQLException;
    boolean save(SupplierDto dto) throws SQLException;
    SupplierDto search(String searchSupplierIDText) throws SQLException;
    boolean update(SupplierDto dto) throws SQLException;
    boolean delete(String supplierId) throws SQLException;
}
