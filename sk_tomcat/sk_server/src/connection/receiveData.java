package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.silver.SilverDAO;

/**
 * created by hyunjin
 * connection of server
 * This servlet receive data from silverApp to server.
 * 
 * 
 * Servlet implementation class receiveData
 */

@WebServlet("/receiveData")
public class receiveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	       
	private SilverDAO sDAO;
	private Connect connect;
	private HashMap<String,String> dataMap;
	private String db;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public receiveData() {
        super();
        // TODO Auto-generated constructor stub
        
        sDAO = new SilverDAO();
        connect = new Connect();

		dataMap = new HashMap<String,String>();
		db = "";
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("!!receiveData");
		dataMap = connect.getData(request, response);
		System.out.println("--"+connect.getType());
		
		switch(connect.getType())
		{
		case "sendBLE":
			db = "update silverMiBandConn set BLESTATUS="+dataMap.get("BLESTATUS")+" where silverID="+dataMap.get("silverID");
			connect.sendData(db, request, response);
			break;
		case "sendSilverData":
			db = "insert into silverData("+connect.getKey()+") values("+connect.getValue()+")";
			connect.sendData(db, request, response);
			break;
		case "sendSilverToken":
			break;
		case "sendHomeComing":
			db = "update silverHomeComingStatus set homeComing="+connect.getKey()+" where silverID="+connect.getValue();
			connect.sendData(db, request, response);
			break;
		case "sendIdentifyNumber":
			break;
		case "sendKeeperName":
			break;
		case "sendKeeperToken":
			db = "insert into keeperData("+connect.getKey()+") values("+connect.getValue()+")";
	    	connect.sendData(db, request, response);
			System.out.println("getKeeperToken :"+db);
			break;
		case "sendRaspIP":
			break;
		case "sendRole":
			break;
			
		}
		
	}
}

