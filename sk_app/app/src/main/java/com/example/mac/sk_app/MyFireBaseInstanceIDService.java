package com.example.mac.sk_app; /**
 *
 * 사용자 기기별 token을 생성하는 클래스 입니다.
 * 나중에 push 알림을 특정 타겟에게 보낼 때 사용되는 고유 키 값 입니다.
 * 이 토큰값을 용도에 맞게 사용하시면 됩니다.
 *
 * Created by mac on 2017. 5. 17..
 */

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
public class MyFireBaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = MyFireBaseInstanceIDService.class.getSimpleName();
    private String token;
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token", "Refreshed token: " + token);
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(token);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    // TODO: 이후 생성등록된 토큰을 서버에 보내 저장해 두었다가 추가 작업을 할 수 있도록 한다.
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }
    public String getToken()
    {
        return this.token;
    }
}
