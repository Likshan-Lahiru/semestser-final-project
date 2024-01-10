package lk.ijse.dao.custom;

import lk.ijse.dto.PlaceOrderDto;

import java.sql.SQLException;

public interface PlaceOrderDAO {
    boolean placeOrder(PlaceOrderDto dto) throws SQLException;
}
