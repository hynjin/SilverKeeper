package connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.silver.SilverDAO;
import conn.silver.vo.SilverAddressVO;
import conn.silver.vo.SilverHeartRateVO;
import conn.silver.vo.SilverVO;
import conn.keeper.KeeperDAO;
import java.sql.DriverManager;
import java.sql.Connection;

/**
 * created by hyunjin
 * connection of server
 * This servlet send SilverData from silverApp to server.
 * 2017-05-13
 * 
 * Servlet implementation class receiveSilverData
 */
@WebServlet("/receiveSilverData")
public class receiveSilverData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public receiveSilverData() {
        super();
        // TODO Auto-generated constructor stub
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

		System.out.println("receiveSilverData");
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

        String heartRate = request.getParameter("heartRate");
        String walkCount = request.getParameter("walkCount");
        String identifyNumber = request.getParameter("identifyNumber");
        String currentTime = request.getParameter("currentTime");
        String checkMiBand = request.getParameter("checkMiBand");

		System.out.println(checkMiBand);
		SilverDAO sDAO = new SilverDAO();
        SilverVO silverVO=new SilverVO(Integer.parseInt(heartRate), Integer.parseInt(walkCount), 
        		Integer.parseInt(identifyNumber),Date.valueOf(currentTime), Boolean.valueOf(checkMiBand).booleanValue());
        //sDAO.insertSilverData("SV005", silverVO);
		
        try{
        	Class.forName("com.mysql.jdbc.Driver");
        	String dbinfo = "jdbc:mysql://localhost:3306/dbTest";
        	String ID = "root";
        	String pw = "root";
        	
        	java.sql.Connection conn = java.sql.DriverManager.getConnection(dbinfo,ID,pw);
        	java.sql.Statement stmt = conn.createStatement();
        	
        	String db = "insert into silverData(heartRate,walkCount,identifyNumber,connMiBand,currentTime) values("+silverVO.toStringDB()+")";
        	System.out.println(db);
        	stmt.executeUpdate(db);
        	conn.close();
        	
        }catch(ClassNotFoundException e)
        {
        	e.printStackTrace();
        }
        catch(java.sql.SQLException e)
        {
        	e.printStackTrace();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
		PrintWriter out = response.getWriter();
		System.out.println(silverVO.toString());
		out.print("server successfully received the SilverData");

	}

}
