package connection;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.Enumeration;
import java.util.HashMap;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

import java.util.Enumeration;
import java.util.HashMap;


/**
 * created by hyunjin
 * connection of server
 * This class send pushAlarm to keeperApp.
 * 2017-05-16
 * 
 * Servlet implementation class sendPushAlarm
 */
//@WebServlet("/sendPushAlarm")
public class PushAlarm {//extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String url;
    private String hKey;
    private String bKey;

	private HashMap<String, String> dataMap;

    private HttpURLConnection conn;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PushAlarm(String key) {
        super();
        // TODO Auto-generated constructor stub
        
        url = "https://console.firebase.google.com/project/silverkeeper-ce4ae";
        hKey = "AIzaSyDiHfqd9p6byNOEhW9-KlDMXvXvbi4KSHY";
        
        bKey = key;
        bKey = "cK10vzn7dLE:APA91bFa-IzfXEYS7AIgTjEjcvRPRbDq_fl74l4KN2vnCS9bAByticmEYr5WxGxrrkN5Kn0LXt0kV3YCHKVg6Rz37TuJkI_AHbMqNee64MgGIrmbSah3xHceJUQw8tBInJjYdf_-GHYJ";
        dataMap = new HashMap<String, String>();
        //System.out.println("push");
		//pushAlarm();
		//System.out.println("good");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("push");
		//pushAlarm();
		System.out.println("good");
	}*/
	
	public Void pushAlarm() {
        try {
            URL obj = new URL(url);
            conn = (HttpURLConnection) obj.openConnection();
            
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestProperty("Authorization","key="+ hKey);

            //String str = "{\"to\" : \""+bKey+"\",  \"priority\" : \"high\",  \"notification\" : {    \"body\" : \"Background Message\",    \"title\" : \"BG Title\"  },  \"data\" : {    \"title\" : \"FG Title\",    \"message\" : \"Foreground Message\"  }}";
           
            JSONObject json = new JSONObject();
            JSONObject info = new JSONObject();

            info.put("body", "푸쉬 발송 테스트 입니다."); // Notification body

            json.put("notification", info);
            json.put("to", bKey); // deviceID

            try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())){
    //혹시나 한글 깨짐이 발생하면 
    //try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){ 인코딩을 변경해준다.

                wr.write(json.toString());
                wr.flush();
            }catch(Exception e){
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();
            


        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;

    }

}
