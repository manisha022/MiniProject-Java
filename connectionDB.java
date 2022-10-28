package simpleBankingApplication1;
import java.sql.*;

public class connectionDB 
{
    public Connection c() throws Exception
    {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Manisha@20");
    	return con;
    }
}
