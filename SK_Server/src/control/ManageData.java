package control;

import java.util.ArrayList;

import conn.keeper.KeeperDAO;
import conn.server.ServerConnection;
import conn.silver.SilverDAO;
import conn.silver.vo.SilverHeartRateVO;
import conn.silver.vo.SilverVO;

public class ManageData {
	private SilverDAO sDao=new SilverDAO();
	private KeeperDAO kDao=new KeeperDAO();
	private ServerConnection con;
	
	public ManageData()
	{
		con=new ServerConnection();
	}
	public String createSilverID(String androidID)
	{
		String silverID=""+androidID;
		
		return silverID;
	}
	public String createKeeperID(String androidID)
	{
		String keeperID=""+androidID;
		
		return keeperID;
	}
	public boolean checkMatching()
	{
		return true;
	}
	public int createIdentifyNumber()
	{
		int idNum=0;
		return idNum;
	}
	public String checkIdentifyNumber(int identifyNumber)
	{
		return sDao.checkIdentifyNumber(identifyNumber);
	}
	public boolean checkConnectRasp()
	{
		int result=con.connectToRasp();
		if(result==0)
		{
			return false;
		}
		return true;
	}
	public int calcMaxAverHeartRate(SilverHeartRateVO[] hrData)
	{
		int sumMax=0,cnt=hrData.length;
		ArrayList<Integer> maxHeartRate=new ArrayList<Integer>();//�ִ� �ɹڼ������� ������ �迭 ����
		for(int i=0;i<cnt;i++)
		{
			maxHeartRate.add(hrData[i].getMaxHeartRate()); //�ִ� �ɹڼ������� ����.
		}
		for(int i=0;i<cnt;i++)
		{
			sumMax+=maxHeartRate.get(i); //�ִ� �ɹڼ��� ����� ���ϱ� ���� ���� ���
		}
		return sumMax/cnt;
	}
	
	public int calcMinAverHeartRate(SilverHeartRateVO[] hrData)
	{
		int sumMin=0,cnt=hrData.length;
		ArrayList<Integer> minHeartRate=new ArrayList<Integer>();//�ּ� �ɹڼ������� ������ �迭 ����
		for(int i=0;i<cnt;i++)
		{
			minHeartRate.add(hrData[i].getMinHeartRate()); //�ּ� �ɹڼ������� ����.
		}
		for(int i=0;i<cnt;i++)
		{
			sumMin+=minHeartRate.get(i); //�ּ� �ɹڼ��� ����� ���ϱ� ���� ���� ���
		}
		return sumMin/cnt;
	}
	public byte checkEmergencyLevel(SilverHeartRateVO[] hrData, SilverVO[] sData)
	{
		byte status=0;
		int maxAver=this.calcMaxAverHeartRate(hrData),
			minAver=this.calcMinAverHeartRate(hrData),
			rate=0,result=0;
		int cnt=sData.length;
		for(int i=0;i<cnt;i++)
		{
			int heartRate=sData[i].getHeartRate();
			if(heartRate>maxAver)
			{
				rate++;
			}
			else if(heartRate<minAver)
			{
				rate++;
			}
		}
		result=rate/cnt;
		return status;
	}
	public byte calcEmergencyRate(SilverHeartRateVO[] hrData, SilverVO[] sData)
	{
		byte emergencyRate=this.checkEmergencyLevel(hrData, sData);
		if(emergencyRate<50)
		{
			return -1;
		}
		else if(emergencyRate>=50&&emergencyRate<=69)
			return 0;
		else if(emergencyRate>=70&& emergencyRate<=100)
		{
			return 1;
		}
		return -2;
	}
	/*public int calcMaxHeartRate(SilverVO[] silverData)
	{
		int max=0;
		return max;
	}
	public int calcMinHeartRate(SilverVO[] silverData)
	{
		int min=0;
		return min;
	}*/
/*	public int calcAverHeartRate(SilverHeartRateVO[] hrData)
	{
		int cnt=hrData.length;
		int maxAver=0,minAver=0,sumMax=0,sumMin=0,rate=0,result=0;
		
		ArrayList<Integer> maxHeartRate=new ArrayList<Integer>();//�ִ� �ɹڼ������� ������ �迭 ����
		ArrayList<Integer> minHeartRate=new ArrayList<Integer>();//�ּ� �ɹڼ������� ������ �迭 ����
		for(int i=0;i<cnt;i++)
		{
			maxHeartRate.add(hrData[i].getMaxHeartRate()); //�ִ� �ɹڼ������� ����
			minHeartRate.add(hrData[i].getMinHeartRate()); //�ּ� �ɹڼ������� ����.
		}
		for(int i=0;i<cnt;i++)
		{
			sumMax+=maxHeartRate.get(i); //�ִ� �ɹڼ��� ����� ���ϱ� ���� ���� ���
			sumMin+=minHeartRate.get(i); //�ּ� �ɹڼ��� ����� ���ϱ� ���� ���� ���
		}
		maxAver=sumMax/cnt; //�ִ� �ɹڼ��� ��հ��
		minAver=sumMin/cnt; //�ּ� �ɹڼ��� ��հ��
		
		
		return result;
	}*/
}
