package resources;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        UserEmail = rs.getString("UserEmail");
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

public Boolean deleteUser(String emailtodelete) {

    Connection conn = null;
    Statement stmt= null;
    Boolean success= false;


    try {
    conn = DriverManager.getConnection(url, user, password);
    stmt = conn.createStatement();
    String query = "DELETE FROM public.\"Users\"WHERE \"UserEmail\" = ?;";
    PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setString(1, emailtodelete);
    pstmt.executeUpdate();
    
    stmt.close();
    conn.close();
    success= true;

    } catch (SQLException e){
    System.out.println(e.getMessage());
    success= false;
    }

    return success;




}

public String[] customQuery(String query) {

    Connection conn = null;
    Statement stmt= null;
    String UserEmail=null;
    int index = 0;
    String[] EmailList= new String[3000];


    try {
    conn = DriverManager.getConnection(url, user, password);
    stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query);
    while ( rs.next() ) {
        UserEmail = rs.getString("UserEmail");
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

public String[] getSingleUserInfo(String email) {

    Connection conn = null;
    Statement stmt= null;
    String UserId=null;
    String UserEmail=null;
    String UserName=null;
    String UserLastUse=null;
    String[] User= new String[4];


    try {
    conn = DriverManager.getConnection(url, user, password);
    stmt = conn.createStatement();

    String query = "SELECT * FROM public.\"Users\" WHERE \"UserEmail\" = ?;";
    PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setString(1, email);
    ResultSet rs = pstmt.executeQuery();

    while ( rs.next() ) {
        try{        
            UserId = rs.getString("UserID");
        }
        catch(Exception e){
            
        }
        try{        
            UserEmail = rs.getString("UserEmail");
        }
        catch(Exception e){
            
        }
        try{        
            UserName = rs.getString("UserName");

        }
        catch(Exception e){
            
        }
        try{        
            UserLastUse = rs.getString("UserLastUse");
        }
        catch(Exception e){
            
        }

        User[0]= UserId;
        User[1]= UserEmail;
        User[2]= UserName;
        User[3]= UserLastUse;

        
     }

    rs.close();
    stmt.close();
    conn.close();

    } catch (SQLException e){
    System.out.println(e.getMessage());
    }

    return User;
}
 


}