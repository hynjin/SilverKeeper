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
 * This servlet receive Miband bluetooth status from silverApp to server.
 * JBDC update
 * 2017-05-13
 * 
 * successfully test
 * 2017-05-16
 * 
 * Servlet implementation class receiveBLEStatus
 */

@WebServlet("/receiveBLEStatus")
public class receiveBLEStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SilverDAO sDAO;
	
	Connect connect;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public receiveBLEStatus() {
        super();
        // TODO Auto-generated constructor stub
        
        sDAO =SilverDAO.getInstance();
        connect = new Connect();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HashMap<String,String> dataMap = new HashMap<String,String>();
		
		System.out.println("receiveBLEStatus");
		String silverID="";
		dataMap = connect.getData(request, response);
		String bleStatus=dataMap.get(silverID);
		if(bleStatus=="o")
		{
			sDAO.updateBLEStatus(silverID, true);
		}
		else
			sDAO.updateBLEStatus(silverID, false);
		/*String db = "update silverMiBandConn set BLESTATUS="+dataMap.get("BLESTATUS")+" where silverID="+dataMap.get("silverID");
		connect.sendData(db, request, response);*/
		
	}

}
