package conn.keeper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import conn.db.DBConnectionModule;

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
			String sql = "insert into ���̺�� values(?,?,?,?,?)";
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
			String sql="select �ҷ��� �����͵� from ���̺�� where ���ǹ�";
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
			String sql =  "update ���̺�� set ��ĥ���=?(��ĥ ��) where ���ǹ�";
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
	
	
	//2017-5-18 18:46 추가. 안드로이드 아이디로 키퍼 식별번호 찾는 연산 추가.
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeeperDAO daotest= new KeeperDAO();
		
		daotest.updateKeeperToken("KP005", "TESTSUCCESS!");
		System.out.println("�׽�Ʈ���: "+daotest.selectSilverID("KP005")+","+daotest.selectKeeperToken("KP005")); 
	}

}
