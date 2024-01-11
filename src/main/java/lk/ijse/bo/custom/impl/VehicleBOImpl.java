package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VehicleBO;
import lk.ijse.dao.custom.ToolDAO;
import lk.ijse.dao.custom.impl.ToolDAOImpl;
import lk.ijse.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.dao.factory.DAOFactory;
import lk.ijse.dto.VehicleDto;
import lk.ijse.entity.Vehicle;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {
    VehicleDAOImpl vehicleDAO= (VehicleDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public boolean save(VehicleDto dto) throws SQLException {
        return new VehicleDAOImpl().save(
                new Vehicle(
                        dto.getVehicleId(),
                        dto.getVehicleStatus(),
                        dto.getLastServiceDate(),
                        dto.getNumberPlateNo()
                )
        );

    }

    @Override
    public ArrayList<VehicleDto> getAll() throws SQLException {
        ArrayList<VehicleDto> vehicleDtos = new ArrayList<>();
        ArrayList<Vehicle> vehicles = new VehicleDAOImpl().getAll();
        for (Vehicle vehicle:vehicles){
           VehicleDto dto = new VehicleDto(
                    vehicle.getVehicleId(),
                    vehicle.getVehicleStatus(),
                    vehicle.getLastServiceDate(),
                    vehicle.getNumberPlateNo()
            );
           vehicleDtos.add(dto);
        }
        return vehicleDtos;
    }

    @Override
    public boolean update(VehicleDto dto) throws SQLException {
        return new VehicleDAOImpl().update(
                new Vehicle(
                        dto.getVehicleId(),
                        dto.getVehicleStatus(),
                        dto.getLastServiceDate(),
                        dto.getNumberPlateNo()
                )
        );

    }

    @Override
    public boolean delete(String txtVehicleIdText) throws SQLException {
        return vehicleDAO.delete(txtVehicleIdText);

    }
}
