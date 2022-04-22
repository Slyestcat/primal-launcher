package net.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class forumPosts {

    public static Connection conn;

    static {
        try {
            conn = sqlConfiguration.getDataSource("primal_forum").getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getPosts() throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "SELECT title,starter_name,tid FROM `forums_topics` WHERE approved=1 ORDER BY start_date DESC LIMIT 5";
        ResultSet rs = stmt.executeQuery(query);
        return rs;

    }

    public static ResultSet getAnnouncements() throws SQLException {
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM forums_topics INNER JOIN forums_posts ON forums_topics.tid = forums_posts.topic_id WHERE forum_id='7' AND author_name='Primal' OR forum_id='7' AND author_name='Sly' OR forum_id='7' AND author_name='Stars' ORDER BY tid DESC LIMIT 2";
        ResultSet rs = stmt.executeQuery(query);
        return rs;

    }

}
