package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.PlaceOrderDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.entity.PlaceOrder;
import org.bridj.cpp.com.VARIANT;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderDAOImpl  {


   /* @Override
    public boolean placeOrder(PlaceOrder entity) throws SQLException {
        boolean result = false;
        Connection connection = null;
        String orderId = entity.getOrderId();
        String customerId = entity.getCustomerId();
        System.out.println(orderId);
        System.out.println(customerId);
        try {
            connection = DbConnection.getInstance().getConnection();
           connection.setAutoCommit(false);

            boolean isOrderSaved =  new OrderDAOImpl().saveOrder(entity.getOrderId(),customerId,entity.getOrderDate(),entity.getName());
            if (isOrderSaved) {

                boolean isUpdated = new ToolDAOImpl().updateTool(entity.getCartTms());
                if(isUpdated) {
                    boolean isOrderDetailSaved = new OrderDeatilDAOImpl().saveOrderDetail(orderId, entity.getCartTms());
                   if(isOrderDetailSaved) {
                        boolean isInvoiceSaved = new InvoiceDAOImpl().invoiceDetailsSave(orderId, entity.getCartTms());
                        if (isInvoiceSaved) {
                            connection.commit();
                            result = true;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;


    }*/

}
