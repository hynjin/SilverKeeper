package sk_server;

import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.silver.SilverDAO;
import conn.silver.vo.SilverVO;

/**
 * Servlet implementation class ConnectServlet
 */
@WebServlet("/connect1")
public class httpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public httpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        System.out.println("get request");
		String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        String silver = request.getParameter("silverID");
        String heart = request.getParameter("heartRate");
        String step = request.getParameter("stepCnt");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
       SilverDAO dao=SilverDAO.getInstance();
        SilverVO silverVO=new SilverVO(15, 111, 0, new Date(System.currentTimeMillis()), true);
        dao.insertSilverData("SV005", silverVO);
        
        
        out.print("아이디="+id);
        out.print("비밀번호="+passwd+"<br>");
        out.print("silverID="+silver+"<br>");
        out.print("heartRate="+heart+"<br>");
        out.print("stepCnt="+step+"<br>");
        System.out.println("아이디="+id+"<br>");
        System.out.println("비밀번호="+passwd+"<br>");
        System.out.println("silverID="+silver+"<br>");
        System.out.println("heartRate="+heart+"<br>");
        System.out.println("stepCnt="+step+"<br>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}