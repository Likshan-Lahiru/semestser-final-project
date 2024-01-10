package lk.ijse.dao.custom;

import lk.ijse.dto.SignUpDto;

import java.sql.SQLException;

public interface SignUpDAO {
    boolean createAccount(final SignUpDto dto) throws SQLException;
}
