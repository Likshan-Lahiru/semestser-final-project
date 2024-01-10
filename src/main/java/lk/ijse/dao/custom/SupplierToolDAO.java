package lk.ijse.dao.custom;

import lk.ijse.dto.tm.StockListTm;

import java.sql.SQLException;
import java.util.List;

public interface SupplierToolDAO {
    boolean saveStockList(List<StockListTm> stockListTms) throws SQLException;
    boolean saveStock(StockListTm stockListTms) throws SQLException;
}
