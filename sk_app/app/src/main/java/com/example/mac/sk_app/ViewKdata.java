package com.example.mac.sk_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by 차민광01027370165 on 2017-05-11.
 */

//보호대상자가 생체정보를 보는 액티비티
public class ViewKdata extends AppCompatActivity {
    TextView heartText,walkText,walkCount,heartRate;
    String keeperID;
    HashMap<String,String> results;
    RequestKeeper rk;
    String param;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_kdata);
        Intent intent=getIntent();
        keeperID=intent.getStringExtra("keeperID");
        System.out.println("KEEPERID:"+keeperID);
        param="getMethod=sendSilverDataToKeeper&keeperID="+keeperID;
        rk=new RequestKeeper();
       rk.execute(param);
    }
    public void cancel(View view) {             //처음으로 돌아가기
        Intent intent = new Intent(this, Loading.class);
        intent.putExtra("keeperID",keeperID);
        startActivity(intent);

        finish();
    }
    public class RequestKeeper extends AsyncTask<String, Void, String> {

        private String url="http://222.108.243.141:8089/sk_tomcat/receiveData.do";
        private String result;
        private HttpURLConnection conn;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        /* "http://silverkeeper.iptime.org/sk_server/receiveSilverData";*/

        }

        @Override
        public String doInBackground(String... params) {
            try {
                URL obj = new URL(url);
                conn = (HttpURLConnection) obj.openConnection();

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
                System.out.println("asyncTask result2:"+result+"\n");
                Log.v("asyncTaskResult2",result);

            } catch (Exception e) {
                e.printStackTrace();

            }



            return result;

        }

        @Override
        protected void onPostExecute(String res)
        {
            super.onPostExecute(res);

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
            heartText=(TextView)findViewById(R.id.heart);
            heartRate=(TextView)findViewById(R.id.heartRate);
            walkText=(TextView)findViewById(R.id.walk);
            walkCount=(TextView)findViewById(R.id.walkCount);
            heartRate.setText(results.get("heartRate"));
            walkCount.setText(results.get("walkCount"));

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
            conn.disconnect();
            rk=new RequestKeeper();
            rk.execute(param);


        }


    }
}
