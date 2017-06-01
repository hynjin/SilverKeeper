package com.example.mac.sk_app; /**
 *
 * 푸시메세지가 들어왔을때 실제 사용자에게 푸시알림을 만들어서 띄워주는 클래스 입니다.
 * Api를 통해 푸시 알림을 전송하면 입력한 내용이 message에 담겨서 오게 됩니다.
 *
 *
 * Created by mac on 2017. 5. 17..
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.StringTokenizer;

public class MyFireBaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";

    private String msg;
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));

    // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
        msg=remoteMessage.getNotification().getBody();
        System.out.println("msg:"+msg);
        HashMap<String,String> hashMap=new HashMap<String,String>();
        StringTokenizer st=new StringTokenizer(msg,"|");
        msg=st.nextToken();
        System.out.println("message:::::"+msg);
        while(st.hasMoreTokens())
        {
            String temp=st.nextToken();
            StringTokenizer st2=new StringTokenizer(temp,"=");
            String key=st2.nextToken();
            System.out.println("key:::"+key);
            String value=st2.nextToken();
            System.out.println("value:::"+value);
            hashMap.put(key,value);
        }

        Intent intent=new Intent(this,Loading.class);
        if(hashMap.get("role").contains("joinKeeper"))
        {
            intent.putExtra("silverID",hashMap.get("silverID"));
            intent.putExtra("KeeperAndroidId",hashMap.get("KeeperAndroidId"));
            intent.putExtra("keeperName",hashMap.get("keeperName"));
        }
        else if(hashMap.get("role").contains("viewStreaming"))
        {
            //라즈베리파이 스트리밍에 관한 처리 필요.
            intent.putExtra("keeperID",hashMap.get("keeperID"));
            intent.putExtra("raspIP",hashMap.get("raspIP"));
        }
        //intent.putExtra("silverID",hashMap.get("silverID"));
        System.out.println(hashMap.get("silverID")+","+hashMap.get("KeeperAndroidId"));
       /* intent.putExtra("KeeperAndroidId",hashMap.get("KeeperAndroidId"));
        intent.putExtra("keeperName",hashMap.get("keeperName"));*/
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        ///////
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());


    }





        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        //sendPushNotification(remoteMessage.getData().get("message"));

    //메세지 받아서 작업 표시줄에 표시
   /*private void showNotification(String title, String message) {


        //태영 추가. message 분석해서 그에 따라 작업 나누기.
        HashMap<String,String> hashMap=new HashMap<String,String>();
        StringTokenizer st=new StringTokenizer(message,"|");
        message=st.nextToken();
        System.out.println("message:::::"+message);
        while(st.hasMoreTokens())
        {
            String temp=st.nextToken();
            StringTokenizer st2=new StringTokenizer(temp,"=");
            String key=st2.nextToken();
            System.out.println("key:::"+key);
            String value=st2.nextToken();
            System.out.println("value:::"+value);
            hashMap.put(key,value);
        }
        Intent intent=null;
        if(!hashMap.get("role").contains("joinKeeper"))
        {

        }
      intent=new Intent(this,CheckJoinKeeper.class);
        intent.putExtra("silverID",hashMap.get("silverID"));
        System.out.println(hashMap.get("silverID")+","+hashMap.get("KeeperAndroidId"));
        intent.putExtra("KeeperAndroidId",hashMap.get("KeeperAndroidId"));
        else if(hashMap.get("role").contains("viewStreaming"))
        {
            //라즈베리파이 스트리밍에 관한 처리 필요.
        }
       else
            {
               // intent=new Intent(this,Loading.class);
            }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        ///////
       Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }*/


/*    private void sendPushNotification(String message) {
        System.out.println("received message : " + message);
        Intent intent = new Intent(this, Loading.class);//MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0, notificationBuilder.build());
    }*/

}