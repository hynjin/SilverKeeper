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
 * This servlet receive silverToken from silverApp to server.
 * 2017-05-13
 * 
 * just receive silverToken
 * successfully test
 * 2017-05-16
 * 
 * Servlet implementation class receiveSilverToken
 */
@WebServlet("/receiveSilverToken")
public class receiveSilverToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connect connect;
    private HashMap<String,String> dataMap;
    
    public receiveSilverToken() {
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
		String key=connect.getKey();
		String androidID=dataMap.get("androidID");
		SilverDAO sDAO=SilverDAO.getInstance();
		String silverID=sDAO.selectSilverID(androidID);
		String sToken=dataMap.get("silverToken");
		sDAO.updateSilverToken(silverID, sToken);
		//String db = "insert into silverData("+connect.getKey()+") values("+connect.getValue()+")";
    	//connect.sendData(db, request, response);
		
	}

}
