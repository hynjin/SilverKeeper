package conn.silver;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import conn.db.DBConnectionModule;
import conn.silver.vo.SilverAddressVO;
import conn.silver.vo.SilverHeartRateVO;
import conn.silver.vo.SilverVO;
public class SilverDAO {
	
	private static final long serialVersionUID=-7422967333451405L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public SilverDAO()
	{
		connModule=DBConnectionModule.getInstance();
		conn=connModule.getConn();
	}
	
	//데이터 입력
	public int insertSilverID(String silverID)
	{
		int rowNum = 0;
		
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into SilverID values(?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, silverID);
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
		
	}
	public int insertSilverData(String silverID, SilverVO silverVO)
	{
		int rowNum = 0;
		
		int walkCount=silverVO.getWalkCount();
		int heartRate=silverVO.getWalkCount();
		int identifyNumber=silverVO.getIdentifyNumber();
		Date currentTime=silverVO.getCurrentTime();
		boolean checkMiBand=silverVO.getCheckMiBand();
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into SilverData values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, silverID);
			pstmt.setInt(2, walkCount);
			pstmt.setInt(3, heartRate);
			pstmt.setInt(4, identifyNumber);
			pstmt.setDate(5, currentTime);
			int check=0;
			if(checkMiBand)
			{
				check=1;
			}
			pstmt.setInt(6,check);
					
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
		
		
	}
	public int insertHeartRate(String silverID, SilverHeartRateVO silverHeartRate)
	{
		return this.insertHeartRate(silverID, 
									silverHeartRate.getMinHeartRate(), 
									silverHeartRate.getMaxHeartRate(), 
									silverHeartRate.getCurrentTime()
									);
	}
	public int insertHeartRate(String silverID, int minHeartRate,int maxHeartRate,Date currentTime)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into silverHeartRate values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, maxHeartRate);
			pstmt.setInt(2, minHeartRate);
			SimpleDateFormat transFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=transFormat.format(currentTime);
			pstmt.setString(3, silverID);	
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	/*public int insertBLEStatus(String silverID, boolean bleStatus)
	{
		int rowNum = 0;
		
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into silverMiBandConn values(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, silverID);
			int ble=0;
			if(bleStatus)
			{
				ble=1;
			}
			pstmt.setInt(2,ble);
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
	}*/
	public int insertSilverAddress(String silverID,String rpURL,String silverToken, String wifiSSID)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql =  "insert into silverAddress values (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, silverID);
			pstmt.setString(2,silverToken);
			pstmt.setString(3,rpURL);
			pstmt.setString(4,wifiSSID);
			rowNum = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	
	//데이터 갱신
	public int updateSilverData(String silverID, SilverVO silverVO)
	{
		int rowNum = 0;
		
		int walkCount=silverVO.getWalkCount();
		int heartRate=silverVO.getWalkCount();
		int identifyNumber=silverVO.getIdentifyNumber();
		Date currentTime=silverVO.getCurrentTime();
		boolean checkMiBand=silverVO.getCheckMiBand();
		PreparedStatement pstmt = null;
		try{
			String sql = "update SilverData set walkCount=?,heartRate=?,identifyNumber=?,currentTime=?,checkMiBand=?) where silverID=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, silverID);
			pstmt.setInt(2, walkCount);
			pstmt.setInt(3, heartRate);
			pstmt.setInt(4, identifyNumber);
			pstmt.setDate(5, currentTime);
			int check=0;
			if(checkMiBand)
			{
				check=1;
			}
			pstmt.setInt(6,check);
					
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	public int updateIdentifyNumber(String silverID,int identifyNumber)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql =  "update silverData set identifyNumber=? where silverID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,identifyNumber);
			pstmt.setString(2, silverID);
			rowNum = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	public int updateBLEStatus(String silverID, boolean bleStatus)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql =  "update silverMiBandConn set BLEStatus=? where silverID=?";
			pstmt = conn.prepareStatement(sql);
			int ble=0;
			if(bleStatus)
			{
				ble=1;
			}
			pstmt.setInt(1,ble);
			pstmt.setString(2, silverID);
			rowNum = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	public int updateHomeComing(String silverID,boolean homeComing)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		int hComing=0;
		if(homeComing==true)
		{
			hComing=1;
		}
		try{
			String sql =  "update SilverHomeComingStatus set homeComing=? where silverID=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,hComing);
			pstmt.setString(2,silverID);
			rowNum = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	public int updateSilverToken(String silverID,String silverToken)
	{
	int rowNum = 0;
	PreparedStatement pstmt = null;
	
	try{
		String sql =  "update silverAddress set silverToken=? where silverID=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,silverToken);
		pstmt.setString(2, silverID);
		rowNum = pstmt.executeUpdate();
	}catch(SQLException se){
		se.printStackTrace();
	}catch(Exception ex){
		ex.printStackTrace();
	}finally
	{
		try{
			if(pstmt != null){
				pstmt.close();
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
	return rowNum;
	}
	public int updateRPURL(String silverID,String rpURL)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql =  "update silverAddress set rassberryPiURL=? where silverID=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,rpURL);
			pstmt.setString(2, silverID);
			rowNum = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	public int updateConnMiBand(String silverID, boolean connMiband)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		int miband=0;
		if(connMiband==true)
		{
			miband=1;
		}
		try{
			String sql =  "update silverData set connMiBand=? where silverID=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,miband);
			pstmt.setString(2,silverID);
			rowNum = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	public int updateWifiSSID(String silverID,String wifiSSID)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql =  "update silverAddress set wifiSSID=? where silverID=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,wifiSSID);
			pstmt.setString(2, silverID);
			rowNum = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	public int updateEmergencyRate(String silverID,byte emergencyRate)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql =  "update SilverEmergencyStatus set emergencyRate=? where silverID=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setByte(1, emergencyRate);
			pstmt.setString(2, silverID);
			rowNum = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	
	//데이터 검색
	
	public SilverVO[] selectSilverData(String silverID)
	{
		ArrayList<SilverVO> voList=new ArrayList<SilverVO>();
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select walkCount,heartRate,identifyNumber,currentTime,connMiBand from silverData where silverID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,silverID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				int walkCount=rs.getInt("walkCount");
				int heartRate=rs.getInt("heartRate");
				int identifyNumber=rs.getInt("identifyNumber");
				Date currentTime=rs.getDate("currentTime");
				boolean connMiBand=true;
				if(rs.getInt("connMiBand")==0)
				{
					connMiBand=false;
				}
				voList.add(new SilverVO(walkCount,heartRate,identifyNumber,currentTime,connMiBand));
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
			// TODO: handle exception
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(pstmt!=null)
				{
					pstmt.close();
				}
				
			} 
			catch (SQLException se) {
				{
					se.printStackTrace();
				}
			}
		}
		return voList.toArray(new SilverVO[voList.size()]);
	}
	public SilverHeartRateVO[] selectSilverHeartRateVO(String silverID)
	{
		ArrayList<SilverHeartRateVO> list=new ArrayList<SilverHeartRateVO>();
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select maxHeartRate,minHeartRate,currentTime from silverHeartRate where silverID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,silverID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				int max=rs.getInt("maxHeartRate");
				int min=rs.getInt("minHeartRate");
				Date currentTime=rs.getDate("currentTime");
				list.add(new SilverHeartRateVO(max, min, currentTime));
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
			// TODO: handle exception
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(pstmt!=null)
				{
					pstmt.close();
				}
				
			} 
			catch (SQLException se) {
				{
					se.printStackTrace();
				}
			}
		}
		return list.toArray(new SilverHeartRateVO[list.size()]);
	}
	public SilverAddressVO selectSilverAddress(String silverID)
	{
		SilverAddressVO savo=null;
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select silverToken,rassberryPiURL,wifiSSID from silverAddress where silverID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,silverID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				String silverToken=rs.getString("silverToken");
				String rassberryPiURL=rs.getString("rassberryPiURL");
				String wifiSSID=rs.getString("wifiSSID");
				savo=new SilverAddressVO(silverToken, rassberryPiURL, wifiSSID);
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
			// TODO: handle exception
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(pstmt!=null)
				{
					pstmt.close();
				}
				
			} 
			catch (SQLException se) {
				{
					se.printStackTrace();
				}
			}
		}
		return savo;
	}
	public boolean selectBLEStatus(String silverID)
	{
		boolean bleStatus=true;
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select BLEStatus from silverMiBandConn where silverID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,silverID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				int result=rs.getInt("BLEStatus");
				if(result==0)
				{
					bleStatus=false;
				}	
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
			// TODO: handle exception
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(pstmt!=null)
				{
					pstmt.close();
				}
				
			} 
			catch (SQLException se) {
				{
					se.printStackTrace();
				}
			}
		}
		return bleStatus;
	}
	public boolean selectHomeComing(String silverID)
	{
		boolean homeComing=true;
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select homeComing from silverHomeComingStatus where silverID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,silverID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				int result=rs.getInt("homeComing");
				if(result==0)
				{
					homeComing=false;
				}	
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
			// TODO: handle exception
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(pstmt!=null)
				{
					pstmt.close();
				}
				
			} 
			catch (SQLException se) {
				{
					se.printStackTrace();
				}
			}
		}
		return homeComing;
	}
	public byte selectEmergencyRate(String silverID)
	{
		byte emergencyRate=-1;
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select emergencyRate from silverEmergencyStatus where silverID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,silverID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				int result=rs.getInt("emergencyRate");
				emergencyRate=(byte)result;
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
			// TODO: handle exception
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(pstmt!=null)
				{
					pstmt.close();
				}
				
			} 
			catch (SQLException se) {
				{
					se.printStackTrace();
				}
			}
		}
		return emergencyRate;
	}
	/*	public int insert(String silverID)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into 테이블명 values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, silverID);
			
					
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	public String select(String silverID)
	{
		String data=null;
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select 불러올 데이터들 from 테이블명 where 조건문";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,silverID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				String result=rs.getString("table_row");
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
			// TODO: handle exception
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(pstmt!=null)
				{
					pstmt.close();
				}
				
			} 
			catch (SQLException se) {
				{
					se.printStackTrace();
				}
			}
		}
		return data;
	}*/
	/*public int update(String silverID)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql =  "update 테이블명 set 고칠대상=?(고칠 값) where 조건문";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString();
			rowNum = pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return rowNum;
	}*/
		
/*	public int insertSilverID(String silverID)
	{
		int rowNum = 0;
		return rowNum;
	}
	public int insertIdentifyNumber(String silverID,int identifyNumber)
	{
		int rowNum = 0;
		return rowNum;
	}
	*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SilverDAO dao=new SilverDAO();
		SilverVO voTest=new SilverVO(35,60,1435,new Date(System.currentTimeMillis()),true);
		SilverHeartRateVO hrTest=new SilverHeartRateVO(150, 78, new Date(System.currentTimeMillis()));
		System.out.println(dao.insertSilverData("SV005",voTest)+"\n");
		System.out.println(dao.insertHeartRate("SV005",hrTest)+"\n");
		System.out.println(dao.insertHeartRate("SV005",190,50,new Date(System.currentTimeMillis()))+"\n");

	}

}
