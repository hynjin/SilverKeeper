package conn.silver;

import conn.silver.vo.SilverVO;

public class SilverDBManager {
private SilverDAO sDAO;

public SilverDBManager()
{
	sDAO=new SilverDAO();	
}
public int searchSilverIdentifyNumber(String silverID)
{
	return sDAO.selectIdentifyNumber(silverID);
}
public int insertSilverData(String silverID, SilverVO vo)
{
	return sDAO.insertSilverData(silverID, vo);
}
}
