package conn.silver.vo;

import java.sql.Date;


public class SilverHeartRateVO {
	private int maxHeartRate;
	private int minHeartRate;
	private String currentTime;
	
	public SilverHeartRateVO() 
	{
		this.maxHeartRate = -1;
		this.minHeartRate = -1;
		this.currentTime="";
	}
	public SilverHeartRateVO(int maxHeartRate, int minHeartRate,String currentTime) 
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
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	@Override
	public String toString() {
		return "SilverHeartRateVO [maxHeartRate=" + maxHeartRate
				+ ", minHeartRate=" + minHeartRate + ", currentTime="
				+ currentTime + "]";
	}

}
