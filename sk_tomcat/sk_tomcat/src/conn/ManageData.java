package conn;

import java.util.ArrayList;
import java.util.Random;

import conn.keeper.KeeperDAO;
import conn.silver.SilverDAO;
import conn.silver.vo.SilverHeartRateVO;
import conn.silver.vo.SilverVO;



public class ManageData {
   private SilverDAO sDao=new SilverDAO();
   private KeeperDAO kDao=new KeeperDAO();
   private static ManageData mData=new ManageData();
   /*private ServerConnection con;*/
   
   public ManageData()
   {
      /*con=new ServerConnection();*/
   }
   public static ManageData getInstance()
   {
	   return mData;
   }
   public String createSilverID(String androidID)
   {
      String silverID="SV_"+androidID;
      
      return silverID;
   }
   public String createKeeperID(String androidID)
   {
      String keeperID="KP_"+androidID;
      
      return keeperID;
   }
   public boolean checkMatching()
   {
      return true;
   }
   public int createIdentifyNumber()
   {
      int idNum=0;
      Random rand=new Random();
      while(true)
      {
         idNum=rand.nextInt(99999);
         if(this.checkIdentifyNumber(idNum)!="")
         {
            break;
         }
      }
      
      return idNum;
   }
   public String checkIdentifyNumber(int identifyNumber)
   {
      return sDao.checkIdentifyNumber(identifyNumber);
   }
   public boolean checkConnectRasp()
   {
     /* int result=con.connectToRasp();
      if(result==0)
      {
         return false;
      }*/
      return true;
   }
   public int calcMaxAverHeartRate(SilverHeartRateVO[] hrData)
   {
      int sumMax=0,cnt=hrData.length;
      for(int i=0;i<cnt;i++)
      {
         sumMax+=hrData[i].getMaxHeartRate(); //최대 심박수값 평균을 구하기 위해 총합 계산
      }
      return sumMax/cnt;
   }
   
   public int calcMinAverHeartRate(SilverHeartRateVO[] hrData)
   {
      int sumMin=0,cnt=hrData.length;
  
      for(int i=0;i<cnt;i++)
      {
        sumMin+=hrData[i].getMinHeartRate(); //최소 심박수값들을 저장.
      }
   
      return sumMin/cnt;
   }
   public int checkEmergencyLevel(SilverHeartRateVO[] hrData, SilverVO[] sData)
   {
      int status=0;
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
      int emergencyRate=this.checkEmergencyLevel(hrData, sData);
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
   public int calcMaxHeartRate(SilverVO[] silverData)
   {
      int max=0;
      int temp=0;
      for(int i=0;i<silverData.length;i++)
      {
    	  temp=silverData[i].getHeartRate();
    	  if(temp>max)
    	  {
    		  max=temp;
    	  }
      }
      
      return max;
   }
   public int calcMinHeartRate(SilverVO[] silverData)
   {
      int min=0;
      int temp=0;
      for(int i=0;i<silverData.length;i++)
      {
    	  temp=silverData[i].getHeartRate();
    	  if(temp<min)
    	  {
    		  min=temp;
    	  }
      }
      return min;
   }
/*   public int calcAverHeartRate(SilverHeartRateVO[] hrData)
   {
      int cnt=hrData.length;
      int maxAver=0,minAver=0,sumMax=0,sumMin=0,rate=0,result=0;
      
      ArrayList<Integer> maxHeartRate=new ArrayList<Integer>();//최대 심박수값들을 저장한 배열 선언
      ArrayList<Integer> minHeartRate=new ArrayList<Integer>();//최소 심박수값들을 저장한 배열 선언
      for(int i=0;i<cnt;i++)
      {
         maxHeartRate.add(hrData[i].getMaxHeartRate()); //최대 심박수값들을 저장
         minHeartRate.add(hrData[i].getMinHeartRate()); //최소 심박수값들을 저장.
      }
      for(int i=0;i<cnt;i++)
      {
         sumMax+=maxHeartRate.get(i); //최대 심박수값 평균을 구하기 위해 총합 계산
         sumMin+=minHeartRate.get(i); //최소 심박수값 평균을 구하기 위해 총합 계산
      }
      maxAver=sumMax/cnt; //최대 심박수값 평균계산
      minAver=sumMin/cnt; //최소 심박수값 평균계산
      
      
      return result;
   }*/
}