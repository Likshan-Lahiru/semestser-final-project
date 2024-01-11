package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.OrderDetailsDto;
import lk.ijse.dto.ToolDto;
import lk.ijse.dto.ToolWasteDetailDto;
import lk.ijse.dto.tm.CartTm;
import lk.ijse.dto.tm.StockListTm;
import lk.ijse.entity.Tool;

import java.sql.SQLException;
import java.util.List;

public interface ToolDAO extends CrudDAO<Tool> {

    boolean updateTool(List<CartTm> tmList) throws SQLException;
    boolean updateQty(CartTm cartTm) throws SQLException;
    boolean addStockList(List<StockListTm> stockListTms) throws SQLException;
    boolean updateQty2(StockListTm stockListTm) throws SQLException;
    boolean updateToolReturnQty(OrderDetailsDto dto) throws SQLException;
    boolean addToolWasteDetail(ToolWasteDetailDto dto) throws SQLException;
    boolean updateWasteQty(ToolWasteDetailDto dto) throws SQLException;
}
