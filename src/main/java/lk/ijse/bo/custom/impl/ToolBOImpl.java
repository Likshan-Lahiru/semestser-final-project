package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ToolBO;
import lk.ijse.dao.custom.impl.ToolDAOImpl;
import lk.ijse.dto.ToolDto;
import lk.ijse.entity.Tool;

import java.sql.SQLException;
import java.util.ArrayList;

public class ToolBOImpl implements ToolBO {
    @Override
    public boolean save(ToolDto dto) throws SQLException {
        return new ToolDAOImpl().save(
                new Tool(
                        dto.getToolId(),
                        dto.getToolName(),
                        dto.getQtyOnhand(),
                        dto.getRentPerDay()
                )
        );

    }

    @Override
    public ArrayList<ToolDto> getAll() throws SQLException {
        ArrayList<ToolDto>  toolDtos = new ArrayList<>();
        ArrayList<Tool> toolArrayList = new ToolDAOImpl().getAll();
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

    @Override
    public ToolDto search(String ToolId) throws SQLException {
       Tool tool = new ToolDAOImpl().search(ToolId);
        return new ToolDto(
               tool.getToolId(),
               tool.getToolName(),
               tool.getQtyOnhand(),
               tool.getRentPerDay()
       );

    }

    @Override
    public boolean update(ToolDto dto) throws SQLException {
        return new ToolDAOImpl().update(
                new Tool(
                        dto.getToolId(),
                        dto.getToolName(),
                        dto.getQtyOnhand(),
                        dto.getRentPerDay()
                )
        );

    }

    @Override
    public boolean delete(String toolId) throws SQLException {
        return new ToolDAOImpl().delete(toolId);
    }
}
