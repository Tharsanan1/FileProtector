import java.sql.*;

public class SqlHandler {
    Connection con;
    SqlHandler() throws SQLException {
        con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:cipher_test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        createTable();
    }
    public void createTable() throws SQLException {
        Statement statement = con.createStatement();
        ResultSet res = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
        if(!res.next()){
            Statement statement1 = con.createStatement();
            statement1.execute("CREATE TABLE user(id integer,name varchar(100),data varchar(5000),primary key(id));");
            statement1.close();
        }
        statement.close();
    }
    public void insertUser(String name, String data) throws SQLException{
        PreparedStatement stmt = con.prepareStatement
                ("insert into user (name,data) values(?,?)");
        stmt.setString(1,name);
        stmt.setString(2,data);
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Inserted Successfully");
        //Statement statement = con.createStatement();
        //statement.executeUpdate("INSERT INTO user (name,data) VALUES('"+name+"','"+data+"');");
        //statement.close();
    }
    public boolean checkUser(String name) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet res = statement.executeQuery("SELECT name From user WHERE name ='"+name+"';");
        if(res.next()){
            statement.close();
            return true;
        }
        else {
            statement.close();
            return false;
        }

    }
    public String getData(String userName) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet res = statement.executeQuery("SELECT data From user WHERE name ='"+userName+"';");
        if(res.next()){
            String s = res.getString("data");
            statement.close();
            return s;
        }
        else {
            statement.close();
            return "notFound";
        }
    }
}
