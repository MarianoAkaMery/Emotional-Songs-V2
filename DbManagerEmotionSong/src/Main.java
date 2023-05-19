import resources.DbConnector;
import pages.LoginPage;


public class Main{



public static void main(String[] args) {


DbConnector app= new DbConnector();
app.connect();
new LoginPage();


}
}
