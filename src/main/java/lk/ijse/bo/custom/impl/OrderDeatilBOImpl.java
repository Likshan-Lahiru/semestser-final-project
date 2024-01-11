package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrderDeatilBO;
import lk.ijse.dao.custom.LoginDAO;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.ToolDAO;
import lk.ijse.dao.custom.impl.OrderDeatilDAOImpl;
import lk.ijse.dao.custom.impl.ToolDAOImpl;
import lk.ijse.dao.factory.DAOFactory;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.dto.tm.CartTm;
import lk.ijse.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDeatilBOImpl implements OrderDeatilBO {
    OrderDeatilDAOImpl orderDeatilDAO= (OrderDeatilDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    ToolDAOImpl toolDAO= (ToolDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TOOL);
    @Override
    public boolean returnOrderDetails(OrderDetailsDto dto) throws SQLException {

            boolean result = false;
            Connection connection = null;

            try {
                connection = DbConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                boolean isUpdated = toolDAO.updateToolReturnQty(new OrderDetails(
                        dto.getOrderId(),
                        dto.getToolId(),
                        dto.getQty(),
                        dto.getStatus(),
                        dto.getDate()

                ));
                if(isUpdated) {
                    boolean isOrderDetailSaved  = orderDeatilDAO.updateReturnPrderDetail(new OrderDetails(
                            dto.getOrderId(),
                            dto.getToolId(),
                            dto.getQty(),
                            dto.getStatus(),
                            dto.getDate()

                    ));
                    if (isOrderDetailSaved) {
                        connection.commit();
                        result = true;
                    }
                }
            }catch (SQLException e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
            return result;



    }

    @Override
    public boolean saveOrderDetail(String orderId, List<CartTm> cartTms) throws SQLException {
        return false;
    }

    @Override
    public boolean saveOrderDetail(String orderId, CartTm cartTm) throws SQLException {
        return false;
    }

    @Override
    public boolean returnOrderDetails(OrderDetails entity) throws SQLException {
        return false;
    }
}
