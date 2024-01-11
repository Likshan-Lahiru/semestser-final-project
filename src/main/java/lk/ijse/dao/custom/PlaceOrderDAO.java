package lk.ijse.dao.custom;

import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.entity.PlaceOrder;

import java.sql.SQLException;

public interface PlaceOrderDAO {
    boolean placeOrder(PlaceOrder entity) throws SQLException;
}
