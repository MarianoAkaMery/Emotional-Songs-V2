package resources;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    
private final String url = "jdbc:postgresql://localhost/postgres";
private final String user = "postgres";
private final String password = "ciao1234";


public Connection connect() {

Connection conn = null;
try {
conn = DriverManager.getConnection(url, user, password); System.out.println("Connected to the PostgreSQL server successfully.");

} catch (SQLException e){
System.out.println(e.getMessage());
}
return conn;
}
}
