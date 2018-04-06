/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Kayland's Surface
 */
public class SqliteDB {

    private Connection c;
    private Statement stmt;

    private SqliteDB() {
        c = null;
        stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test9.db");
            System.out.println("DB FILE: " + (new File("test9.db").getAbsolutePath()));
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS MESSAGE "
                    + "(NAME TEXT NOT NULL, "
                    + "TIME CHAR(250), "
                    + "MESSAGE CHAR(250))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static SqliteDB getInstance() {
        return SqliteDBHolder.INSTANCE;
    }

    private static class SqliteDBHolder {

        private static final SqliteDB INSTANCE = new SqliteDB();
    }

    public void insert(String insertion) {
        try {
            stmt = c.createStatement();
            String sql = insertion;
            stmt.executeUpdate(sql);
            stmt.close();
            // c.commit();
        } catch (Exception e) {
            System.err.println("bitch you fucked up");
            System.err.println(e);
        }
    }

    public ArrayList<MessageData> getMessages() {
        ArrayList<MessageData> messages = new ArrayList<>();

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MESSAGE;");
            while (rs.next()) {
                MessageData data = new MessageData();
                //Timestamp timestamp = rs.getTimestamp("TIME");
                data.name = rs.getString("NAME");
                //data.timestamp = timestamp.toLocalDateTime();
                data.message = rs.getString("MESSAGE");
                messages.add(data);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("well that failed");
            System.err.println(e);
        }
        return messages;
    }

    public void close() {
        try {
            c.close();
        } catch (Exception e) {
            System.err.println("suck it, also it didnt close");
            System.err.println(e);
        }
    }
}
