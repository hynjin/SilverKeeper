package connection;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.ManageData;
import conn.keeper.KeeperDAO;
import conn.silver.SilverDAO;
import conn.silver.vo.SilverHeartRateVO;
import conn.silver.vo.SilverVO;

/**
 * created by hyunjin
 * connection of server
 * This servlet receive data from silverApp to server.
 * 
 * 
 * Servlet implementation class receiveData
 */

@WebServlet("/receiveData.do")
public class receiveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	       
	private SilverDAO sDAO;
	private KeeperDAO kDAO;
	private Connect connect;
	private HashMap<String,String> dataMap;
	private String db;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public receiveData() {
        super();
        // TODO Auto-generated constructor stub
        
        sDAO = new SilverDAO();
        kDAO =new KeeperDAO();
        connect = new Connect();

		dataMap = new HashMap<String,String>();
		db = "";
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
   /* protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	System.out.println("!!receiveData");
		dataMap = connect.getData(request, response);
		System.out.println("--"+connect.getType());
		
		switch(connect.getType())
		{
		case "sendBLE":
			db = "update silverMiBandConn set BLESTATUS="+dataMap.get("BLESTATUS")+" where silverID="+dataMap.get("silverID");
			connect.sendData(db, request, response);
			break;
		case "sendSilverData":
			db = "insert into silverData("+connect.getKey()+") values("+connect.getValue()+")";
			connect.sendData(db, request, response);
			break;
		case "sendSilverToken":
			break;
		case "sendHomeComing":
			db = "update silverHomeComingStatus set homeComing="+connect.getKey()+" where silverID="+connect.getValue();
			connect.sendData(db, request, response);
			break;
		case "sendIdentifyNumber":
			break;
		case "sendKeeperName":
			break;
		case "sendKeeperToken":
			db = "insert into keeperData("+connect.getKey()+") values("+connect.getValue()+")";
	    	connect.sendData(db, request, response);
			System.out.println("getKeeperToken :"+db);
			break;
		case "sendRaspIP":
			break;
		case "sendRole":
			break;
		//태영추가
		case "checkJoin":
			checkJoinProcess(dataMap, request, response);
			break;
		}
    }*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("!!receiveData");
		dataMap = connect.getData(request, response);
		System.out.println("--"+connect.getType());
		
		switch(connect.getType())
		{
		/*case "sendBLE":
			db = "update silverMiBandConn set BLESTATUS="+dataMap.get("BLESTATUS")+" where silverID="+dataMap.get("silverID");
			connect.sendData(db, request, response);
			break;
		case "sendSilverData":
			db = "insert into silverData("+connect.getKey()+") values("+connect.getValue()+")";
			connect.sendData(db, request, response);
			break;
		case "sendSilverToken":
			break;
		case "sendHomeComing":
			db = "update silverHomeComingStatus set homeComing="+connect.getKey()+" where silverID="+connect.getValue();
			connect.sendData(db, request, response);
			break;
		case "sendIdentifyNumber":
			break;
		case "sendKeeperName":
			break;
		case "sendKeeperToken":
			db = "insert into keeperData("+connect.getKey()+") values("+connect.getValue()+")";
	    	connect.sendData(db, request, response);
			System.out.println("getKeeperToken :"+db);
			break;
		case "sendRaspIP":
			break;
		case "sendRole":
			break;*/
		//태영추가
		case "checkJoin": //사용자가 신규 사용자인지, 기존 사용자인지 구분하는 연산.
			checkJoinProcess(dataMap, request, response);
			break;
		case "sendSilverDataToKeeper": //키퍼로부터 식별번호를 받아, 해당하는 실버의 정보를 보내는 연산
			sendSilverDataToKeeperProcess(dataMap,request,response);
			break;
		case "sendSilverData" ://실버로부터 식별번호를 받아, 해당 정보를 보내는 연산
			sendSilverDataProcess(dataMap,request,response);
		case "receiveSilverData": //실버로부터 실버의 생체데이터를 받아 수집하여 계산하는 연산.
			receiveSilverDataProcess(dataMap,request,response);
		}
	}
	protected void checkJoinProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		String androidID=dataMap.get("androidID");
		System.out.println("androidID="+androidID+"\n");
		String token=dataMap.get("token");
		System.out.println("token="+token+"\n");
		String silverID=sDAO.selectSilverID(androidID);    			
		String keeperID=kDAO.selectKeeperID(androidID);
		HashMap<String,String> sendMap=new HashMap<String,String>();
		
		if(silverID!="")
		{
			sendMap.put("result", "silver");
			sendMap.put("silverID",silverID);
			System.out.println("S:"+silverID);
		}
		else if(keeperID!="")
		{
			sendMap.put("result", "keeper");
			sendMap.put("keeperID",keeperID);
			
			System.out.println("K:"+keeperID);
		}
		else
		{
			sendMap.put("result", "new");
			sendMap.put("androidID", androidID);
		}
		
		connect.setData(sendMap, request, response);
		
		
    }
	protected void sendSilverDataToKeeperProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		String keeperID=dataMap.get("keeperID");
		System.out.println("keeperID="+keeperID+"\n");
		String silverID=kDAO.selectSilverID(keeperID);
		SilverVO vo=sDAO.selectSilverData(silverID);
		HashMap<String,String> sendMap=new HashMap<String,String>();
		int walkCount=vo.getWalkCount();
		int heartRate=vo.getHeartRate();
		String currentTime=vo.getCurrentTime();
		boolean connMiBand=vo.getCheckMiBand();
		System.out.println("--------------------sendSilverDataToKeeper-----------------\n");
		System.out.println("walkCount:"+walkCount+"\n");
		System.out.println("heartRate:"+heartRate+"\n");
		System.out.println("currentTime:"+currentTime+"\n");
		System.out.println("connMiBand:"+connMiBand);
		System.out.println("----------------------------------------------------------\n");

		sendMap.put("walkCount", walkCount+"");
		sendMap.put("heartRate", heartRate+"");
		sendMap.put("currentTime", currentTime);
		sendMap.put("connMiBand",connMiBand+"");
		
		connect.setData(sendMap, request, response);
    }
	protected void sendSilverDataProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String silverID=dataMap.get("silverID");
		System.out.println("silverID="+silverID+"\n");
		SilverVO vo=sDAO.selectSilverData(silverID);
		int identifyNumber=sDAO.selectIdentifyNumber(silverID);
		
		int walkCount=vo.getWalkCount();
		int heartRate=vo.getHeartRate();
		String currentTime=vo.getCurrentTime();
		boolean connMiBand=vo.getCheckMiBand();
		
		HashMap<String,String> sendMap=new HashMap<String,String>();
		System.out.println("---------------------sendSilverDataProcess----------------\n");
		System.out.println("walkCount:"+walkCount+"\n");
		System.out.println("heartRate:"+heartRate+"\n");
		System.out.println("currentTime:"+currentTime+"\n");
		System.out.println("connMiBand:"+connMiBand);
		System.out.println("identifyNumber:"+identifyNumber);
		System.out.println("----------------------------------------------------------\n");

		sendMap.put("walkCount", walkCount+"");
		sendMap.put("heartRate", heartRate+"");
		sendMap.put("currentTime", currentTime);
		sendMap.put("connMiBand",connMiBand+"");
		sendMap.put("identifyNumber", identifyNumber+"");
	}
	protected void receiveSilverDataProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String silverID=dataMap.get("silverID");
		String walkCount=dataMap.get("walkCount");
		String heartRate=dataMap.get("heartRate");
		String currentTime=dataMap.get("currentTime");
		String connMiBand=dataMap.get("connMiBand");
		boolean conn;
		if(connMiBand.contains("true"))
		{
			conn=true;
		}
		else conn=false;
		HashMap<String,String>sendMap=new HashMap<String,String>();
		SilverVO newData=new SilverVO(Integer.parseInt(heartRate),Integer.parseInt(walkCount),currentTime,conn);
				
		int result=sDAO.insertSilverData(silverID, newData);
		setHeartRateData(silverID);
		
		if(result!=0)
			sendMap.put("insertResult","success");
		else
			sendMap.put("insertResult", "fail");
		connect.setData(sendMap, request, response);
	}
	protected void sendSilverEmergencyStatusProcess(HashMap<String,String>dataMap,HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		String silverID=dataMap.get("silverID");
		SilverHeartRateVO[] hrList=sDAO.selectSilverHeartRateVO(silverID);
		SilverVO[] voList=sDAO.selectFixtedNumberSilverDataArray(silverID);
		ManageData manage=new ManageData();
		int result=manage.checkEmergencyLevel(hrList, voList);
		
		//SilverHeartRateVO[] voList=sDAO.selectSilverHeartRateVO(silverID);
		
	}
	public void setHeartRateData(String silverID)
	{
		SilverVO[] voList=sDAO.selectFixtedNumberSilverDataArray(silverID);
		//ManageData manage=new ManageData();
		
		ArrayList<Integer>maxList=new ArrayList<Integer>();
		ArrayList<Integer>minList=new ArrayList<Integer>();
		
		int	temp=0,max=0,min=0,length=voList.length;
		
			for(int x=0;x<6;x++)
			{
				
				temp=voList[x].getHeartRate();
				if(max<temp)
				{
					max=temp;
					
				}
				else if(min>temp)
				{
					min=temp;
					
					
				}
				if(x==voList.length-1)
				{
					break;
				}
			}

			sDAO.insertHeartRate(silverID, min, max, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(System.currentTimeMillis())));

	}
}
/*SilverVO[] voList=sDAO.selectFixtedNumberSilverDataArray(silverID);
		//ManageData manage=new ManageData();
		
		ArrayList<Integer>maxList=new ArrayList<Integer>();
		ArrayList<Integer>minList=new ArrayList<Integer>();
		
		int i=voList.length/6,
			j=voList.length%6,
			temp=0,max=0,min=0,maxTime=0,minTime=0;
		for(int k=0;k<=i;k++)
		{
			for(int x=0;x<6;x++)
			{
				
				temp=voList[(6*k)+x].getHeartRate();
				if(max<temp)
				{
					max=temp;
					maxTime=(6*k)+x;
				}
				else if(min>temp)
				{
					min=temp;
					minTime=(6*k)+x;
				}
				if(((6*k)+x)==voList.length-1)
				{
					break;
				}
			}
			maxList.add(max);
			minList.add(min);
		}
		for(int y=0;y<maxList.size();y++)
		{
			sDAO.insertHeartRate(silverID, minList.get(y), maxList.get(y), (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(System.currentTimeMillis())));
		}
	}*/



