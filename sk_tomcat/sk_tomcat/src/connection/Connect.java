package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.keeper.KeeperDAO;
import conn.silver.SilverDAO;

/**
 * created by hyunjin
 * connection of server
 * This class connection with server.
 * 2017-05-16
 * 
 * successfully test
 * 2017-05-16
 * 
 */

public class Connect {
	
    /*private String dbinfo;
	private String ID;
	private String pw;
	private String key;
	private String value;*/
	private HashMap<String, String> dataMap;
	/*private static SilverDAO sDAO=SilverDAO.getInstance();
	private static KeeperDAO kDAO=KeeperDAO.getInstance();*/

	
	public Connect()
	{
		dataMap = new HashMap<String, String>();
       /* try{
        	Class.forName("com.mysql.jdbc.Driver");
        	dbinfo = "jdbc:oracle:thin:@localhost:1521:xe";
        	ID = "tybaek";
        	pw = "ws1541235";
        	key = "";
        	value = "";
        	
        }catch(ClassNotFoundException e)
        {
        	e.printStackTrace();
        }*/
	}
	 
	//receive data from client
	public HashMap<String,String> getData(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String name = (String) parameterNames.nextElement();
			dataMap.put(name, request.getParameter(name));
			System.out.println(name + "=" + request.getParameter(name));
        } 
		return dataMap;
	}
	
	//send data to client
	public void setData( HashMap<String,String> map,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().append(MaptoString(map));
	}

	//string data format
	public String toString()
	{
		String str = "";
		for( String key : dataMap.keySet() ){
            str = str + String.format("%s=%s,", key, dataMap.get(key));
        }
		return str;
	}

	//data format for send to client
	public String MaptoString(HashMap<String,String> map)
	{
		String str = "";
		for( String key : map.keySet() ){
            str = str + String.format("%s=%s&", key, map.get(key));
        }
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : map.keySet()) 
		{
			if (stringBuilder.length() > 0) 
		    {
				stringBuilder.append("&");
		    }
			stringBuilder.append(key);
			stringBuilder.append("=");
			stringBuilder.append(map.get(key));
		}
		return stringBuilder.toString();
	}
	
	//extract key from client data
	public String getKey()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : dataMap.keySet()) 
		{
			if (stringBuilder.length() > 0) 
		    {
				stringBuilder.append(",");
		    }
			stringBuilder.append(key);
		}
		return stringBuilder.toString();
	}
	
	//extract value from client data
	public String getValue()
	{

		StringBuilder stringBuilder = new StringBuilder();
		for (String key : dataMap.keySet()) 
		{
			if (stringBuilder.length() > 0) 
		    {
				stringBuilder.append(",");
		    }
			stringBuilder.append(dataMap.get(key));
		}
		return stringBuilder.toString();
	}
	
	//send data to DB
	public void sendData(String db ,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		
		/*try{
        	
        	java.sql.Connection conn = java.sql.DriverManager.getConnection(dbinfo,ID,pw);
        	java.sql.Statement stmt = conn.createStatement();
        	
        	System.out.println(db);
        	stmt.executeUpdate(db);
        	conn.close();
        	
        }catch(java.sql.SQLException e)
        {
        	e.printStackTrace();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }*/
        
		PrintWriter out = response.getWriter();
		System.out.println(db);
		out.print("server successfully send the Data");
	}
	
	//receive data from DB
	public String receiveData(String db ,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String str = "";
		/*try{
        	
        	java.sql.Connection conn = java.sql.DriverManager.getConnection(dbinfo,ID,pw);
        	java.sql.Statement stmt = conn.createStatement();
        	
        	System.out.println(db);
        	ResultSet rs = stmt.executeQuery(db);
        	str = rs.getString("");
        	conn.close();
        	
        }catch(java.sql.SQLException e)
        {
        	e.printStackTrace();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }*/
        
		PrintWriter out = response.getWriter();
		System.out.println(db);
		out.print("server successfully receive the Data");
		
		return str;
	}
}

