package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ToolDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.dto.ToolDto;
import lk.ijse.dto.ToolWasteDetailDto;
import lk.ijse.dto.tm.CartTm;
import lk.ijse.dto.tm.StockListTm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolDAOImpl implements ToolDAO {
    @Override
    public boolean save(ToolDto dto) throws SQLException {
        String sql ="INSERT INTO tool VALUES (?,?,?,?)";
       return SQLUtil.execute(sql,
                dto.getToolId(),
                dto.getToolName(),
                dto.getQtyOnhand(),
                dto.getRentPerDay()
                );
    }

    @Override
    public ArrayList<ToolDto> getAll() throws SQLException {
        String sql = "SELECT * FROM tool";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<ToolDto> dtoList = new ArrayList<>();
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
    public ToolDto search(String ToolId) throws SQLException {
        String sql = "SELECT * FROM tool WHERE tool_id = ?";
        ResultSet resultSet = SQLUtil.execute(sql,ToolId);
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
        String sql= "UPDATE tool SET  tool_name = ?, qty_on_hand = ?, rent_per_day_price = ? WHERE tool_id = ?";
        return SQLUtil.execute(sql,
                dto.getToolName(),
                dto.getQtyOnhand(),
                dto.getRentPerDay(),
                dto.getToolId()
                );
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
        String sql = "UPDATE tool SET qty_on_hand = qty_on_hand - ? WHERE tool_id = ?";
       return SQLUtil.execute(sql,
                cartTm.getQty(),
                cartTm.getToolId()

        );
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
        String sql = "UPDATE tool SET qty_on_hand = qty_on_hand + ? WHERE tool_id = ?";
       return SQLUtil.execute(sql,
                stockListTm.getQty(),
                stockListTm.getToolId()
        );
    }

    @Override
    public boolean updateToolReturnQty(OrderDetailsDto dto) throws SQLException {
        String sql = "UPDATE tool SET qty_on_hand = qty_on_hand + ? WHERE tool_id = ?";
       return SQLUtil.execute(sql,
              dto.getQty(),
              dto.getToolId()
        );
    }

    @Override
    public boolean delete(String toolId) throws SQLException {
        String sql = "DELETE FROM tool WHERE tool_id = ?";
       return SQLUtil.execute(sql,toolId);

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }



    @Override
    public boolean addToolWasteDetail(ToolWasteDetailDto dto) throws SQLException {

        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isUpdated = new ToolDAOImpl().updateWasteQty(dto);
            if(isUpdated) {
                boolean isUpdated2 = new StockListDAOImpl().updateWasteQty(dto);
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
        String sql = "UPDATE tool SET qty_on_hand = qty_on_hand - ? WHERE tool_id = ?";
        return SQLUtil.execute(sql,
                dto.getWasteCount(),
                dto.getToolId()
        );
    }
}
