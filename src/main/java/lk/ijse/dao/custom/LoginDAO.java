package lk.ijse.dao.custom;

import lk.ijse.dto.LoginDto;
import lk.ijse.dto.SignUpDto;
import lk.ijse.entity.Login;

import java.sql.SQLException;

public interface LoginDAO {
    public boolean checkCredentianl(Login entity) throws SQLException;
    public  SignUpDto getName(String nameText) throws SQLException;
}
