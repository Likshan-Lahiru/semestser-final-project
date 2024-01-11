package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.PlaceOrderDto;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    boolean placeOrder(PlaceOrderDto dto) throws SQLException;
    String generateNextOrderId() throws SQLException;
}
