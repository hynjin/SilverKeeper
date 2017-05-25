package connection;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.ManageData;
import conn.silver.SilverDAO;
import conn.silver.vo.SilverVO;

/**
 * created by hyunjin
 * connection of server
 * This servlet receive roleChoice from App to server.
 * 2017-05-13
 * 
 * just receive roleChoice
 * successfully test
 * 2017-05-16
 * 
 * Servlet implementation class selectRole
 */
@WebServlet("/selectRole")
public class selectRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connect connect;
    private HashMap<String,String> dataMap;
    private ManageData mData=ManageData.getInstance();
    
    public selectRole() {
        super();
        // TODO Auto-generated constructor stub
        
        connect = new Connect();
        dataMap = new HashMap<String,String>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		dataMap = connect.getData(request, response);
		
		//태영추가 
		SilverDAO sDAO =new SilverDAO();
		String key=connect.getKey();
		String androidID=dataMap.get("andoridID");
		if(sDAO.selectSilverID(androidID)=="noData")
		{
			String silverID=mData.createSilverID(androidID);
			sDAO.insertSilverID(silverID);
			sDAO.insertIdentifyNumber(silverID, mData.createIdentifyNumber());
		}
		else 
		{
			//해당하는 실버 식별번호가 있다=이미 등록된 회원이므로 그에 따른 연산 추가 필요함.
		}
	}

}
