package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SignUpBO;
import lk.ijse.dao.custom.impl.SignUpDAOImpl;
import lk.ijse.dto.SignUpDto;
import lk.ijse.entity.SignUp;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {
    @Override
    public boolean createAccount(SignUpDto dto) throws SQLException {
        new SignUpDAOImpl().createAccount(
                new SignUp(
                        dto.getFirstName(),
                        dto.getScondName(),
                        dto.getUserName(),
                        dto.getPasssword(),
                        dto.getEmail()
                )
        );
        return false;
    }
}
