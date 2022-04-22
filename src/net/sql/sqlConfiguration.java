package net.sql;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.SQLException;

public class sqlConfiguration {
    public static String sqlUsername = "primal_reader";
    public static String sqlPassword = "5LHos2YkxI3lRsHS";
    public static String sqlHost = "157.245.143.62";

    public static MysqlDataSource getDataSource(String sqlDatabase) throws SQLException {
        MysqlDataSource gameSource = new MysqlDataSource();
        gameSource.setUser(sqlUsername);
        gameSource.setPassword(sqlPassword);
        gameSource.setServerName(sqlHost);
        gameSource.setDatabaseName(sqlDatabase);

        return gameSource;
    }

}
