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
 * This servlet send connectRequest from server to silverApp.
 * 2017-05-13
 * 
 * just send keeper name when get request.
 * 
 * Servlet implementation class sendConnectRequest
 */
@WebServlet("/sendConnectRequest")
public class sendConnectRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connect connect;
    private HashMap<String,String> dataMap;
    
    public sendConnectRequest() {
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
		dataMap.put("keeperName","hong-hong");
		connect.setData(dataMap, request, response);
	}

}
