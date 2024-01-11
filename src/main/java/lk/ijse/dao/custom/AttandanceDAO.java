package lk.ijse.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.AttandanceDto;
import lk.ijse.dto.tm.AttandanceTm;
import lk.ijse.entity.Attandance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface AttandanceDAO extends SuperDAO {
    boolean addAttandance(Attandance entity) throws SQLException;
    ArrayList<Attandance> getAttandanceDetails() throws SQLException;
    boolean isExist(LocalDate date) throws SQLException;
    ObservableList<AttandanceTm> getAttendanceOfDay(String date) throws SQLException;
}
