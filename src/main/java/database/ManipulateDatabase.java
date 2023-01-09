package database;

import java.sql.*;

public class ManipulateDatabase {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/test";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "";
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

        clearH2Database(conn);
        createDatabase(conn);
    }

    public static void createDatabase(Connection conn) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE REGISTRATION " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " +
                " last VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        conn.setAutoCommit(false);
        try {
            String addUser1 = "insert into registration (id,first, last, age) values ('1','Jake','Miller','27')";
            String addUser2 = "insert into registration (id, first, last, age) values ('2','Elizabeth','Miller','25')";
            PreparedStatement addUserStatement1 = conn.prepareStatement(addUser1);
            PreparedStatement addUserStatement2 = conn.prepareStatement(addUser2);
            addUserStatement1.executeUpdate();
            addUserStatement2.executeUpdate();

            //Jake and Elizabeth also want to add their kid Mikey to the account.
            //But if his information is input incorrectly,
            //then no one's information is processed:

            //String addUser3 = "insert into registration (first, last, age) values ('3','Mikey', 'Miller', 'TwentyFiver')";
            //PreparedStatement addUserStatement3 = conn.prepareStatement(addUser3);
            //addUserStatement3.executeUpdate();
            conn.commit();
        } catch(SQLException e) {
            System.out.println("The error was the following:" + e);
            conn.rollback();
            System.out.println("No accounts have been made. Transaction has been rolled back! The series of statements do not satisfy order of work.");
        }
        PreparedStatement dbContents = conn.prepareStatement(
                "SELECT * FROM registration");
        ResultSet rs = dbContents.executeQuery();
        while (rs.next()){
            System.out.println("ID: " + rs.getInt("id") +
                    "; First: " + rs.getString("first") +
                    "; Last: " + rs.getString("last") +
                    "; Age: " + rs.getInt("age"));
        }


    }

    public static void clearH2Database(Connection con) throws SQLException {
        String sql = "DROP ALL OBJECTS"; // H2 specific

        Statement statement = con.createStatement();
        statement.execute(sql);
    }
}
