import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

import java.sql.CallableStatement;

/*
 Create  or replace procedure getSal(no IN number, salOUT float) 
 AS
 BEGIN
 select sal into sal from Employee where eno =eno;
 END getSal/
 
 mysql:
DELIMITER //
create procedure getSal(IN no INT, OUT sal FLOAT) BEGIN select sal into sal from Employee where eno = no; END//
DELEMITER;
  */


public class ProcedureEx {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.MysqlDataSource");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/v1_schema","root","root");
		
		CallableStatement cst =  con.prepareCall("{call getsal(?,?)}");
		cst.setInt(1, 555);
		cst.registerOutParameter(2, Types.FLOAT);
		cst.execute();
		System.out.println("salary : "+cst.getFloat(2));
		cst.close();
		con.close();
	}

}
