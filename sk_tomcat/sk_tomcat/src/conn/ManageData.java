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
   public String createSilverID(String androidID)		//안드로이드ID 이용해 실버ID 생성(앞에 SV_ 붙임)
   {
      String silverID="SV_"+androidID;
      
      return silverID;
   }
   public String createKeeperID(String androidID)		//안드로이드ID 이용해 키퍼ID 생성(앞에 KP_ 붙임)
   {
      String keeperID="KP_"+androidID;
      
      return keeperID;
   }
   public boolean checkMatching()
   {
      return true;
   }
   public int createIdentifyNumber()		//인증번호 생성
   {
      int idNum=0;
      Random rand=new Random();				//랜덤으로 생성
      while(true)
      {
         idNum=rand.nextInt(999999)+100000;
         String searchResult=this.checkIdentifyNumber(idNum);
         
         if(searchResult==null)
         {
           break;
         }
       
      }
      
      return idNum;
   }
   public String checkIdentifyNumber(int identifyNumber)	//인증번호 체크
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
   public int calcMaxAverHeartRate(SilverHeartRateVO[] hrData)	//최대평균심박수 구하기
   {
      int sumMax=0,cnt=hrData.length;
      if(cnt==0)
      {
    	  return -1;
      }
      for(int i=0;i<cnt;i++)
      {
         sumMax+=hrData[i].getMaxHeartRate(); //理쒕? ?щ컯?섍컪 ?됯퇏??援ы븯湲??꾪빐 珥앺빀 怨꾩궛
      }
      return sumMax/cnt;
   }
   
   public int calcMinAverHeartRate(SilverHeartRateVO[] hrData)	//최소평균심박수 구하기
   {
      int sumMin=0,cnt=hrData.length;
  
      if(cnt==0)
      {
    	  return -1;
      }
      for(int i=0;i<cnt;i++)
      {
        sumMin+=hrData[i].getMinHeartRate(); //理쒖냼 ?щ컯?섍컪?ㅼ쓣 ???
        
      }
      
      return sumMin/cnt;
   }
   public int checkEmergencyLevel(SilverHeartRateVO[] hrData, SilverVO[] sData)	//위험레벨 구하기
   {
      int status=0;
      int maxAver=this.calcMaxAverHeartRate(hrData),
         minAver=this.calcMinAverHeartRate(hrData),
         rate=0,result=0;
      if(maxAver==-1||minAver==-1)
      {
    	  return -1;
      }
    	  int cnt=sData.length;
      System.out.println("----------checkEmergencyLevel-----------\n");
      System.out.println("maxAver="+maxAver+"\n");
      System.out.println("minAver="+minAver+"\n");
      System.out.println("cnt="+cnt+"\n");
      
      for(int i=0;i<cnt;i++)
      {
         int heartRate=sData[i].getHeartRate();
         System.out.println("heartRate:"+heartRate+"\n");
         if(heartRate>maxAver)
         {
            rate++;
            System.out.println("rate:"+rate+"\n");
         }
         else if(heartRate<minAver)
         {
            rate++;
            System.out.println("rate:"+rate+"\n");
         }
      }
      
      result=100*(cnt-rate)/cnt;
      System.out.println("result:"+result+"\n");
      System.out.println("------------------------------------------\n");
      return result;
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
   public int calcMinHeartRate(SilverVO[] silverData)		//최소심박수 계산
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
      
      ArrayList<Integer> maxHeartRate=new ArrayList<Integer>();//理쒕? ?щ컯?섍컪?ㅼ쓣 ??ν븳 諛곗뿴 ?좎뼵
      ArrayList<Integer> minHeartRate=new ArrayList<Integer>();//理쒖냼 ?щ컯?섍컪?ㅼ쓣 ??ν븳 諛곗뿴 ?좎뼵
      for(int i=0;i<cnt;i++)
      {
         maxHeartRate.add(hrData[i].getMaxHeartRate()); //理쒕? ?щ컯?섍컪?ㅼ쓣 ???
         minHeartRate.add(hrData[i].getMinHeartRate()); //理쒖냼 ?щ컯?섍컪?ㅼ쓣 ???
      }
      for(int i=0;i<cnt;i++)
      {
         sumMax+=maxHeartRate.get(i); //理쒕? ?щ컯?섍컪 ?됯퇏??援ы븯湲??꾪빐 珥앺빀 怨꾩궛
         sumMin+=minHeartRate.get(i); //理쒖냼 ?щ컯?섍컪 ?됯퇏??援ы븯湲??꾪빐 珥앺빀 怨꾩궛
      }
      maxAver=sumMax/cnt; //理쒕? ?щ컯?섍컪 ?됯퇏怨꾩궛
      minAver=sumMin/cnt; //理쒖냼 ?щ컯?섍컪 ?됯퇏怨꾩궛
      
      
      return result;
   }*/
}