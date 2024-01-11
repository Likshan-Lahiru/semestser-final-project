package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PlaceOrderBO;
import lk.ijse.dao.custom.impl.PlaceOrderDAOImpl;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.entity.PlaceOrder;

import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    @Override
    public boolean placeOrder(PlaceOrderDto dto) throws SQLException {
       return new PlaceOrderDAOImpl().placeOrder(
                new PlaceOrder(
                        dto.getOrderId(),
                        dto.getCustomerId(),
                        dto.getOrderDate(),
                        dto.getCartTms(),
                        dto.getName()
                )
        );

    }
}
