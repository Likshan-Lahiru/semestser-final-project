package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SignUpDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SignUpDto;
import lk.ijse.entity.SignUp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpDAOImpl implements SignUpDAO {
    @Override
    public boolean createAccount(SignUp entity) throws SQLException {
        String sql = "INSERT INTO user entity(?, ?, ?, ?, ?)";
       return SQLUtil.execute(sql,
                entity.getUserName(),
                entity.getFirstName(),
                entity.getScondName(),
                entity.getEmail(),
                entity.getPasssword()
        );
    }
}
