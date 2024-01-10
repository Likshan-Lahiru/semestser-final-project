package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.LoginDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.LoginDto;
import lk.ijse.dto.SignUpDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean checkCredentianl(LoginDto dto) throws SQLException {
        String sql = "select * from user where user_name = ? and password = ?";
        ResultSet resultSet =  SQLUtil.execute(sql,dto.getUsername(),dto.getPassword());
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SignUpDto getName(String nameText) throws SQLException {
        String sql = "select * from user where user_name = ?";
        ResultSet resultSet = SQLUtil.execute(sql,nameText);
        SignUpDto dto = null;
        if (resultSet.next()) {
            dto = new SignUpDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
        }

        return dto;
    }

}
