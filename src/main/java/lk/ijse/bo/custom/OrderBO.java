package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.OrderDetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    String getAllOrdersCount() throws SQLException;
    List<OrderDetailsDto> getAllOrderDetails() throws SQLException;
    String splitOrderId(String currentOrderId);
    String generateNextOrderId() throws SQLException;
    boolean saveOrder(String customerId,String orderId,String orderDate, String name) throws SQLException;
}
