package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceDAO extends SuperDAO {
    boolean invoiceDetailsSave(String orderId, List<CartTm> cartTms) throws SQLException;

    boolean saveDetails(String orderId, CartTm cartTm) throws SQLException;
}
