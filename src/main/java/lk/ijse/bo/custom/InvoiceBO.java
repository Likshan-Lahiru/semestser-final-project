package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceBO extends SuperBO {
    boolean invoiceDetailsSave(String orderId, List<CartTm> cartTms) throws SQLException;
}
