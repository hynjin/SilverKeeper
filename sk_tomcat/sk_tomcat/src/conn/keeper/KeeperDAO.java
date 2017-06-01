package conn.keeper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import conn.db.DBConnectionModule;
import conn.silver.vo.SilverVO;

public class KeeperDAO implements Serializable{
	
	private static final long serialVersionUID=-5542359229673091405L;
	private DBConnectionModule connModule;
	private Connection conn;
	//private KeeperDAO keeperDAO;
	public KeeperDAO()
	{
		connModule=DBConnectionModule.getInstance();
		conn=connModule.getConn();
	}
/*	public static KeeperDAO getInstance()
	{
		return keeperDAO;
	}*/
/*	public int insert(String silverID)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into 占쏙옙占싱븝옙占?values(?,?,?,?,?)";
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
			String sql="select 占쌀뤄옙占쏙옙 占쏙옙占쏙옙占싶듸옙 from 占쏙옙占싱븝옙占?where 占쏙옙占실뱄옙";
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
	}
	public int update(String silverID)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql =  "update 占쏙옙占싱븝옙占?set 占쏙옙칠占쏙옙占??(占쏙옙칠 占쏙옙) where 占쏙옙占실뱄옙";
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
	public int insertKeeperID(String silverID, String keeperID)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into keeperID values(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keeperID);
			pstmt.setString(2, silverID);
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
	public String[] selectAllKeeperID(String silverID)
	{
		ArrayList<String> keeperIDs=new ArrayList<String>();
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select keeperID from keeperID where silverID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,silverID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				String result=rs.getString("keeperID");
				keeperIDs.add(result);
				
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
		return keeperIDs.toArray(new String[keeperIDs.size()]);
	}
	
	public String selectSilverID(String keeperID)
	{
		String silverID=null;
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select silverID from keeperID where keeperID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,keeperID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				String result=rs.getString("silverID");
				silverID=result;
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
		return silverID;
	}
	public int updateKeeperToken(String keeperID,String keeperToken)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql =  "update keeperData set keeperToken=? where keeperID=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,keeperToken);
			pstmt.setString(2,keeperID);
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
	public String selectKeeperToken(String keeperID)
	{
		String keeperToken=null;
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select keeperToken from keeperData where keeperID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,keeperID);
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				String result=rs.getString("keeperToken");
				keeperToken=result;
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
		return keeperToken;
	}
	
	
	//2017-5-18 18:46 異붽?. ?덈뱶濡쒖씠???꾩씠?붾줈 ?ㅽ띁 ?앸퀎踰덊샇 李얜뒗 ?곗궛 異붽?.
	public String selectKeeperID(String androidID)
	{
		String keeperID="noData";
		PreparedStatement pstmt=null;
		try 
		{
			String sql="select keeperID from keeperID";
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				keeperID=rs.getString("keeperID");
				
				if(keeperID.contains(androidID))
				{
					break;
				}
				keeperID="noData";
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
		return keeperID;
	}
	
	public int insertKeeperToken(String keeperID,String token)
	{
		
			int rowNum = 0;
			PreparedStatement pstmt = null;
			try{
				String sql = "insert into keeperData values(?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, keeperID);
				pstmt.setString(2, token);
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeeperDAO daotest= new KeeperDAO();
		
		daotest.updateKeeperToken("KP005", "TESTSUCCESS!");
		System.out.println("占쌓쏙옙트占쏙옙占? "+daotest.selectSilverID("KP005")+","+daotest.selectKeeperToken("KP005")); 
	}

}
