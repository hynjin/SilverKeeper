package com.example.mac.sk_app;

/**
 * Created by mac on 2017. 5. 6..
 */


import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import android.util.Log;

import android.os.AsyncTask;



public class AppClient extends AsyncTask<String, Void, Void> {

    private String url;
    private String result;

    private HttpURLConnection conn;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        url = "http://silverkeeper.iptime.org/sk_server/receiveSilverData";

    }

    @Override
    public Void doInBackground(String... params) {
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
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");


            OutputStream out = conn.getOutputStream();
            String test ="";
            for(int i=0;i<params.length;i++)
                test = test + params[i];
            Log.v("///",test);
            out.write(test.getBytes());
            out.close();

            InputStream in = conn.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buf = new byte[1024 * 8];
            int length = 0;
            while ((length = in.read(buf)) != -1) {
                bout.write(buf, 0, length);
            }
            System.out.println(new String(bout.toByteArray(), "UTF-8"));
            Log.w("server",new String(bout.toByteArray(), "UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        conn.disconnect();
        System.out.println(result);
    }


}



