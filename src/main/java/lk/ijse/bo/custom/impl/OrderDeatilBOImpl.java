package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrderDeatilBO;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.impl.OrderDeatilDAOImpl;
import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.entity.OrderDetails;

import java.sql.SQLException;

public class OrderDeatilBOImpl implements OrderDeatilBO {
    @Override
    public boolean returnOrderDetails(OrderDetailsDto dto) throws SQLException {
        return new OrderDeatilDAOImpl().returnOrderDetails(
                new OrderDetails(
                        dto.getOrderId(),
                        dto.getToolId(),
                        dto.getQty(),
                        dto.getStatus(),
                        dto.getDate()
                )
        );

    }
}
