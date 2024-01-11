package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ToolBO;
import lk.ijse.dao.custom.LoginDAO;
import lk.ijse.dao.custom.ToolDAO;
import lk.ijse.dao.custom.impl.ToolDAOImpl;
import lk.ijse.dao.factory.DAOFactory;
import lk.ijse.dto.ToolDto;
import lk.ijse.entity.Tool;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.ArrayList;

public class ToolBOImpl implements ToolBO {
    ToolDAO toolDAO= (ToolDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TOOL);
    @SneakyThrows
    @Override
    public boolean save(ToolDto dto) throws SQLException {
        return toolDAO.save(
                new Tool(
                        dto.getToolId(),
                        dto.getToolName(),
                        dto.getQtyOnhand(),
                        dto.getRentPerDay()
                )
        );

    }
    @SneakyThrows
    @Override
    public ArrayList<ToolDto> getAll() throws SQLException {
        ArrayList<ToolDto>  toolDtos = new ArrayList<>();
        ArrayList<Tool> toolArrayList = toolDAO.getAll();
        for (Tool tool:toolArrayList){
          ToolDto toolDto = new ToolDto(
                    tool.getToolId(),
                    tool.getToolName(),
                    tool.getQtyOnhand(),
                    tool.getRentPerDay()
            );
        toolDtos.add(toolDto);
        }
        return toolDtos;
    }
    @SneakyThrows
    @Override
    public ToolDto search(String ToolId) throws SQLException {
       Tool tool = toolDAO.search(ToolId);
        return new ToolDto(
               tool.getToolId(),
               tool.getToolName(),
               tool.getQtyOnhand(),
               tool.getRentPerDay()
       );

    }
    @SneakyThrows
    @Override
    public boolean update(ToolDto dto) throws SQLException {
        return toolDAO.update(
                new Tool(
                        dto.getToolId(),
                        dto.getToolName(),
                        dto.getQtyOnhand(),
                        dto.getRentPerDay()
                )
        );

    }
    @SneakyThrows
    @Override
    public boolean delete(String toolId) throws SQLException {
        return toolDAO.delete(toolId);
    }
}
