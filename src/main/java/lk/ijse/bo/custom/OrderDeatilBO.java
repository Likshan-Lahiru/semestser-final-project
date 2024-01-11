package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.dto.tm.CartTm;
import lk.ijse.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDeatilBO extends SuperBO {
    boolean returnOrderDetails(OrderDetailsDto dto) throws SQLException;
    boolean saveOrderDetail(String orderId, List<CartTm> cartTms) throws SQLException;
    boolean saveOrderDetail(String orderId, CartTm cartTm) throws SQLException;
    boolean returnOrderDetails(OrderDetails entity) throws SQLException;

}
