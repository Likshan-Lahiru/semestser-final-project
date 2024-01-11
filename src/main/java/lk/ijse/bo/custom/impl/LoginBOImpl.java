package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.custom.impl.LoginDAOImpl;
import lk.ijse.dto.LoginDto;
import lk.ijse.dto.SignUpDto;
import lk.ijse.entity.Login;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    @Override
    public boolean checkCredentianl(LoginDto dto) throws SQLException {
       return new LoginDAOImpl().checkCredentianl(
               new Login(
                dto.getPassword(),
                dto.getPassword()
               ));

    }

    @Override
    public SignUpDto getName(String nameText) throws SQLException {
        return new LoginDAOImpl().getName(nameText);
    }
}
