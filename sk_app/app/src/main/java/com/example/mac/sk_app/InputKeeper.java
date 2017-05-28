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

import static com.example.mac.sk_app.R.id.identifyNumber;
import static com.example.mac.sk_app.R.id.keeperName;

//인증번호와 키퍼의 이름을 입력하는 액티비티
public class InputKeeper extends AppCompatActivity {
    EditText idNumView,kNameView;
    String initIdentifyNumber="1234";         //초기화된 인증번호
    String initKeeperName="Jackson";         //초기화된 보호대상자의 이름
    String inputNumber,inputName,param1,param2,androidID,token;
    JoinKeeper joinKeeper;
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
        param1="getMethod=joinKeeper&=androidID="+androidID+"&inputNumber="+inputNumber+"&inputName="+inputName;
        joinKeeper=new JoinKeeper();
        joinKeeper.execute(param1);
        /*if(!sidentifyNumber.equals(initIdentifyNumber)) {               //인증번호를 잘못 입력했을 때 인증번호 오류를 알리는 액티비티로 이동
            Intent intent = new Intent(this, ErrorIdentity.class);
            startActivity(intent);
            finish();
        } else if(!skeeperName.equals(initKeeperName)) {                //키퍼이름을 잘못 입력했을 때 보호자가 연결을 거부했다고 알리는 액티비티로 이동
            Intent intent = new Intent(this, RejectKeeper.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, SuccessVerification.class);
            startActivity(intent);
            finish();

        }*/

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
            System.out.println("Result onPostExecute:"+res);
            /*results=new HashMap<String,String>();
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
            }*/
            System.out.println("Result!:"+res);

            conn.disconnect();
        }
    }
    public class sendToken extends AsyncTask<String, Void, String> {

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
            /*results=new HashMap<String,String>();
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
            }*/
            System.out.println("Result!:"+res);

            conn.disconnect();
        }
    }
}