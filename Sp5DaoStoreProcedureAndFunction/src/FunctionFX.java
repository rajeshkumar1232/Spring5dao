import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

import com.mysql.jdbc.CallableStatement;

/*
DELIMITER $$
CREATE FUNCTION getAvg(no1 INT, no2 INT) RETURNS INT
BEGIN
DECLARE num1 INT;
DECLARE num2 INT;
  select sal into num1 from Employee where eno = no1;
  select sal into num2 from Employee where eno = no2;
  RETURN (num1+num2)/2;
END$$
DELIMITER ; 
  
  
 */
public class FunctionFX {
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.MysqlDataSource");
		Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/v1_schema","root","root");
		java.sql.CallableStatement cst =  con.prepareCall("{? = call getAvg(?,?)}");
		cst.setInt(2,555);
		cst.setInt(3,666);
		cst.registerOutParameter(1, Types.INTEGER);
		cst.execute();
		System.out.println(cst.getInt(1));
		cst.close();
		con.close();
		
		
	}

}
