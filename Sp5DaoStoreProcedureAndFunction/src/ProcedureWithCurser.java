/*
 DELIMITER //
create procedure getEmp(IN salRange INT, OUT emp SYS_REFCURSER) BEGIN select sal into sal from Employee where eno = no; END//
DELEMITER; 
 */



public class ProcedureWithCurser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
