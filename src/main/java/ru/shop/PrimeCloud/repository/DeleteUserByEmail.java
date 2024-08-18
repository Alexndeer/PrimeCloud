package ru.shop.PrimeCloud.repository;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class DeleteUserByEmail extends SqlUpdate {

    public DeleteUserByEmail(DataSource dataSource) {
        super(dataSource, "DELETE FROM users WHERE email = :email");
        super.declareParameter(new SqlParameter("email", Types.VARCHAR));
    }
}
