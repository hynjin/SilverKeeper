package conn.silver.vo;
import java.sql.*;

public class SilverVO {

private int heartRate;
private int walkCount;
//private int identifyNumber;
//private int currentTime;
private Date currentTime;
private boolean checkMiBand;

public SilverVO()
{

this.heartRate=1;
this.walkCount=0;
//this.identifyNumber=0;
this.currentTime=new Date(System.currentTimeMillis());
this.checkMiBand=true;
}
public SilverVO(int heartRate, int walkCount, int identifyNumber,Date currentTime,boolean checkMiBand)
{

this.heartRate=heartRate;
this.walkCount=walkCount;
//this.identifyNumber=identifyNumber;
this.currentTime=currentTime;
this.checkMiBand=checkMiBand;
}

public int getHeartRate()
{
return this.heartRate;
}
public void setHeartRate(int heartRate)
{
this.heartRate=heartRate;
return;
}
public int getWalkCount() {
return walkCount;
}
public void setWalkCount(int walkCount) {
this.walkCount = walkCount;
}
/*public int getIdentifyNumber()
{
return this.identifyNumber;
}
public void setIdentifyNumber(int identifyNumber)
{
this.identifyNumber=identifyNumber;
return;
}
*/
/*public int getCurrentTime() {
return currentTime;
}*/
public Date getCurrentTime() {
return currentTime;
}
public void setCurrentTime(Date currentTime) {
this.currentTime = currentTime;
}
public boolean getCheckMiBand()
{
return this.checkMiBand;
}
public void setCheckMiBand(boolean checkMiBand)
{
this.checkMiBand=checkMiBand;
}
public String toString()
{
return "heartRate="+this.heartRate+"&"+
        "walkCount="+this.walkCount+"&"+
        /*"identifyNumber="+this.identifyNumber+"&"+*/
        "currentTime="+this.currentTime+"&"+
        "checkMiBand="+this.checkMiBand;
}
/*public String toStringDB()
{
//checkMiBand가 끝에 있으면 true 값이 null로 넘어감..왜지..중간에 있으면 문제 없음!
return this.heartRate+","+this.walkCount+","+this.identifyNumber+","+this.checkMiBand+","+"'"+this.currentTime+"'";
}
*/
}
