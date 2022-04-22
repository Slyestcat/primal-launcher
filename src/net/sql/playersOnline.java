package net.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class playersOnline {

    public static int getOnline() throws SQLException {
        int playersOnline = 0;
        Connection conn = sqlConfiguration.getDataSource("primal").getConnection();
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM `statistics_online` WHERE 1";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            playersOnline = rs.getInt("online");
        }
        return playersOnline;
    }
}
