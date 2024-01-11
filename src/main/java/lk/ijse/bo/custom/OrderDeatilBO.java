package lk.ijse.bo.custom;

import lk.ijse.dto.OrderDetailsDto;

import java.sql.SQLException;

public interface OrderDeatilBO {
    boolean returnOrderDetails(OrderDetailsDto dto) throws SQLException;
}
