package lk.ijse.bo.custom;

import lk.ijse.dto.SignUpDto;

import java.sql.SQLException;

public interface SignUpBO {
    boolean createAccount(SignUpDto dto) throws SQLException;
}
