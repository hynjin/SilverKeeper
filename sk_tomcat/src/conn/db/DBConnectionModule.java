package conn.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionModule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9018086771909664711L;
	private Connection conn;
	private String url;
	private String dbId;
	private String dbPwd;
	private static DBConnectionModule obj = new DBConnectionModule();

	public static DBConnectionModule getInstance(){
		return obj;
	}
	
	private DBConnectionModule() {
		// TODO Auto-generated constructor stub
		this("jdbc:oracle:thin:@127.0.0.1:1521:XE","tybaek","ws1541235");//"jdbc:mysql://localhost:3306/dbTest","root","root","root","root");
	}

	private DBConnectionModule(String url, String dbId, String dbPwd) {
		//super();
		this.url = url;
		this.dbId = dbId;
		this.dbPwd = dbPwd;
		connect();
	}
	private void connect(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, dbId, dbPwd);
		}
		catch(ClassNotFoundException ce){
			System.out.println(ce.getMessage());
			System.out.println("클래스없음\n");
		}
		catch(SQLException se){
			System.out.println(se.getMessage());
			System.out.println("SQL 오류\n");
		}
	}
	public void disconnect(){
		try{
			if(conn != null)
				conn.close();
		}
		catch(SQLException se){
			System.out.println(se.getMessage());
		}
	}

	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public static void main(String[] args) {
		System.out.println("Test Over");
	}
}