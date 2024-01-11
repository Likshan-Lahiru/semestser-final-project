package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ToolDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ToolBO extends SuperBO {
    boolean save(ToolDto dto) throws SQLException;
    ArrayList<ToolDto> getAll() throws SQLException;
    ToolDto search(String ToolId) throws SQLException;
    boolean update(ToolDto dto) throws SQLException;
    boolean delete(String toolId) throws SQLException;
}
