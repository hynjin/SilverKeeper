package com.jellygom.miband_sdk.MiBandIO.Model;

import java.util.UUID;

public class MibandUUID {

  public static final String MAC_FILTER_MI1S ="D3:3C:A8"; //"D0:EF:DC";///"C8:0F:10";

  // UUID 기본 형식
  private static final String BASIC_UUID = "0000%s-0000-1000-8000-00805f9b34fb";

  // 기본 서비스 UUID 및 특성 UUID
  public static final UUID UUID_SERVICE_MIBAND = UUID.fromString(String.format(BASIC_UUID, "fee0"));
  public static final UUID UUID_CHARACTERISTIC_USER_INFO = UUID.fromString(String.format("00000006-0000-3512-2118-0009af100700"));//BASIC_UUID, "ff04"));
  public static final UUID UUID_DESCRIPTOR_UPDATE_NOTIFICATION = UUID.fromString(String.format(BASIC_UUID, "2902"));
  public static final UUID UUID_CHARACTERISTIC_REALTIME_STEPS = UUID.fromString("00000007-0000-3512-2118-0009af100700");//String.format(BASIC_UUID, "ff06"));
  public static final UUID UUID_CHARACTERISTIC_BATTERY = UUID.fromString(String.format(BASIC_UUID, "ff0c"));

  // 알람 서비스 UUID 및 특성 UUID
  public static final UUID UUID_SERVICE_ALERT = UUID.fromString(String.format(BASIC_UUID, "1802"));
  public static final UUID UUID_CHARACTERISTIC_ALERT_LEVEL = UUID.fromString(String.format(BASIC_UUID, "2a06"));

  // 심박센서 서비스 UUID 및 특성 UUID
  public static final UUID UUID_SERVICE_HEART_RATE = UUID.fromString(String.format(BASIC_UUID, "180d"));
  public static final UUID UUID_CHARACTERISTIC_HEART_RATE_CONTROL_POINT = UUID.fromString(String.format(BASIC_UUID, "2a39"));
  public static final UUID UUID_CHARACTERISTIC_HEART_RATE_MEASUREMENT = UUID.fromString(String.format(BASIC_UUID, "2a37"));
}
