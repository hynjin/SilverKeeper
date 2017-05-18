package connection;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.silver.SilverDAO;
import conn.silver.vo.SilverVO;

/**
 * created by hyunjin
 * connection of server
 * This servlet send silverData from server to App.
 * 2017-05-13
 * 
 * just send silverData when get request.
 * 
 * Servlet implementation class sendSilverData
 */
@WebServlet("/sendSilverData")
public class sendSilverData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connect connect;
    private HashMap<String,String> dataMap;
    
    public sendSilverData() {
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
		
		SilverDAO sDAO=SilverDAO.getInstance();
		String androidID="";//실버 Android ID 받아오는 연산 필요.
		String silverID=sDAO.selectSilverID(androidID);
		SilverVO[] vo=sDAO.selectSilverData(silverID);
		
		
		dataMap.put("heartRate",vo[0].getHeartRate()+"");
		dataMap.put("walkCount",vo[0].getWalkCount()+"");
		dataMap.put("currentTime",vo[0].getCurrentTime().toString());
		
		connect.setData(dataMap, request, response);
	}

}
