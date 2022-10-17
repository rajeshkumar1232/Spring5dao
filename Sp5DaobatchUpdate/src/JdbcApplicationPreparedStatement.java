import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JdbcApplicationPreparedStatement {

	public static void main(String[] args) throws Exception{
		
		Class.forName("com.mysql.cj.jdbc.MysqlDataSource");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/v1_schema","root","root");
		
		PreparedStatement pst = con.prepareStatement("insert into Employee value(?,?,?,?)");
		pst.setInt(1, 111);
		pst.setString(2, "TTT");
		pst.setFloat(3, 2000);
		pst.setString(4, "Ipac");
		
		pst.setInt(1, 222);
		pst.setString(2, "FFF");
		pst.setFloat(3, 3000);
		pst.setString(4, "Cpac");	
		
		/*ps.setInt(1, 333);
		ps.setString(2, "www");
		ps.setFloat(3, 4000);
		ps.setString(4, "Mpac");	
		
		ps.setInt(1, 444);
		ps.setString(2, "qqq");
		ps.setFloat(3, 3500);
		ps.setString(4, "Dpac");	*/
		
		pst.executeBatch();
		
		
		pst.close();
		con.close();
	}
}
