package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.dto.tm.CartTm;
import lk.ijse.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends SuperDAO {
    boolean saveOrderDetail(String orderId, List<CartTm> cartTms) throws SQLException;
    boolean saveOrderDetail(String orderId, CartTm cartTm) throws SQLException;
    boolean returnOrderDetails(OrderDetails entity) throws SQLException;
    boolean updateReturnPrderDetail(OrderDetails entity) throws SQLException;
}
