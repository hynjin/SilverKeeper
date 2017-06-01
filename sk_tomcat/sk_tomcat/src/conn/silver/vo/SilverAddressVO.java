package conn.silver.vo;

import java.util.Arrays;

public class SilverAddressVO {
	private String silverToken;
	private String rassberryPiURL;
	private String wifiSSID;
	
	public SilverAddressVO()
	{
		this.silverToken = null;
		this.rassberryPiURL = "";
		this.wifiSSID = null;
	}
	
	public SilverAddressVO(String silverToken, String rassberryPiURL, String wifiSSID) 
	{
		this.silverToken = silverToken;
		
		this.rassberryPiURL = rassberryPiURL;
		this.wifiSSID = wifiSSID;
	}

	public String getSilverToken() {
		return silverToken;
	}

	public void setSilverToken(String silverToken) {
		this.silverToken = silverToken;
	}

	public String getRassberryPiURL() {
		return rassberryPiURL;
	}

	public void setRassberryPiURL(String rassberryPiURL) {
		this.rassberryPiURL = rassberryPiURL;
	}

	public String getWifiSSID() {
		return wifiSSID;
	}

	@Override
	public String toString() {
		return "SilverAddressVO [silverToken=" + silverToken
				+ ", rassberryPiURL=" + rassberryPiURL + ", wifiSSID="
				+ wifiSSID + "]";
	}

	public void setWifiSSID(String wifiSSID) {
		this.wifiSSID = wifiSSID;
	}
	
}
