package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PlaceOrderBO;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.ToolDAO;
import lk.ijse.dao.factory.DAOFactory;
import lk.ijse.dao.custom.impl.*;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.PlaceOrderDto;
import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ToolDAO toolDAO = (ToolDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TOOL);
    OrderDeatilDAOImpl orderDeatilDAO = (OrderDeatilDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    InvoiceDAOImpl invoiceDAO = (InvoiceDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVOICE);


    @Override
    public boolean placeOrder(PlaceOrderDto dto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        String orderId = dto.getOrderId();
        String customerId = dto.getCustomerId();
        System.out.println(orderId);
        System.out.println(customerId);
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved =  orderDAO.saveOrder(dto.getOrderId(),customerId,dto.getOrderDate(),dto.getName());
            if (isOrderSaved) {

                boolean isUpdated = toolDAO.updateTool(dto.getCartTms());
                if(isUpdated) {
                    boolean isOrderDetailSaved = orderDeatilDAO.saveOrderDetail(orderId, dto.getCartTms());
                    if(isOrderDetailSaved) {
                        boolean isInvoiceSaved = invoiceDAO.invoiceDetailsSave(orderId, dto.getCartTms());
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

    }

    @Override
    public String generateNextOrderId() throws SQLException {
        return new OrderDAOImpl().generateNextOrderId();

    }
}
