package lk.ijse.dao.custom;

import lk.ijse.dto.StockListDto;
import lk.ijse.dto.ToolWasteDetailDto;

import java.sql.SQLException;

public interface StockListDAO {
    boolean addStockList(StockListDto stockListDto) throws SQLException;
    boolean updateWasteQty(ToolWasteDetailDto dto) throws SQLException;
}
