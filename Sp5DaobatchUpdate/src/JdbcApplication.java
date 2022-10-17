import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcApplication {

	public static void main(String[] args) throws Exception{
		
		Class.forName("com.mysql.cj.jdbc.MysqlDataSource");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/v1_schema","root","root");
		Statement st = con.createStatement();
		st.addBatch("insert into Employee value(888,'www',60000,'bksc')");
		st.addBatch("delete from Employee where eno = 111");
		int[] rowcount = st.executeBatch();
		for(int i :rowcount) {
			System.out.println(i);
		}
		st.close();
		con.close();
	}
}
