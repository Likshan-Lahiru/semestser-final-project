package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDetailDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.dto.tm.CartTm;
import lk.ijse.entity.OrderDetails;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDeatilDAOImpl implements OrderDetailDAO {
    @Override
    public boolean saveOrderDetail(String orderId, List<CartTm> cartTms) throws SQLException {
        System.out.println(orderId);
        for (CartTm cartTm : cartTms) {
            if(!saveOrderDetail(orderId, cartTm)) {

                return false;

            }
        }

        return true;
    }

    @Override
    public  boolean saveOrderDetail(String orderId, CartTm cartTm) throws SQLException {
        String sql = "INSERT INTO order_detail VALUES(?, ?, ?, ?,?,?)";
       return SQLUtil.execute(sql,
                cartTm.getToolId(),
                orderId,
                cartTm.getQty(),
                cartTm.getRentPerDay(),
                cartTm.getOrderDate(),
                cartTm.getStatus()
                );
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO order_detail VALUES(?, ?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, cartTm.getToolId());
        pstm.setString(2, orderId);
        pstm.setInt(3,  cartTm.getQty());
        pstm.setDouble(4, cartTm.getRentPerDay());
        pstm.setString(5,cartTm.getOrderDate());
        pstm.setString(6,cartTm.getStatus());



        return pstm.executeUpdate() > 0;*/
    }
    @Override
    public boolean returnOrderDetails(OrderDetails entity) throws SQLException {
        boolean result = false;
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isUpdated = new ToolDAOImpl().updateToolReturnQty(entity);
            if(isUpdated) {
                boolean isOrderDetailSaved  = new OrderDeatilDAOImpl().updateReturnPrderDetail(entity);
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
    public boolean updateReturnPrderDetail(OrderDetails entity) throws SQLException {
        String sql = "UPDATE order_detail SET status = ?, order_date = CURRENT_DATE WHERE order_id = ?  AND tool_id = ?";
        return SQLUtil.execute(sql,
                entity.getStatus(),
                entity.getOrderId(),
                entity.getToolId()
        );
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE order_detail SET status = ?, order_date = CURRENT_DATE WHERE order_id = ?  AND tool_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getStatus());
        pstm.setString(2, dto.getOrderId());
        pstm.setString(3, dto.getToolId());

        boolean isUpdate =  pstm.executeUpdate()>0;

        return isUpdate;*/
    }
}
