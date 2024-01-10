package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.StockListDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.StockListDto;
import lk.ijse.dto.ToolWasteDetailDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockListDAOImpl implements StockListDAO {

    @Override
    public boolean addStockList(StockListDto stockListDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {

            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isUpdated = new ToolDAOImpl().addStockList(stockListDto.getStockListTms());
            if(isUpdated) {
                boolean isSaved = new SupplierToolDAOImpl().saveStockList(stockListDto.getStockListTms());
                if(isSaved) {


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
        String sql = "INSERT INTO tool_waste VALUES(?,?,?,?,?)";
      return SQLUtil.execute(sql,
                dto.getToolId(),
                dto.getToolName(),
                dto.getQtyOnhand(),
                dto.getWasteCount(),
                dto.getLastupdatedDate()
        );
        /*Connection connection =  DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO tool_waste VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getToolId());
        pstm.setString(2,);
        pstm.setInt(3,dto.);
        pstm.setString(4, dto.getWasteCount());
        pstm.setString(5, dto.getLastupdatedDate());



        boolean isUpdate = pstm.executeUpdate() > 0;
        return isUpdate;*/
    }
}
