package lk.ijse.bo.custom;

import lk.ijse.dto.PlaceOrderDto;

import java.sql.SQLException;

public interface PlaceOrderBO {
    boolean placeOrder(PlaceOrderDto dto) throws SQLException;
}
