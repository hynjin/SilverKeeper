package connection;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by hyunjin
 * connection of server
 * This servlet send silverID from server to silverApp.
 * 2017-05-13
 * 
 * just send silverID when get request.
 * 
 * Servlet implementation class sendSilverID
 */
@WebServlet("/sendSilverID")
public class sendSilverID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connect connect;
    private HashMap<String,String> dataMap;
    
    public sendSilverID() {
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
		dataMap.put("silverID","S0001");
		connect.setData(dataMap, request, response);
	}

}
