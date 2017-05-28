package com.example.mac.sk_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckJoinKeeper extends AppCompatActivity {
    Button yesBtn,noBtn;
    TextView keeperName;
    String kName, silverAndroidId, keeperAndroidId,param;
    CheckJoinKeeperAsync cjk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        kName=getIntent().getStringExtra("keeperName");
        keeperName=(TextView)findViewById(R.id.keeperName);
        silverAndroidId=getIntent().getStringExtra("silverAndroidId");
        keeperAndroidId=getIntent().getStringExtra("keeperAndroidId");
        yesBtn=(Button)findViewById(R.id.yesBtn);
        noBtn=(Button)findViewById(R.id.noBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String param="getMethod=checkJoinKeeper&keeperAndroidID="+keeperAndroidId+"&silverAndroidId="+silverAndroidId;
                cjk=new CheckJoinKeeperAsync();
                cjk.execute(param);

            }
        });
        setContentView(R.layout.activity_check_join_keeper);


    }
    public class CheckJoinKeeperAsync extends AsyncTask<String, Void, String> {

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
           /* HashMap<String,String> results=new HashMap<String,String>();
            StringTokenizer st=new StringTokenizer(result,"&");
            while(st.hasMoreTokens())
            {
                String data = st.nextToken();
                String temp=data;
                StringTokenizer st2=new StringTokenizer(temp,"=");
                while(st2.hasMoreTokens())
                {
                    results.put(st2.nextToken(),st2.nextToken());
                }
            }*/




            return result;

        }

        @Override
        protected void onPostExecute(String res)
        {
            super.onPostExecute(res);
            System.out.println("Result!:"+res);


            conn.disconnect();


        }


    }
}
