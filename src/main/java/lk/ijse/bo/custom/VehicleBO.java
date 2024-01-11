package lk.ijse.bo.custom;

import lk.ijse.dto.VehicleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleBO {
    boolean save(VehicleDto dto) throws SQLException;
    ArrayList<VehicleDto> getAll() throws SQLException;
    boolean update(VehicleDto dto) throws SQLException;
    boolean delete(String txtVehicleIdText) throws SQLException;
}
