package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.LoginDto;
import lk.ijse.dto.SignUpDto;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean checkCredentianl(LoginDto dto) throws SQLException;
    SignUpDto getName(String nameText) throws SQLException;
}
