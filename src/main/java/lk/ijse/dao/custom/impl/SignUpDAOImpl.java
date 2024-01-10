package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SignUpDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SignUpDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpDAOImpl implements SignUpDAO {
    @Override
    public boolean createAccount(SignUpDto dto) throws SQLException {
        String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?)";
       return SQLUtil.execute(sql,
                dto.getUserName(),
                dto.getFirstName(),
                dto.getScondName(),
                dto.getEmail(),
                dto.getPasssword()
        );
    }
}
