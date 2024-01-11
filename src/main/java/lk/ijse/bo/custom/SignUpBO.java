package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SignUpDto;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    boolean createAccount(SignUpDto dto) throws SQLException;
}
