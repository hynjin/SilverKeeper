package com.example.mac.sk_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;

//라즈베리파이의 맥주소를 입력하는 액티비티
public class InputRasp extends AppCompatActivity {

    //String init="12345678";         //초기화한 라즈베리파이의 맥주소

    EditText raspMac;
    String androidID,raspIP,param,token,silverID,idNum;
    JoinSilver joinSilver;
    Button ok,cancel;
    Intent intent;
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_rasp);
        raspMac = (EditText) findViewById(R.id.raspMAC);
        intent=getIntent();
        context=this;
    }
    public void ok(View view) {

        raspIP=raspMac.getText().toString();
        System.out.println("raspIP:"+raspIP);

        androidID=android.provider.Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        System.out.println("androidID:"+androidID);
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage("입력 주소:"+raspIP+"\n"+"입력하신 값이 맞습니까?");
        builder.setTitle("입력 확인");
        builder.setPositiveButton("예",new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog,int which)
            {
                MyFireBaseInstanceIDService createToken= new MyFireBaseInstanceIDService();
                createToken.onTokenRefresh();
                token=createToken.getToken();
                param="getMethod=joinSilver&androidID="+androidID+"&token="+token+"&raspIP="+raspIP;
                joinSilver=new JoinSilver();
                joinSilver.execute(param);

            }

        }
        );
        builder.setNegativeButton("아니오",new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog,int which) {

                dialog.cancel();
            }
        });
        builder.create().show();

        /*if(raspIP.equals(init)) {                            //라즈베리파이의 맥주소를 올바르게 입력했을 때 라즈베리 맥주소 인증 성공을 알리는 액티비티로 이동
             intent= new Intent(this, SuccessRasp.class);
            startActivity(intent);
            finish();
        }*/
/*        else {                                                  //라즈베리파이의 맥주소를 잘못 입력했을 때 맥주소 입력 오류를 알리는 액티비티로 이동
            intent = new Intent(this, ErrorRasp.class);
            startActivity(intent);
            finish();
        }*/
    }
    public void cancel(View view) {
        Intent intent = new Intent(this, AgreeData.class);
        startActivity(intent);

        finish();
    }
    public class JoinSilver extends AsyncTask<String, Void, String> {

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
                ByteArrayOutputStream bout = new ByteArrayOutputStream();

                byte[] buf = new byte[1024 * 8];
                int length = 0;
                while ((length = in.read(buf)) != -1) {
                    bout.write(buf, 0, length);
                }

                System.out.println(new String(bout.toByteArray(), "UTF-8"));
                Log.w("server",new String(bout.toByteArray(), "UTF-8"));

                result=new String(bout.toByteArray(), "UTF-8");
                System.out.println("asyncTask result!!!:"+result+"\n");
                Log.v("asyncTaskResult!!!!!!!",result);

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
                    String key=st2.nextToken();
                    String value=st2.nextToken();
                    results.put(key,value);
                }
            }
            String result=results.get("result");
            if(result.contains("fail"))
            {
                System.out.println("result-fail");
                intent=new Intent(context, AgreeData.class);
                intent.putExtra("androidID",androidID);
                startActivity(intent);
                finish();
            }
            else
            {
                System.out.println("result-success");
                silverID=results.get("silverID");
                AlertDialog.Builder builder2=new AlertDialog.Builder(context);
                builder2.setMessage("보호대상자 등록이 완료되었습니다 : "+silverID);
                builder2.setTitle("등록 완료");
                builder2.setNeutralButton("확인",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                intent=new Intent(context,ViewData.class);
                                intent.putExtra("silverID",silverID);
                                startActivity(intent);
                                finish();

                            }
                        }
                );
                builder2.create().show();

            }*/

            conn.disconnect();
            return result;
        }
        @Override
        protected void onPostExecute(String res) {
            super.onPostExecute(res);
            System.out.println("Result!:" + res);
            HashMap<String, String> results = new HashMap<String, String>();
            StringTokenizer st = new StringTokenizer(result, "&");
            while (st.hasMoreTokens()) {
                String data = st.nextToken();
                String temp = data;
                StringTokenizer st2 = new StringTokenizer(temp, "=");
                while (st2.hasMoreTokens()) {
                    String key = st2.nextToken();
                    String value = st2.nextToken();
                    results.put(key, value);
                }
            }

            System.out.println("result-success");
            silverID = results.get("silverID");
            idNum=results.get("identifyNumber");
            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
            builder2.setMessage("보호대상자 등록이 완료되었습니다 : " + silverID + "\n식별번호:"+idNum);
            builder2.setTitle("등록 완료");
            builder2.setNeutralButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            intent = new Intent(context, ViewData.class);
                            intent.putExtra("silverID", silverID);
                            intent.putExtra("identifyNumber",idNum);
                            startActivity(intent);
                            finish();

                        }
                    }
            );
            builder2.create().show();


            conn.disconnect();

        }

    }

}