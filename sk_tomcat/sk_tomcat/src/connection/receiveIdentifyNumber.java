package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.ManageData;
import conn.keeper.KeeperDAO;
import conn.silver.SilverDAO;

/**
 * created by hyunjin
 * connection of server
 * This servlet receive Silver identifyNumber from keeperApp to server.
 * 2017-05-13
 * 
 * keeper : keeperID + identifyNumber
 * server : keeperID -> silverID -> identifyNumber
 * *****just receive identifyNumber from keeper.
 * 
 * Servlet implementation class receiveIdentifyNumber
 */
@WebServlet("/receiveIdentifyNumber")
public class receiveIdentifyNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private SilverDAO sDAO;
	private KeeperDAO kDAO;
    private Connect connect;
    private HashMap<String,String> dataMap;
	
    public receiveIdentifyNumber() {
        super();
        // TODO Auto-generated constructor stub
        sDAO = SilverDAO.getInstance();
        kDAO=KeeperDAO.getInstance();
        connect = new Connect();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("receiveIdentifyNumber");
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
    	response.setCharacterEncoding("UTF-8");

    	PrintWriter out=response.getWriter();//응답을 위해 PrintWriter 객체 생성
    	
    	dataMap = connect.getData(request, response); //connect를 통해 client로부터 전달받은 데이터값을 받음.
    	String key=connect.getKey();
    	String androidID=dataMap.get("androidID");	//
    	String identifyNumber=dataMap.get(androidID);
    	ManageData mData=ManageData.getInstance();
    	
    	
    	String silverID=mData.checkIdentifyNumber(Integer.parseInt(identifyNumber));
    	if(silverID.equals(""))
    	{
  		   	response.getWriter().write("identify Fail!");
  		   	return;
    	}
    	
    	String keeperID=mData.createKeeperID(androidID);
    	kDAO.insertKeeperID(silverID, keeperID);
    	
    	response.setContentType("text/plain");
    	response.getWriter().write(keeperID);
    	out.println("IdentifyResult|"+keeperID+"|"+silverID);//인증결과 키퍼 식별번호와 실버 식별번호 전달.
    	return;
		//sDAO.updateIdentifyNumber(silverID, Integer.parseInt(identifyNumber));
		
    }

}
