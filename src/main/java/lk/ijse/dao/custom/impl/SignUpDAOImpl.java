package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.SignUpDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SignUpDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpDAOImpl implements SignUpDAO {
    @Override
    public boolean createAccount(SignUpDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getUserName());
        pstm.setString(2, dto.getFirstName());
        pstm.setString(3, dto.getScondName());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getPasssword());

        boolean isCreateAccount = pstm.executeUpdate()>0;
        return isCreateAccount;
    }
}
