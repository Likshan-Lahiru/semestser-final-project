package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.dto.PlaceOrderDto;
import java.sql.*;
import java.util.ArrayList;


public class OrderDAOImpl {
    public static boolean saveOrder(String customerId,String orderId,String orderDate, String name) throws SQLException {
        String sql = "INSERT INTO orders VALUES (?,?,?,?)";
        PlaceOrderDto dto = new PlaceOrderDto(customerId, orderId, orderDate, name);
        return SQLUtil.execute(sql,
                dto.getCustomerId(),
                dto.getOrderId(),
                dto.getOrderDate(),
                dto.getName()
                );
    }
    public static String generateNextOrderId() throws SQLException {
        String sql = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1";
        ResultSet resultSet = SQLUtil.execute(sql);
        String currentOrderId = null;
        if (resultSet.next()){
            currentOrderId = resultSet.getString(1);
            return splitOrderId(currentOrderId);
        }
        return splitOrderId(null);
        /*Connection connection = DbConnection.getInstance().getConnection();


        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentOrderId = null;

        if (resultSet.next()) {
            currentOrderId = resultSet.getString(1);
            return splitOrderId(currentOrderId);
        }
        return splitOrderId(null);*/
    }
    private static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O");
            int id = Integer.parseInt(split[1]);
            System.out.println(id);
            if (id == 11) {
                id++;
                System.out.println(id);
                return "O00" + id;
            }
            id++;
            return "O00" + id;
        }
        return "O001";
    }


    public ArrayList<OrderDetailsDto> getAllOrderDetails() throws SQLException {
        String sql = "SELECT * FROM order_detail";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<OrderDetailsDto> dtoList = new ArrayList<>();
        while (resultSet.next()) {

            OrderDetailsDto dto = new OrderDetailsDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            dtoList.add(dto);
        }
        return dtoList;

        /*Connection connection = DbConnection.getInstance().getConnection();


        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<OrderDetailsDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();


*/
    }


    public String getAllOrdersCount() throws SQLException {
        String sql = "SELECT COUNT(order_id) FROM orders";
        ResultSet resultSet = SQLUtil.execute(sql);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
        /* Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(order_id) FROM orders";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;*/
    }
}
