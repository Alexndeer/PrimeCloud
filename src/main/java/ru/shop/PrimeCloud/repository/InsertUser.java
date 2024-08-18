package ru.shop.PrimeCloud.repository;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Types;

public class InsertUser extends SqlUpdate {

    public InsertUser(DataSource dataSource){
        super(dataSource,"INSERT INTO users (email, password, phone_number, name, role) " +
                "VALUES (:email, :password, :phone_number, :name, :role)");
        super.declareParameter(new SqlParameter("email", Types.VARCHAR));
        super.declareParameter(new SqlParameter("password", Types.VARCHAR));
        super.declareParameter(new SqlParameter("phone_number", Types.VARCHAR));
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("role", Types.TINYINT));
        super.setGeneratedKeysColumnNames("id");
        super.setReturnGeneratedKeys(true);
    }
}
