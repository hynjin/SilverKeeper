/*
 * created by hyunjin
 * connection of server
 */

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
import conn.silver.vo.SilverAddressVO;
import conn.silver.vo.SilverHeartRateVO;
import conn.silver.vo.SilverVO;
import conn.keeper.KeeperDAO;

@WebServlet("/connect")
public class Connection  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SilverDAO sDAO;
	private KeeperDAO kDAO;
	
	public Connection()
	{
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 System.out.println("get request");
		 //String method = request.getParameter("method");
			String id = request.getParameter("id");
	        String passwd = request.getParameter("passwd");
	        String silver = request.getParameter("silverID");
	        String heart = request.getParameter("heartRate");
	        String step = request.getParameter("stepCnt");
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        
	        
	       SilverDAO dao=new SilverDAO();
	        SilverVO silverVO=new SilverVO(15, 111, new Date(System.currentTimeMillis()), true);
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
	
	public void connectToApp(int identifyNumber)
	{
		System.out.println("connectToApp");
	}
	
	public void connectToRasp()
	{
		System.out.println("connectToRasp");
	}
	
	public boolean selectRole()
	{
		System.out.println("selectRole");
		return true;
	}
	
	public void sendIdentifyNumberToSilver(int identifyNumber)
	{
		System.out.println("sendIdentifyNumberToSilver");
	}
	
	public void sendSilverID(String silverID)
	{
		System.out.println("sendSilverID");
	}
	
	public void sendKeeperID(String keeperID)
	{
		System.out.println("sendKeeperID");
	}
	
	public void sendSilverData(int heartRate,int walkCount, boolean homeComing, int checkEmergency, boolean checkMiBand, boolean checkBluetoothConnecnt)
	{
		System.out.println("sendSilverData");
	}
	public void sendConnectRequest(int identifyNumber, String keeperName)
	{
		System.out.println("sendConnectRequest");
	}
	
	public void sendWifiSSID(String wifiSSID)
	{
		System.out.println("sendWifiSSID");
	}
	
	public void receiveSilverToken()
	{
		System.out.println("receiveSilverToken");
	}
	
	public void receiveKeeperToken()
	{
		System.out.println("receiveKeeperToken");
	}
	
	public void receiveKeeperName()
	{
		System.out.println("receiveKeeperName");
	}
	
	public void receiveIdentifyNumber()
	{
		System.out.println("receiveIdentifyNumber");
	}
	
	public void receiveSilverData()
	{
		System.out.println("receiveSilverData");
	}
	
	public void receiveRaspIP(byte[] raspIP)
	{
		System.out.println("receiveRaspIP");
	}
	
	public void receiveHomeComing(boolean homeComing)
	{
		System.out.println("receiveHomeComing");
	}
	
	public void receiveBLEStatus(boolean checkBluetoothConnect)
	{
		System.out.println("receiveBLEStatus");
	}
	
	public void sendPushAlarm()
	{
		System.out.println("sendPushAlarm");
	}
}
