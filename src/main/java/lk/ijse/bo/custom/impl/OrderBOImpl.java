package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrderBO;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.OrderDetailDAO;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.dto.OrderDetailsDto;

import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    @Override
    public String getAllOrdersCount() throws SQLException {

        return null;
    }

    @Override
    public List<OrderDetailsDto> getAllOrderDetails() throws SQLException {
        return new OrderDAOImpl().getAllOrderDetails();

    }

    @Override
    public String splitOrderId(String currentOrderId) {
        return null;
    }

    @Override
    public String generateNextOrderId() throws SQLException {
        return new OrderDAOImpl().generateNextOrderId();

    }

    @Override
    public boolean saveOrder(String customerId, String orderId, String orderDate, String name) throws SQLException {
        return false;
    }
}
