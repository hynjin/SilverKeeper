package conn.silver.vo;

import java.sql.Date;


public class SilverHeartRateVO {
	private int maxHeartRate;
	private int minHeartRate;
	private Date currentTime;
	
	public SilverHeartRateVO() 
	{
		this.maxHeartRate = -1;
		this.minHeartRate = -1;
		this.currentTime=new Date(System.currentTimeMillis());
	}
	public SilverHeartRateVO(int maxHeartRate, int minHeartRate,Date currentTime) 
	{
		
		this.maxHeartRate = maxHeartRate;
		this.minHeartRate = minHeartRate;
		this.currentTime=currentTime;
	}

	public int getMaxHeartRate() {
		return maxHeartRate;
	}
	public void setMaxHeartRate(int maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}
	public int getMinHeartRate() {
		return minHeartRate;
	}
	public void setMinHeartRate(int minHeartRate) {
		this.minHeartRate = minHeartRate;
	}
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	@Override
	public String toString() {
		return "SilverHeartRateVO [maxHeartRate=" + maxHeartRate
				+ ", minHeartRate=" + minHeartRate + ", currentTime="
				+ currentTime + "]";
	}

}
