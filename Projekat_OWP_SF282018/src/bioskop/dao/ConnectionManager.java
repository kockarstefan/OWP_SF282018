package bioskop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;
import org.sqlite.JDBC;

public class ConnectionManager extends JDBC {
	
	private static final String DATABASE_NAME = "database.db";

	private static final String FILE_SEPARATOR = System.getProperty("file.separator"); 
	
	private static final String WINDOWS_PATH = "D:" + FILE_SEPARATOR + "Users" + FILE_SEPARATOR + "Stef" + FILE_SEPARATOR + DATABASE_NAME;

	private static final String PATH = WINDOWS_PATH;	

	private static Connection connection;
	
	public static void open() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + PATH);
			System.out.println("Connected to the db.");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return connection;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void close() {
		try {
			connection.close();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}