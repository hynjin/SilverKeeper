package connection;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.keeper.KeeperDAO;

/**
 * created by hyunjin
 * connection of server
 * This servlet receive keeperToken keeperApp to server.
 * 2017-05-13
 * 
 * successfully test
 * 2017-05-16
 * 
 * Servlet implementation class receiveKeeperToken
 */
@WebServlet("/receiveKeeperToken")
public class receiveKeeperToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connect connect;
    private HashMap<String,String> dataMap;
    private KeeperDAO kDAO=new KeeperDAO();
    public receiveKeeperToken() {
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
		dataMap = connect.getData(request, response);
		/*String db = "insert into keeperData("+connect.getKey()+") values("+connect.getValue()+")";
    	connect.sendData(db, request, response);*/
		
		String key=connect.getKey();
		String androidID=dataMap.get("androidID");
    	String keeperID=kDAO.selectKeeperID(androidID);
		String keeperToken=dataMap.get("keeperToken");
		kDAO.updateKeeperToken(keeperID, keeperToken);
		
	}

}
