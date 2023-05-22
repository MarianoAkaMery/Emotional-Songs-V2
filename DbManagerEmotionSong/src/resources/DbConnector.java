package resources;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



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


public Boolean getAdminInfo(String emailGiven, String passwordGiven) {

    Connection conn = null;
    Statement stmt= null;
    String emailAdminList=null;
    String passwordAdminList=null;
    Boolean check=false;

    try {
    conn = DriverManager.getConnection(url, user, password);
    stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery( "SELECT * FROM public.\"AdminInfo\"");
    while ( rs.next() ) {
        emailAdminList = rs.getString("Email");
        passwordAdminList = rs.getString("Password");
        if (emailAdminList.equals(emailGiven) & passwordAdminList.equals(passwordGiven)){
            check= true;
        }
     }

    rs.close();
    stmt.close();
    conn.close();

    } catch (SQLException e){
    System.out.println(e.getMessage());
    }

    return check;
}

public String[] getUserInfo() {

    Connection conn = null;
    Statement stmt= null;
    String UserEmail=null;
    int index = 0;
    String[] EmailList= new String[3000];


    try {
    conn = DriverManager.getConnection(url, user, password);
    stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery( "SELECT * FROM public.\"Users\"");
    while ( rs.next() ) {
        UserEmail = rs.getString("Email");
        EmailList[index]= UserEmail;
        index=index+1;
        
     }

    rs.close();
    stmt.close();
    conn.close();

    } catch (SQLException e){
    System.out.println(e.getMessage());
    }

    return EmailList;
}
 


}