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
import oracle.sql.DATE;

/**
 * created by hyunjin
 * connection of server
 * This servlet receive SilverData from silverApp to server.
 * JDBC insert
 * 2017-05-13
 * 
 * successfully test
 * 2017-05-16
 * 
 * Servlet implementation class receiveSilverData
 */
@WebServlet("/receiveSilverData")
public class receiveSilverData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private SilverDAO sDAO;
    private SilverVO silverVO;
    private HashMap<String,String> dataMap;
    private Connect connect;
    
    public receiveSilverData() {
        super();
        // TODO Auto-generated constructor stub
        
        sDAO =new SilverDAO();
        silverVO = new SilverVO();
        
        connect = new Connect();

    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("receiveSilverData");
		
		dataMap=connect.getData(request, response);
		/*String db = "insert into silverData("+connect.getKey()+") values("+connect.getValue()+")";
    	connect.sendData(db, request, response);
    	*/
		String androidID=dataMap.get("androidID");
		
		String silverID=sDAO.selectSilverID(androidID);
		
		String walkCount=dataMap.get("walkCount");
		String heartRate=dataMap.get("heartRate");
		String currentTime=dataMap.get("currentTime");
		String connMiBand=dataMap.get("connMiBand");
		boolean miBand=false;
		if(connMiBand.contains("o"))
		{
			miBand=true;
		}
		sDAO.insertSilverData(silverID, new SilverVO(Integer.parseInt(heartRate),Integer.parseInt(walkCount),new Date(Long.parseLong(currentTime)),miBand));
    	
	}

}
