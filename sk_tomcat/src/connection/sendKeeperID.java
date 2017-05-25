package connection;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.keeper.KeeperDAO;
import conn.silver.SilverDAO;

/**
 * created by hyunjin
 * connection of server
 * This servlet send keeperID from server to keeperApp.
 * 2017-05-13
 * 
 * just send KeeperID when get request.
 * 
 * Servlet implementation class sendKeeperID
 */
@WebServlet("/sendKeeperID")
public class sendKeeperID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connect connect;
    private HashMap<String,String> dataMap;
    
    public sendKeeperID() {
        super();
        // TODO Auto-generated constructor stub
        connect = new Connect();
        dataMap = new HashMap<String,String>();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		KeeperDAO kDAO =new KeeperDAO();
		dataMap=connect.getData(request, response);
		
		String androidID=dataMap.get("androidID");
		
		dataMap.put("keeperID",kDAO.selectKeeperID(androidID));
		
		connect.setData(dataMap, request, response);
		
	}

}
