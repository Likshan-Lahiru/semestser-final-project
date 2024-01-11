package lk.ijse.bo;

import javafx.collections.ObservableList;
import lk.ijse.dto.AttandanceDto;
import lk.ijse.dto.tm.AttandanceTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AttandanceBO {
    boolean addAttandance(AttandanceDto dto) throws SQLException;
    List<AttandanceDto> getAttandanceDetails() throws SQLException;
    boolean isExist(LocalDate date) throws SQLException;
    ObservableList<AttandanceTm> getAttendanceOfDay(String date) throws SQLException;
}
