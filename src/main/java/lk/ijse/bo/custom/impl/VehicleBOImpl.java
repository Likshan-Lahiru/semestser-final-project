package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VehicleBO;
import lk.ijse.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.dto.VehicleDto;
import lk.ijse.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {
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
        return new VehicleDAOImpl().delete(txtVehicleIdText);

    }
}
