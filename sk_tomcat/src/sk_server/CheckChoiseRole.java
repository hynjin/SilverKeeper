package sk_server;

import java.io.IOException;
import java.io.PrintWriter;


import conn.keeper.KeeperDAO;
import conn.silver.SilverDAO;
/*import net.sf.json.*;*/


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class CheckChoiseRole
 */
@WebServlet("/CheckChoiseRole")
public class CheckChoiseRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckChoiseRole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		process(request,response);
	}
	private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		/*String uri=request.getRequestURI();*/
		/*int lastIndex=uri.lastIndexOf("/");
		String action=uri.substring(lastIndex+1);*/
		JSONObject jsonObject=new JSONObject();
		
		String androidID=request.getParameter("androidID");
		String token=request.getParameter("token");
		response.getWriter().write(androidID+"\n");
		response.getWriter().write(token+"\n");
		PrintWriter out=response.getWriter();
		SilverDAO sDAO=new SilverDAO();
		KeeperDAO kDAO=new KeeperDAO();
		sDAO.insertSilverID("SV_"+androidID);
		String sCheck=sDAO.selectSilverID(androidID);
		String kCheck=kDAO.selectKeeperID(androidID);
		
		
		if(sCheck==""&&kCheck==""){
			
				jsonObject.put("result", "New");
				
			
			/*response.getWriter().write("New User");
			out.println("New");*/
		}
		else{
				jsonObject.put("result", "Existing");
			
		}
		response.setContentType("application/x-json; charset=UTF-8");
		out.print(jsonObject);
		out.flush();
		
	}
	private void requestCheckChoiseRole(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		String androidID=request.getParameter("androidID");
		String token=request.getParameter("token");
		response.getWriter().write(androidID+"\n");
		response.getWriter().write(token+"\n");
		PrintWriter out=response.getWriter();
		SilverDAO sDAO=new SilverDAO();
		KeeperDAO kDAO=new KeeperDAO();
		String sCheck=sDAO.selectSilverID(androidID);
		String kCheck=kDAO.selectKeeperID(androidID);
		if(sCheck==""&&kCheck==""){
			response.getWriter().write("New User");
			out.println("New");
		}
		else{
			response.getWriter().write("Existing user");
			out.println("Existing");
		}
	}
	

}
