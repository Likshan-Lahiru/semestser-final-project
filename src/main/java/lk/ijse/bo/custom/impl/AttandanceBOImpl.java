package lk.ijse.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.bo.custom.AttandanceBO;
import lk.ijse.dao.custom.impl.AttadanceDAOImpl;
import lk.ijse.dto.AttandanceDto;
import lk.ijse.dto.tm.AttandanceTm;
import lk.ijse.entity.Attandance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AttandanceBOImpl implements AttandanceBO {
    @Override
    public boolean addAttandance(AttandanceDto dto) throws SQLException {
        new AttadanceDAOImpl().addAttandance(new Attandance(
                dto.getEmployeeId(),
                dto.getEmployeeName(),
                dto.getDate(),
                dto.getNIC(),
                dto.getStatus()
        ));
        return false;
    }

    @Override
    public ArrayList<AttandanceDto> getAttandanceDetails() throws SQLException {
       ArrayList<Attandance> attandances  =   new AttadanceDAOImpl().getAttandanceDetails();
       ArrayList<AttandanceDto> dto = new ArrayList<>();
       for (Attandance attandance: attandances){
          AttandanceDto attandanceDto = new AttandanceDto(
                   attandance.getEmployeeId(),
                   attandance.getEmployeeName(),
                   attandance.getDate(),
                   attandance.getNIC(),
                   attandance.getStatus()
           );
           dto.add(attandanceDto);
       }

       return dto;
    }

    @Override
    public boolean isExist(LocalDate date) throws SQLException {
        return false;
    }

    @Override
    public ObservableList<AttandanceTm> getAttendanceOfDay(String date) throws SQLException {
        return null;
    }
}
