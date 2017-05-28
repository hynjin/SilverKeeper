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
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
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

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        //sendPushNotification(remoteMessage.getData().get("message"));
    }
    //메세지 받아서 작업 표시줄에 표시
    private void showNotification(String title, String message) {
        Intent intent = new Intent(this, Loading.class);//MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        //태영 추가. message 분석해서 그에 따라 작업 나누기.
      /*  StringTokenizer st=new StringTokenizer(message,"|");
            message = st.nextToken();
            String role=st.nextToken();
            String androidID=st.nextToken();*/




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
    }


    //private void sendPushNotification(String message) {
    //    System.out.println("received message : " + message);
    //    Intent intent = new Intent(this, Loading.class);//MainActivity.class);
    //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    //    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
    //            PendingIntent.FLAG_ONE_SHOT);

    //    Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    //    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
    //            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark/*R.drawable.nofi*/).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher) )
    //            .setContentTitle("Push Title ")
    //            .setContentText(message)
    //            .setAutoCancel(true)
    //            .setSound(defaultSoundUri).setLights(000000255,500,2000)
    //            .setContentIntent(pendingIntent);

    //    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    //    PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
    //    PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
    //    wakelock.acquire(5000);

    //    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    //}

}