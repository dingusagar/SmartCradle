package com.example.dingu.smartcradle;

/**
 * Created by dingu on 6/4/18.
 */

public class Device {
    String IPAddress = "";
    String DeviceName = "";

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }
}
