package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.InvoiceDAO;
import lk.ijse.dto.tm.CartTm;
import java.sql.SQLException;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {
    @Override
    public boolean invoiceDetailsSave(String orderId, List<CartTm> cartTms) throws SQLException {
        for (CartTm cartTm : cartTms) {
            if(!saveDetails(orderId, cartTm)) {

                return false;

            }
        }

        return true;
    }

    @Override
    public boolean saveDetails(String orderId, CartTm cartTm) throws SQLException {
        String sql = "INSERT INTO invoice VALUES(?, ?, ?, ?,?)";
        return SQLUtil.execute(sql,
                orderId,
                cartTm.getToolId(),
                cartTm.getLblDescriptionText(),
                cartTm.getQty(),
                cartTm.getTotal()
        );
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO invoice VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setString(2, cartTm.getToolId());
        pstm.setString(3, cartTm.getLblDescriptionText());
        pstm.setInt(4,  cartTm.getQty());
        pstm.setDouble(5,cartTm.getTotal());

        return pstm.executeUpdate() >0;*/
    }
}
