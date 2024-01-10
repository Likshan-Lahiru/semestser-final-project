package lk.ijse.dao.custom;

import lk.ijse.dto.LoginDto;
import lk.ijse.dto.SignUpDto;
import java.sql.SQLException;

public interface LoginDAO {
    public boolean checkCredentianl(LoginDto dto) throws SQLException;
    public  SignUpDto getName(String nameText) throws SQLException;
}
