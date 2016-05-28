package Utilities;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;

//Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");

public class DBReader 
{
	private Connection connection;
	//private SQLServerDriver connection;
	private Statement statement;
	private ResultSet resultSet;
		
	public DBReader(String ip, String dbName, String userName, String password) throws Exception  
	{
		String URL = "jdbc:sqlserver://" + ip + ";databaseName=" + dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		this.connection = DriverManager.getConnection(URL, userName, password);
		this.statement = connection.createStatement();
	}
	
	public LinkedList<LinkedList<String>> query(String query) throws Exception  
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("before query");
		this.resultSet = statement.executeQuery(query);
		LinkedList<LinkedList<String>> retValue = new LinkedList<LinkedList<String>>();
		
		ResultSetMetaData meta = resultSet.getMetaData();
		int columnCount = meta.getColumnCount();
		
		while(this.resultSet.next())
		{
			LinkedList<String> subsetRetValue = new LinkedList<String>();
			for (int i = 1; i <= columnCount; i++)
			{
				subsetRetValue.add(this.resultSet.getObject(i).toString());
				i++;
			}
			retValue.add(subsetRetValue);
		}
		/*
		for (int i =0; i < retValue.size(); i++)
		{
			LinkedList<String> temp = retValue.get(i);
			for (int j=0; j < temp.size(); j++)
			{
				System.out.print(temp.get(j) + " ");
			}
			System.out.println();
		}
		*/
		return retValue;
	}

}
