package com.jellygom.mibandsdkdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jellygom.miband_sdk.MiBandIO.Listener.HeartrateListener;
import com.jellygom.miband_sdk.MiBandIO.Listener.NotifyListener;
import com.jellygom.miband_sdk.MiBandIO.Listener.RealtimeStepListener;
import com.jellygom.miband_sdk.MiBandIO.Listener.BatteryListener;
import com.jellygom.miband_sdk.MiBandIO.MibandCallback;
import com.jellygom.miband_sdk.MiBandIO.Model.UserInfo;
import com.jellygom.miband_sdk.Miband;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private static final String TAG = MainActivity.class.getSimpleName();

  private Miband miband;
  private BluetoothAdapter mBluetoothAdapter;

  private TextView heart, step, battery;
  private TextView text;

  private RealtimeStepListener realtimeStepListener = new RealtimeStepListener() {
    @Override
    public void onNotify(final int steps) {
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          step.setText(steps + " steps");
          text.append(steps + " steps\n");
        }
      });
    }
  };

  private HeartrateListener heartrateNotifyListener = new HeartrateListener() {
    @Override
    public void onNotify(final int heartRate) {
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          heart.setText(heartRate + " bpm");
          text.append(heartRate + " bpm\n");
        }
      });
    }
  };

  private BatteryListener batteryListener = new BatteryListener() {
    @Override
    public void onNotify(final int level) {
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          battery.setText(level + " %");
          text.append(level + " %\n");
        }
      });
    }
  };

  private final MibandCallback mibandCallback = new MibandCallback() {
    @Override
    public void onSuccess(Object data, int status) {
      switch (status) {
        case MibandCallback.STATUS_SEARCH_DEVICE:
          Log.e(TAG, "성공: STATUS_SEARCH_DEVICE");
          miband.connect((BluetoothDevice) data, this);
          break;
        case MibandCallback.STATUS_CONNECT:
          Log.e(TAG, "성공: STATUS_CONNECT");
          //miband.setHeartRateScanListener(heartrateNotifyListener);
          miband.getUserInfo(this);
          break;
        case MibandCallback.STATUS_SEND_ALERT:
          Log.e(TAG, "성공: STATUS_SEND_ALERT");
          break;
        case MibandCallback.STATUS_GET_USERINFO:
          Log.e(TAG, "성공: STATUS_GET_USERINFO"+((BluetoothGattCharacteristic) data).getValue());
          UserInfo userInfo = new UserInfo().fromByteData(((BluetoothGattCharacteristic) data).getValue());
          miband.setUserInfo(userInfo, this);

          //miband.startHeartRateScan(1, this,heartrateNotifyListener);
          //miband.setHeartRateScanListener(heartrateNotifyListener);
          break;
        case MibandCallback.STATUS_SET_USERINFO:
          Log.e(TAG, "성공: STATUS_SET_USERINFO");
          miband.setHeartRateScanListener(heartrateNotifyListener);
          break;
        case MibandCallback.STATUS_START_HEARTRATE_SCAN:
          Log.e(TAG, "성공: STATUS_START_HEARTRATE_SCAN");
          heartrateNotifyListener.onNotify((int)data);
          break;
        case MibandCallback.STATUS_GET_BATTERY:
          Log.e(TAG, "성공: STATUS_GET_BATTERY");
          final int level = (int) data;
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              battery.setText(level+ " % battery");
              text.append(level + " % battery\n");
            }
          });
          break;
        case MibandCallback.STATUS_GET_ACTIVITY_DATA:
          Log.e(TAG, "성공: STATUS_GET_ACTIVITY_DATA");
          final int steps = (int) data;
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              step.setText(steps+ " steps");
              text.append(steps+ " steps\n");
            }
          });
          break;
      }
    }

    @Override
    public void onFail(int errorCode, String msg, int status) {
      switch (status) {
        case MibandCallback.STATUS_SEARCH_DEVICE:
          Log.e(TAG, "실패: STATUS_SEARCH_DEVICE");
          break;
        case MibandCallback.STATUS_CONNECT:
          Log.e(TAG, "실패: STATUS_CONNECT");
          break;
        case MibandCallback.STATUS_SEND_ALERT:
          Log.e(TAG, "실패: STATUS_SEND_ALERT");
          break;
        case MibandCallback.STATUS_GET_USERINFO:
          Log.e(TAG, "실패: STATUS_GET_USERINFO");
          break;
        case MibandCallback.STATUS_SET_USERINFO:
          Log.e(TAG, "실패: STATUS_SET_USERINFO");
          break;
        case MibandCallback.STATUS_START_HEARTRATE_SCAN:
          Log.e(TAG, "실패: STATUS_START_HEARTRATE_SCAN");
          break;
        case MibandCallback.STATUS_GET_BATTERY:
          Log.e(TAG, "실패: STATUS_GET_BATTERY");
          break;
        case MibandCallback.STATUS_GET_ACTIVITY_DATA:
          Log.e(TAG, "실패: STATUS_GET_ACTIVITY_DATA");
          break;
      }
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    findViewById(R.id.button_vive).setOnClickListener(this);
    findViewById(R.id.button_steps).setOnClickListener(this);
    findViewById(R.id.button_realtime_steps).setOnClickListener(this);
    findViewById(R.id.button_battery).setOnClickListener(this);
    findViewById(R.id.button_heart_start_one).setOnClickListener(this);
    findViewById(R.id.button_heart_start_many).setOnClickListener(this);

    heart = (TextView) findViewById(R.id.heart);
    step = (TextView) findViewById(R.id.steps);
    battery = (TextView) findViewById(R.id.battery);
    text = (TextView) findViewById(R.id.text);


    miband = new Miband(getApplicationContext());

    mBluetoothAdapter = ((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();

    //miband = new Miband(getApplicationContext());

    miband.searchDevice(mBluetoothAdapter, this.mibandCallback);

    miband.setDisconnectedListener(new NotifyListener() {
      @Override
      public void onNotify(byte[] data) {
        miband.searchDevice(mBluetoothAdapter, mibandCallback);
      }
    });

  }

  @Override
  public void onClick(View view) {
    int i = view.getId();
    if (i == R.id.button_vive) {
      Log.d("button","vive");
      miband.sendAlert(this.mibandCallback);
    } else if (i == R.id.button_steps) {
      Log.d("button","steps");
      miband.setCurrentStepListener(realtimeStepListener,this.mibandCallback);
      //miband.getCurrentSteps(this.mibandCallback);
    } else if (i == R.id.button_realtime_steps) {
      Log.d("button","realtime_steps");
      miband.setRealtimeStepListener(realtimeStepListener);
    } else if (i == R.id.button_battery) {
      Log.d("button","battery");
      miband.setBatteryListener(batteryListener);
      miband.getBatteryLevel(this.mibandCallback);
    } else if (i == R.id.button_heart_start_one) {
      Log.d("button","heart_scan");
      miband.startHeartRateScan(1, this.mibandCallback,heartrateNotifyListener);
      miband.setHeartRateScanListener(heartrateNotifyListener);
    } else if (i == R.id.button_heart_start_many) {
      Log.d("button","heart_scan *3");
      miband.startHeartRateScan(0, this.mibandCallback,heartrateNotifyListener);
    }
  }


}
