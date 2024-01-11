package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SignUpBO;
import lk.ijse.dao.custom.LoginDAO;
import lk.ijse.dao.custom.SignUpDAO;
import lk.ijse.dao.custom.impl.SignUpDAOImpl;
import lk.ijse.dao.factory.DAOFactory;
import lk.ijse.dto.SignUpDto;
import lk.ijse.entity.SignUp;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {
    SignUpDAO signUpDAO= (SignUpDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SIGNUP);
    @Override
    public boolean createAccount(SignUpDto dto) throws SQLException {
       return signUpDAO.createAccount(
                new SignUp(
                        dto.getFirstName(),
                        dto.getScondName(),
                        dto.getUserName(),
                        dto.getPasssword(),
                        dto.getEmail()
                )
        );

    }
}
