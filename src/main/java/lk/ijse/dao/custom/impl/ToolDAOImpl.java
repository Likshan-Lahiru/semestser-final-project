package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ToolDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.dto.ToolDto;
import lk.ijse.dto.ToolWasteDetailDto;
import lk.ijse.dto.tm.CartTm;
import lk.ijse.dto.tm.StockListTm;
import lk.ijse.model.StockListModel;
import lk.ijse.model.ToolModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolDAOImpl implements ToolDAO {
    @Override
    public boolean save(ToolDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql ="INSERT INTO tool VALUES (?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getToolId());
        pstm.setString(2,dto.getToolName());
        pstm.setInt(3,dto.getQtyOnhand());
        pstm.setDouble(4, dto.getRentPerDay());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public ArrayList<ToolDto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "Select * FROM tool";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<ToolDto> dtoList = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String toolId = resultSet.getString("tool_id");
            String toolName = resultSet.getString("tool_name");
            int qtyOnHand = Integer.parseInt(String.valueOf(resultSet.getInt("qty_on_hand")));
            double rentPerDayPrice =resultSet.getDouble("rent_per_day_price");

            var dto = new ToolDto(toolId, toolName, qtyOnHand, rentPerDayPrice);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public ToolDto search(String dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM tool WHERE tool_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto);
        ResultSet resultSet = pstm.executeQuery();
        ToolDto toolDto = null;
        if (resultSet.next()){
            toolDto = new ToolDto(
                    resultSet.getString("tool_id"),
                    resultSet.getString("tool_name"),
                    resultSet.getInt("qty_on_hand"),
                    resultSet.getDouble("rent_per_day_price")
            );

        }
        return toolDto;
    }

    @Override
    public boolean update(ToolDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql= "UPDATE tool SET  tool_name = ?, qty_on_hand = ?, rent_per_day_price = ? WHERE tool_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getToolName());
        pstm.setInt(2,dto.getQtyOnhand());
        pstm.setDouble(3,dto.getRentPerDay());
        pstm.setString(4, dto.getToolId());


        boolean isUpdate = pstm.executeUpdate() > 0;
        return isUpdate;
    }

    @Override
    public boolean updateTool(List<CartTm> tmList) throws SQLException {
        for (CartTm cartTm : tmList) {
            if(!updateQty(cartTm)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(CartTm cartTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE tool SET qty_on_hand = qty_on_hand - ? WHERE tool_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, cartTm.getQty());
        pstm.setString(2, cartTm.getToolId());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean addStockList(List<StockListTm> stockListTms) throws SQLException {
        for (StockListTm stockListTm : stockListTms) {
            if(!updateQty2(stockListTm)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty2(StockListTm stockListTm) throws SQLException {
        System.out.printf(String.valueOf(stockListTm.getQty()));
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE tool SET qty_on_hand = qty_on_hand + ? WHERE tool_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, stockListTm.getQty());
        pstm.setString(2, stockListTm.getToolId());



        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean updateToolReturnQty(OrderDetailsDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE tool SET qty_on_hand = qty_on_hand + ? WHERE tool_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, Integer.parseInt(dto.getQty()));
        pstm.setString(2, dto.getToolId());
        boolean issaved = pstm.executeUpdate()>0;


        return issaved;
    }

    @Override
    public boolean delete(String toolId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM tool WHERE tool_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,toolId);

        boolean isDeleted = pstm.executeUpdate()>0;

        return isDeleted;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }



    @Override
    public boolean addToolWasteDetail(ToolWasteDetailDto dto) throws SQLException {
        ToolModel toolModel = new ToolModel();
        StockListModel stockListModel  =new StockListModel();
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isUpdated = new ToolDAOImpl().updateWasteQty(dto);
            if(isUpdated) {
                boolean isUpdated2 = stockListModel.updateWasteQty(dto);
                if(isUpdated2) {
                    connection.commit();
                    result = true;
                }
            }
        }catch (SQLException e) {
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }


        return result;
    }

    @Override
    public boolean updateWasteQty(ToolWasteDetailDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE tool SET qty_on_hand = qty_on_hand - ? WHERE tool_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getWasteCount());
        pstm.setString(2, dto.getToolId());

        boolean isUpdate = pstm.executeUpdate() > 0;


        return isUpdate;
    }
}
