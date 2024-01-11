package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.VehicleDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.VehicleDto;
import lk.ijse.entity.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public boolean save(Vehicle entity) throws SQLException {
        String sql = "INSERT INTO vehical values (?,?,?,?)";
        return   SQLUtil.execute(sql,
                entity.getVehicleId(),
                entity.getVehicleStatus(),
                entity.getLastServiceDate(),
                entity.getNumberPlateNo()
                );
    }

    @Override
    public ArrayList<Vehicle> getAll() throws SQLException {
        String sql= "SELECT * FROM vehical ";
        ResultSet resultSet = SQLUtil.execute(sql);
        ArrayList<Vehicle> vehicleEntity =new ArrayList<>();
        while (resultSet.next()){
            Vehicle entity = new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            vehicleEntity.add(entity);
        }
        return vehicleEntity;

    }

    @Override
    public boolean update(Vehicle entity) throws SQLException {
        String sql = "UPDATE vehical SET status=?, last_service_date=?, number_plate_no=? WHERE vehical_id=?";
        return SQLUtil.execute(sql,
                entity.getVehicleStatus(),
                entity.getLastServiceDate(),
                entity.getNumberPlateNo(),
                entity.getVehicleId()
        );
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE vehical SET status=?, last_service_date=?, number_plate_no=? WHERE vehical_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getVehicleStatus());
        pstm.setString(2, dto.getLastServiceDate());
        pstm.setString(3, dto.getNumberPlateNo());
        pstm.setString(4, dto.getVehicleId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;*/
    }

    @Override
    public boolean delete(String txtVehicleIdText) throws SQLException {
        String sql = "DELETE FROM vehical WHERE vehical_id=?";
        return SQLUtil.execute(sql,txtVehicleIdText);
        /* DbConnection connection = DbConnection.getInstance();
        String sql = "DELETE FROM vehical WHERE vehical_id=?";
        PreparedStatement pstm = connection.getConnection().prepareStatement(sql);

        pstm.setString(1, txtVehicleIdText);

        boolean isDeleted = pstm.executeUpdate() > 0;
        return isDeleted;*/
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Vehicle search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
