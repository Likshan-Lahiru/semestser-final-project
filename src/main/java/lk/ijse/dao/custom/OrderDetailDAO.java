package lk.ijse.dao.custom;

import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO {
    boolean saveOrderDetail(String orderId, List<CartTm> cartTms) throws SQLException;
    boolean saveOrderDetail(String orderId, CartTm cartTm) throws SQLException;
    boolean returnOrderDetails(OrderDetailsDto dto) throws SQLException;
    boolean updateReturnPrderDetail(OrderDetailsDto dto) throws SQLException;
}
