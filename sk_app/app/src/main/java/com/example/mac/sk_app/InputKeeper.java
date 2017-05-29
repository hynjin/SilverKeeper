package com.example.mac.sk_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;

import static com.example.mac.sk_app.R.id.identifyNumber;
import static com.example.mac.sk_app.R.id.keeperName;

//인증번호와 키퍼의 이름을 입력하는 액티비티
public class InputKeeper extends AppCompatActivity {
    EditText idNumView,kNameView;
    String inputNumber,inputName,param1,param2,androidID,token,keeperID;
    JoinKeeper joinKeeper;
    CheckCreateID ccid;
    MyFireBaseInstanceIDService createToken;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_keeper);
        androidID=getIntent().getStringExtra("androidID");

    }
    public void ok(View view) {
        idNumView = (EditText) findViewById(identifyNumber);
        kNameView = (EditText) findViewById(keeperName);
        //인증번호를 레이아웃에서 불러오기
        inputNumber=idNumView.getText().toString();
         //키퍼이름을 레이아웃에서 불러오기
        inputName=kNameView.getText().toString();
        System.out.println("inputNumber:"+inputNumber);
        System.out.println("inputName:"+inputName);
        MyFireBaseInstanceIDService createToken= new MyFireBaseInstanceIDService();
        createToken.onTokenRefresh();
        token=createToken.getToken();
        param1="getMethod=joinKeeper&androidID="+androidID+"&inputNumber="+inputNumber+"&inputName="+inputName;
        param2="getMethod=checkCreateKeeperID&androidID="+androidID+"&token="+token;
        joinKeeper=new JoinKeeper();
        joinKeeper.execute(param1);

    }
    public void cancel(View view) {
        Intent intent = new Intent(this, ChoiceKeeper.class);
        startActivity(intent);

        finish();
    }
    public class JoinKeeper extends AsyncTask<String, Void, String> {

        private String url=URLData.url;
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
                System.out.println("asyncTask result:"+result+"\n");
                Log.v("asyncTaskResult",result);

            } catch (Exception e) {
                e.printStackTrace();

            }
            return result;
        }
        @Override
        protected void onPostExecute(String res)
        {
            super.onPostExecute(res);

            ccid=new CheckCreateID();
            ccid.execute(param2);

        }
    }
    public class CheckCreateID extends AsyncTask<String, Void, String> {

        private String url=URLData.url;
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
                System.out.println("asyncTask result:"+result+"\n");
                Log.v("asyncTaskResult",result);

            } catch (Exception e) {
                e.printStackTrace();

            }
            return result;
        }
        @Override
        protected void onPostExecute(String res)
        {
            super.onPostExecute(res);
            System.out.println("Result onPostExecute:"+res);
            StringTokenizer st=new StringTokenizer(res,"=");

                String key = st.nextToken();
                String result=st.nextToken();

                System.out.println("Result!:"+res);
                if(result.contains("created"))
                {
                    keeperID="KP_"+androidID;
                   Intent intent=new Intent(InputKeeper.this,ViewKdata.class);
                    intent.putExtra("keeperID",keeperID);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    ccid=new CheckCreateID();
                    ccid.execute(param2);
                }

            conn.disconnect();
        }
    }
}