package com.example.mac.sk_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;

import static android.R.attr.value;

public class Loading extends AppCompatActivity {

    String androidID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        if(getIntent().getStringExtra("KeeperAndroidId")!=null)
        {
            System.out.println("Loading:SilverID:"+getIntent().getStringExtra("silverID"));
            System.out.println("Loading:KeeperAndroidId:"+getIntent().getStringExtra("KeeperAndroidId"));
            System.out.println("Loading:keeperName:"+getIntent().getStringExtra("keeperName"));
            Intent intent=new Intent(this,CheckJoinKeeper.class);
            intent.putExtra("silverID",getIntent().getStringExtra("silverID"));
            intent.putExtra("KeeperAndroidId",getIntent().getStringExtra("KeeperAndroidId"));
            intent.putExtra("keeperName",getIntent().getStringExtra("keeperName"));
            startActivity(intent);
            finish();

        }
        else if(getIntent().getStringExtra("raspIP")!=null)
        {
            Intent intent=new Intent(this,ViewStreamingActivity.class);
            intent.putExtra("raspIP",getIntent().getStringExtra("raspIP"));
            intent.putExtra("keeperID",getIntent().getStringExtra("keeperID"));
            startActivity(intent);
            finish();

        }
        else
            LoadingActivity();

    }
    private void LoadingActivity() {
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                androidID=android.provider.Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);

               /* MyFireBaseInstanceIDService createToken= new MyFireBaseInstanceIDService();
                createToken.onTokenRefresh();
                String token=createToken.getToken();*/
                String param = "getMethod=checkJoin&androidID="+androidID;//+"&token="+token;
                AppClient appClient = new AppClient();

                appClient.execute(param);
                // finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 3000);
    }
    public class AppClient extends AsyncTask<String, Void, String> {

        private String url=URLData.url;//"http://222.108.243.141:8089/sk_tomcat/receiveData.do";
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
             /*   BufferedReader rd=new BufferedReader(new InputStreamReader(in));
                String line;
                while((line=rd.readLine())!=null) {

                }*/
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
            HashMap<String,String> results=new HashMap<String,String>();
            StringTokenizer st=new StringTokenizer(result,"&");
            while(st.hasMoreTokens())
            {
                String data = st.nextToken();
                String temp=data;
                StringTokenizer st2=new StringTokenizer(temp,"=");
                while(st2.hasMoreTokens())
                {
                    String key=st2.nextToken();
                    String value=st2.nextToken();
                    results.put(key,value);
                }
            }
            System.out.println("value:"+value);
            Intent intent;
            String res=results.get("result");
            if(res.contains("new"))
           {
                intent=new Intent(Loading.this, ChoiceRole.class);
               System.out.println("res:"+res+" value="+results.get("androidID"));
               intent.putExtra("androidID",androidID);
               startActivity(intent);
            }
            else if(res.contains("silver"))
            {
                intent=new Intent(Loading.this, ViewData.class);
                System.out.println("res:"+res+" value="+results.get("silverID"));
                intent.putExtra("silverID",results.get("silverID"));
                startActivity(intent);
            }
            else if(res.contains("keeper")) {
                intent=new Intent(Loading.this, ViewKdata.class);
                System.out.println("res:"+res+" value="+results.get("keeperID"));
                intent.putExtra("keeperID",results.get("keeperID"));
                startActivity(intent);
            }
            finish();
            return result;

        }

        @Override
        protected void onPostExecute(String res)
        {
            super.onPostExecute(res);
            System.out.println("Result!:"+res);


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


        }


    }
}
