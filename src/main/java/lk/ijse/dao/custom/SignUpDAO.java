package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.SignUpDto;
import lk.ijse.entity.SignUp;

import java.sql.SQLException;

public interface SignUpDAO extends SuperDAO {
    boolean createAccount(final SignUp entity) throws SQLException;
}
