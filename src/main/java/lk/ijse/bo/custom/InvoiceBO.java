package lk.ijse.bo.custom;

import lk.ijse.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceBO {
    boolean invoiceDetailsSave(String orderId, List<CartTm> cartTms) throws SQLException;
}
