package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.VehicleDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.VehicleDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public boolean save(VehicleDto dto) throws SQLException {
        String sql = "INSERT INTO vehical values (?,?,?,?)";
        return   SQLUtil.execute(sql,
                dto.getVehicleId(),
                dto.getVehicleStatus(),
                dto.getLastServiceDate(),
                dto.getNumberPlateNo()
                );
    }

    @Override
    public ArrayList<VehicleDto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql= "SELECT * FROM vehical ";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ArrayList<VehicleDto> vehicleDtos =new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            String vehical_id =resultSet.getString(1);
            String status =resultSet.getString(2);
            String last_service_date =resultSet.getString(3);
            String number_plate_no  =resultSet.getString(4);


            VehicleDto dto = new VehicleDto(vehical_id,status,last_service_date,number_plate_no);
            vehicleDtos.add(dto);
        }

        return vehicleDtos;
    }

    @Override
    public boolean update(VehicleDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE vehical SET status=?, last_service_date=?, number_plate_no=? WHERE vehical_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getVehicleStatus());
        pstm.setString(2, dto.getLastServiceDate());
        pstm.setString(3, dto.getNumberPlateNo());
        pstm.setString(4, dto.getVehicleId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;
    }

    @Override
    public boolean delete(String txtVehicleIdText) throws SQLException {
        DbConnection connection = DbConnection.getInstance();
        String sql = "DELETE FROM vehical WHERE vehical_id=?";
        PreparedStatement pstm = connection.getConnection().prepareStatement(sql);

        pstm.setString(1, txtVehicleIdText);

        boolean isDeleted = pstm.executeUpdate() > 0;
        return isDeleted;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public VehicleDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
