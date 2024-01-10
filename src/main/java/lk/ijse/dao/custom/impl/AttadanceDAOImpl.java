package lk.ijse.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.AttandanceDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.AttandanceDto;
import lk.ijse.dto.tm.AttandanceTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttadanceDAOImpl implements AttandanceDAO {
    @Override
    public boolean addAttandance(AttandanceDto dto) throws SQLException {
        String sql = "INSERT INTO employee_attandance VALUES(?,?,?,?,?)";
        return SQLUtil.execute(sql,
                dto.getEmployeeId(),
                dto.getEmployeeName(),
                dto.getDate(),
                dto.getNIC(),
                dto.getStatus()
        );
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO employee_attandance VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getEmployeeId());
        pstm.setString(2, dto.getEmployeeName());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getNIC());
        pstm.setString(5, dto.getStatus());

        return pstm.executeUpdate() > 0;*/
    }

    @Override
    public  List<AttandanceDto> getAttandanceDetails() throws SQLException {
        String sql = "SELECT * FROM employee_attandance";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<AttandanceDto> attandanceDto = new ArrayList<>();
        while (resultSet.next()){
            AttandanceDto dto = new AttandanceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            attandanceDto.add(dto);
        }
        return attandanceDto;
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee_attandance";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<AttandanceDto> attandanceDto = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String employee_id = resultSet.getString(1);
            String employee_name = resultSet.getString(2);
            String NIC = resultSet.getString(3);
            String date = resultSet.getString(4);
            String status = resultSet.getString(5);

            AttandanceDto dto = new AttandanceDto(employee_id, employee_name, NIC, date, status);
            attandanceDto.add(dto);
        }

        return attandanceDto;*/
    }
    @Override
    public boolean isExist(LocalDate date) throws SQLException {
        String sql = "SELECT employee_id FROM employee_attandance WHERE date=?";
        ResultSet resultSet = SQLUtil.execute(sql,date);
        return resultSet.next();
        /*Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT employee_id FROM employee_attandance WHERE date=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, date.toString());

        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next();*/
    }
    @Override
    public ObservableList<AttandanceTm> getAttendanceOfDay(String date) throws SQLException {
        String sql = "SELECT * FROM employee_attandance WHERE date = ?";
        ObservableList<AttandanceTm> tmList = FXCollections.observableArrayList();
        ResultSet resultSet = SQLUtil.execute(sql);
        while(resultSet.next()){
            final var add = tmList.add(new AttandanceTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return tmList;
        /*Connection connection = DbConnection.getInstance().getConnection();


        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, date);

        ResultSet resultSet =pstm.executeQuery();
        ObservableList<AttandanceTm> tmList = FXCollections.observableArrayList();

        */

    }
}
