/**
 * ClassName :- FiloReader
 * Initializes Fillo to retrive the data from excel using sql commands
 * 
 * Created By 	:- Umesh Joshi/Viral Singh
 * Created Date :- 17-Nov 2018
 * Modified By 	:- 
 * Modified Date:- 
 *
 */

package cetera.Automation.testUtils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FiloReader {
	private Fillo filo = new Fillo();
	private static Connection connection;
	private Recordset record;
	
		
	public Connection createConnection() {
		if(connection==null) {
			try {
				connection = filo.getConnection(System.getProperty("user.dir")+"/resources/excelFiles/Data.xlsx");
			} catch (FilloException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	
	public Recordset executeQuery(String strQuery) {
		try {
			record = connection.executeQuery(strQuery);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return record;
	}
	
	public void setStatus(String testCaseId,String status) throws FilloException{
		Recordset record=connection.executeQuery("Select * from Results where TC_ID='"+testCaseId+"'");
		if(record.getCount()>0) {
			record.next();
			connection.executeQuery("Update Results set Status='"+status+"' where TC_ID='"+testCaseId+"'");
		}else {
			record.next();
			connection.executeQuery("Insert into Results('"+testCaseId+"','description','"+status+"')");
		}
	}
	
	
}
