package connection;

import java.io.IOException;
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
 * This servlet receive Silver HomeComing status from silverApp to server.
 * 2017-05-13
 * 
 * successfully test
 * 2017-05-16
 * 
 * Servlet implementation class receiveHomeComing
 */

@WebServlet("/receiveHomeComing")
public class receiveHomeComing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private SilverDAO sDAO;
	private Connect connect;
	private HashMap<String,String> dataMap;
    public receiveHomeComing() {
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
		System.out.println("receiveHomeComing");
		
		connect.getData(request, response);
		String silverID=sDAO.selectSilverID("androidID");
		
		dataMap = connect.getData(request, response);
		String bleStatus=dataMap.get(silverID);
		if(bleStatus=="o")
		{
			sDAO.updateBLEStatus(silverID, true);
		}
		else
			sDAO.updateBLEStatus(silverID, false);
		/*String db = "update silverHomeComingStatus set homeComing="+connect.getKey()+" where silverID="+connect.getValue();
		
		connect.sendData(db, request, response);*/
		
       
	}
}

