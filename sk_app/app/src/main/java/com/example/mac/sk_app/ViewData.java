package com.example.mac.sk_app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jellygom.miband_sdk.MiBandIO.Listener.HeartrateListener;
import com.jellygom.miband_sdk.MiBandIO.Listener.NotifyListener;
import com.jellygom.miband_sdk.MiBandIO.MibandCallback;
import com.jellygom.miband_sdk.MiBandIO.Model.UserInfo;
import com.jellygom.miband_sdk.Miband;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;
/**
 * Created by 차민광01027370165 on 2017-05-11.
 */

//보호자가 생체정보를 보는 화면
public class ViewData extends AppCompatActivity {

    TextView heartText,walkText,walkCount,heartRate,testIdNum;
    ImageView statusView;
    String silverID;
    HashMap<String,String> results;
    RequestSilver rs;
    SendSilverData ssd;
    RequestSilverStatus rss;
    String param1,param2,param3;
    String identifyNumber;
    Random rand=new Random();
    int wCount,hRate,conM;
    boolean connMiBand;

    private static final String TAG = ViewData.class.getSimpleName();
    private Miband miband;
    private BluetoothAdapter mBluetoothAdapter;


    //private String url=URLData.url;//"http://222.108.243.141:8089/sk_tomcat/receiveData.do";
    //private String result;
    //private HttpURLConnection conn;

    /*private RealtimeStepListener realtimeStepListener = new RealtimeStepListener() {
        @Override
        public void onNotify(final int steps) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    step.setText(steps + " steps");
                    text.append(steps + " steps\n");
                }
            });
        }
    };*/

    private HeartrateListener heartrateNotifyListener = new HeartrateListener() {
        @Override
        public void onNotify(final int heartRate) {
            hRate = heartRate;
            param2="getMethod=receiveSilverData&silverID="+silverID;
            param2+="&heartRate="+hRate+"&walkCount="+wCount+"&currentTime="+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(System.currentTimeMillis()))+"&connMiBand="+connMiBand;
            System.out.println("param2:"+param2);
            ssd=new SendSilverData();
            ssd.execute(param2);
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(heartRate >=0)
                        heartText.setText(heartRate + " bpm");
                    //text.append(heartRate + " bpm\n");
                }
            });*/
        }
    };

    /*private BatteryListener batteryListener = new BatteryListener() {
        @Override
        public void onNotify(final int level) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    battery.setText(level + " %");
                    text.append(level + " %\n");
                }
            });
        }
    };*/

    private final MibandCallback mibandCallback = new MibandCallback() {
        @Override
        public void onSuccess(Object data, int status) {
            switch (status) {
                case MibandCallback.STATUS_SEARCH_DEVICE:
                    Log.e(TAG, "성공: STATUS_SEARCH_DEVICE");
                    miband.connect((BluetoothDevice) data, this);
                    break;
                case MibandCallback.STATUS_CONNECT:
                    Log.e(TAG, "성공: STATUS_CONNECT");
                    //miband.setHeartRateScanListener(heartrateNotifyListener);
                    miband.getUserInfo(this);
                    break;
                case MibandCallback.STATUS_SEND_ALERT:
                    Log.e(TAG, "성공: STATUS_SEND_ALERT");
                    break;
                case MibandCallback.STATUS_GET_USERINFO:
                    Log.e(TAG, "성공: STATUS_GET_USERINFO"+((BluetoothGattCharacteristic) data).getValue());
                    UserInfo userInfo = new UserInfo().fromByteData(((BluetoothGattCharacteristic) data).getValue());
                    miband.setUserInfo(userInfo, this);
                    break;
                case MibandCallback.STATUS_SET_USERINFO:
                    Log.e(TAG, "성공: STATUS_SET_USERINFO");
                    miband.setHeartRateScanListener(heartrateNotifyListener);
                    break;
                case MibandCallback.STATUS_START_HEARTRATE_SCAN:
                    Log.e(TAG, "성공: STATUS_START_HEARTRATE_SCAN");
                    Log.v("zz",data.toString());
                    //heartrateNotifyListener.onNotify((int)data);
                    break;
                //case MibandCallback.STATUS_GET_BATTERY:
                //    Log.e(TAG, "성공: STATUS_GET_BATTERY");
                //    final int level = (int) data;
                //    runOnUiThread(new Runnable() {
                //        @Override
                //        public void run() {
                //            battery.setText(level+ " % battery");
                //            text.append(level + " % battery\n");
                //        }
                //    });
                //    break;
                //case MibandCallback.STATUS_GET_ACTIVITY_DATA:
                //    Log.e(TAG, "성공: STATUS_GET_ACTIVITY_DATA");
                //    final int steps = (int) data;
                //    runOnUiThread(new Runnable() {
                //        @Override
                //        public void run() {
                //            step.setText(steps+ " steps");
                //            text.append(steps+ " steps\n");
                //        }
                //    });
                //    break;
            }
        }

        @Override
        public void onFail(int errorCode, String msg, int status) {
            switch (status) {
                case MibandCallback.STATUS_SEARCH_DEVICE:
                    Log.e(TAG, "실패: STATUS_SEARCH_DEVICE");
                    break;
                case MibandCallback.STATUS_CONNECT:
                    Log.e(TAG, "실패: STATUS_CONNECT");
                    break;
                case MibandCallback.STATUS_SEND_ALERT:
                    Log.e(TAG, "실패: STATUS_SEND_ALERT");
                    break;
                case MibandCallback.STATUS_GET_USERINFO:
                    Log.e(TAG, "실패: STATUS_GET_USERINFO");
                    break;
                case MibandCallback.STATUS_SET_USERINFO:
                    Log.e(TAG, "실패: STATUS_SET_USERINFO");
                    break;
                case MibandCallback.STATUS_START_HEARTRATE_SCAN:
                    Log.e(TAG, "실패: STATUS_START_HEARTRATE_SCAN");
                    break;
                //case MibandCallback.STATUS_GET_BATTERY:
                //    Log.e(TAG, "실패: STATUS_GET_BATTERY");
                //    break;
                //case MibandCallback.STATUS_GET_ACTIVITY_DATA:
                //    Log.e(TAG, "실패: STATUS_GET_ACTIVITY_DATA");
                //    break;
            }
        }
    };



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        miband = new Miband(getApplicationContext());

        mBluetoothAdapter = ((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();

        //miband = new Miband(getApplicationContext());

        miband.searchDevice(mBluetoothAdapter, this.mibandCallback);

        miband.setDisconnectedListener(new NotifyListener() {
            @Override
            public void onNotify(byte[] data) {
                miband.searchDevice(mBluetoothAdapter, mibandCallback);
            }
        });




        setContentView(R.layout.view_data);
        Intent intent=getIntent();
        silverID=intent.getStringExtra("silverID");
        identifyNumber=intent.getStringExtra("identifyNumber");
        System.out.println("SILVERID:"+silverID);
        param1="getMethod=sendSilverData&silverID="+silverID;
        rs=new RequestSilver();
        //param2="getMethod=receiveSilverData&silverID="+silverID;
        //hRate=rand.nextInt(101)+50;
        //wCount=rand.nextInt(201);
        //conM=rand.nextInt(2);
        //if(conM==1)
        //{
        //    connMiBand=true;
        //}
        //else connMiBand=false;

        //param2+="&heartRate="+hRate+"&walkCount="+wCount+"&currentTime="+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(System.currentTimeMillis()))+"&connMiBand="+connMiBand;
        rs.execute(param1);
        //ssd=new SendSilverData();
        //ssd.execute(param2);
        param3="getMethod=checkEmergency&silverID="+silverID;
        rss=new RequestSilverStatus();
        rss.execute(param3);

        miband.startHeartRateScan(1, mibandCallback,heartrateNotifyListener);
        miband.setHeartRateScanListener(heartrateNotifyListener);
    }
    public void ok(View view) {
        Intent intent = new Intent(this, CheckIdentity.class);
        intent.putExtra("silverID",silverID);
        intent.putExtra("identifyNumber",identifyNumber);
        startActivity(intent);

        finish();
    }
    public void cancel(View view) {             //처음으로 돌아가기
        Intent intent = new Intent(this, Loading.class);
        intent.putExtra("silverID",silverID);
        startActivity(intent);

        finish();
    }
    public class RequestSilver extends AsyncTask<String, Void, String> {
        private String url=URLData.url;;//"http://222.108.243.141:8089/sk_tomcat/receiveData.do";
        //private String result;
        private HttpURLConnection conn;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                URL obj = new URL(url);
                conn = (HttpURLConnection) obj.openConnection();
                Thread.sleep(1000);
            }
            catch(Exception e)
            {}


        /* "http://silverkeeper.iptime.org/sk_server/receiveSilverData";*/

        }

        @Override
        public String doInBackground(String... params) {
            return BackgroundDoing(conn,params);
        }
        @Override
        protected void onPostExecute(String res)
        {
            super.onPostExecute(res);
            System.out.println("Result! RequestSilver:"+res);
            results=new HashMap<String,String>();
            StringTokenizer st=new StringTokenizer(res,"&");
            while(st.hasMoreTokens())
            {
                String data = st.nextToken();
                String temp=data;
                StringTokenizer st2=new StringTokenizer(temp,"=");
                while(st2.hasMoreTokens())
                {
                    String key=st2.nextToken();
                    String value=st2.nextToken();
                    System.out.println("key:"+key);
                    System.out.println("value:"+value);
                    results.put(key,value);
                }
            }
            System.out.println("Result!:"+res);
            heartRate=(TextView)findViewById(R.id.heartRate2);
            walkCount=(TextView)findViewById(R.id.walkCount2);
            testIdNum=(TextView)findViewById(R.id.testIdNum);
            heartRate.setText(results.get("heartRate"));
            walkCount.setText(results.get("walkCount"));
            testIdNum.setText(results.get("identifyNumber"));
            identifyNumber=results.get("identifyNumber");
           /* Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements())
            {
                String name = (String) parameterNames.nextElement();
                if(name.equals("getMethod"))
                    type = request.getParameter(name);
                else
                    dataMap.put(name, request.getParameter(name));
                System.out.println(name + "=" + request.getParameter(name));
            }
            return dataMap;*/

            rs=new RequestSilver();
            rs.execute(param1);
           conn.disconnect();
       }
   }
    public class RequestSilverStatus extends AsyncTask<String, Void, String> {
        private String url=URLData.url;;//"http://222.108.243.141:8089/sk_tomcat/receiveData.do";
        //private String result;
        private HttpURLConnection conn;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                URL obj = new URL(url);
                conn = (HttpURLConnection) obj.openConnection();
                Thread.sleep(1000);
            }
            catch(Exception e)
            {}


        /* "http://silverkeeper.iptime.org/sk_server/receiveSilverData";*/

        }

        @Override
        public String doInBackground(String... params) {
            return BackgroundDoing(conn,params);
        }
        @Override
        protected void onPostExecute(String res)
        {
            super.onPostExecute(res);
            StringTokenizer st=new StringTokenizer(res,"=");
            statusView=(ImageView)findViewById(R.id.statusImage);
                String status = st.nextToken();
                String value = st.nextToken();
            if(value.contains("emergency"))
            {
                statusView.setImageResource(R.drawable.red);
            }
            else if(value.contains("warning"))
            {
                statusView.setImageResource(R.drawable.yellow);
            }
            else if(value.contains("safe"))
            {
                statusView.setImageResource(R.drawable.green);
            }
            System.out.println("Result status!:"+res);
            rss=new RequestSilverStatus();
            rss.execute(param3);
            conn.disconnect();
        }
    }
    public class SendSilverData extends AsyncTask<String, Void, String> {
        private String url=URLData.url;;//"http://222.108.243.141:8089/sk_tomcat/receiveData.do";
        //private String result;
        private HttpURLConnection conn;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                URL obj = new URL(url);
                conn = (HttpURLConnection) obj.openConnection();
                //Thread.sleep(1000);
            }
            catch(Exception e)
            {}


        /* "http://silverkeeper.iptime.org/sk_server/receiveSilverData";*/

        }

        @Override
        public String doInBackground(String... params) {
            return BackgroundDoing(conn,params);
        }
        @Override
        protected void onPostExecute(String res)
        {
            super.onPostExecute(res);
            System.out.println("Result!:"+res);

            //hRate=rand.nextInt(101)+50;

            miband.startHeartRateScan(1, mibandCallback,heartrateNotifyListener);
            miband.setHeartRateScanListener(heartrateNotifyListener);
            wCount=rand.nextInt(201);
            conM=1;//rand.nextInt(2);

            if(conM==1)
            {
                connMiBand=true;
            }
            else connMiBand=false;
            //param2="getMethod=receiveSilverData&silverID="+silverID;
            //param2+="&heartRate="+hRate+"&walkCount="+wCount+"&currentTime="+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(System.currentTimeMillis()))+"&connMiBand="+connMiBand;
            //ssd=new SendSilverData();
            //ssd.execute(param2);
            conn.disconnect();
        }
    }

    public String BackgroundDoing(HttpURLConnection conn,String... params){
        String result = new String();
        try {
            //URL obj = new URL(url);
            //conn = (HttpURLConnection) obj.openConnection();

                /*conn.setReadTimeout(10000);*/
            /*conn.setConnectTimeout(15000);*/
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf-8");


            OutputStream out = conn.getOutputStream();
            String test ="";
            for(int i=0;i<params.length;i++)
                test = test + params[i];
            Log.v("///",test);
                /*System.out.println()*/
            out.write(test.getBytes());
            out.flush();
            out.close();
            conn.connect();

            InputStream in = conn.getInputStream();

            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            byte[] buf = new byte[1024 * 8];
            int length = 0;
            while ((length = in.read(buf)) != -1) {
                bout.write(buf, 0, length);
            }

            System.out.println(new String(bout.toByteArray(), "UTF-8"));
            Log.w("server",new String(bout.toByteArray(), "UTF-8"));

            result=new String(bout.toByteArray(), "UTF-8");
            System.out.println("asyncTask result:"+result+"\n");
            Log.v("asyncTaskResult",result);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;
    }

}
