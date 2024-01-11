package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.custom.LoginDAO;
import lk.ijse.dao.custom.impl.SignUpDAOImpl;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.dao.factory.DAOFactory;
import lk.ijse.dto.SupplierDto;
import lk.ijse.entity.Attandance;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAOImpl supplierDAO= (SupplierDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public ArrayList<SupplierDto> getAll() throws SQLException {
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        for (Supplier supplier:suppliers){
           SupplierDto dto = new SupplierDto(
                    supplier.getSupplierId(),
                    supplier.getSupplierName(),
                    supplier.getSupplierNIC(),
                    supplier.getSupplierAddress(),
                    supplier.getSupplierContactNumber()
            );
            supplierDtos.add(dto);
        }
        return supplierDtos;
    }

    @Override
    public boolean save(SupplierDto dto) throws SQLException {
       return supplierDAO.save(
                new Supplier(
                        dto.getSupplierId(),
                        dto.getSupplierName(),
                        dto.getSupplierNIC(),
                        dto.getSupplierAddress(),
                        dto.getSupplierContactNumber()
                )
        );

    }
    @Override
    public SupplierDto search(String searchSupplierIDText) throws SQLException {
        Supplier supplier =  supplierDAO.search(searchSupplierIDText);
        return new SupplierDto(
                supplier.getSupplierId(),
                supplier.getSupplierName(),
                supplier.getSupplierNIC(),
                supplier.getSupplierAddress(),
                supplier.getSupplierContactNumber()

        );
    }
    @Override
    public boolean update(SupplierDto dto) throws SQLException {
       return supplierDAO.update(
               new Supplier( dto.getSupplierId(),
                       dto.getSupplierName(),
                       dto.getSupplierNIC(),
                       dto.getSupplierAddress(),
                       dto.getSupplierContactNumber())


        );

    }
    @Override
    public boolean delete(String supplierId) throws SQLException {
        return supplierDAO.delete(supplierId);

    }
}
