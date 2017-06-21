package com.deppon.yu.device;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static int REQUEST_READ_PHONE_STATE = 11111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.textView)).setText(this.getDeviceInfo());

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }
    }


    /**
     * 获取设备信息
     *
     * @return
     */
    private String getDeviceInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("主板： "+ Build.BOARD + "\n");
        sb.append("系统启动程序版本号： "+ Build.BOOTLOADER + "\n");
        sb.append("系统定制商： "+ Build.BRAND + "\n");

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            sb.append("cpu指令集：" + Build.CPU_ABI + "\n");
            sb.append("cpu指令集2:" + Build.CPU_ABI2 + "\n");
        }else {

            if(Build.SUPPORTED_32_BIT_ABIS.length != 0){
                sb.append("cpu指令集:");
                sb.append(" [ 32位 ] ");
                sb.append("[ ");
                for (int i = 0; i < Build.SUPPORTED_32_BIT_ABIS.length; i++) {

                    if (i == Build.SUPPORTED_32_BIT_ABIS.length - 1) {
                        sb.append(Build.SUPPORTED_32_BIT_ABIS[i]);
                    } else {
                        sb.append(Build.SUPPORTED_32_BIT_ABIS[i] + " , ");
                    }

                }
                sb.append(" ]");
                sb.append("\n");
            }

            if(Build.SUPPORTED_64_BIT_ABIS.length != 0){
                sb.append("cpu指令集:");
                sb.append(" [ 64位 ] ");
                sb.append("[ ");
                for(int i=0;i<Build.SUPPORTED_64_BIT_ABIS.length;i++){

                    if(i == Build.SUPPORTED_64_BIT_ABIS.length - 1){
                        sb.append(Build.SUPPORTED_64_BIT_ABIS[i]);
                    }else{
                        sb.append(Build.SUPPORTED_64_BIT_ABIS[i] + " , ");
                    }

                }
                sb.append(" ]");
                sb.append("\n");
            }

        }

        sb.append("设置参数： "+ Build.DEVICE + "\n");
        sb.append("显示屏参数： "+ Build.DISPLAY + "\n");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            sb.append("无线电固件版本： "+ Build.getRadioVersion() + "\n");
        }
        sb.append("硬件识别码： "+ Build.FINGERPRINT + "\n");
        sb.append("硬件名称： "+ Build.HARDWARE + "\n");
        sb.append("HOST: "+ Build.HOST + "\n");
        sb.append("修订版本列表： "+ Build.ID + "\n");
        sb.append("硬件制造商： "+ Build.MANUFACTURER + "\n");
        sb.append("版本： "+ Build.MODEL + "\n");
        sb.append("硬件序列号：" + Build.SERIAL + "\n");
        sb.append("手机制造商：" + Build.PRODUCT + "\n");
        sb.append("描述Build的标签：" + Build.TAGS + "\n");
        sb.append("TIME: "+ Build.TIME + "\n");
        sb.append("builder类型：" + Build.TYPE + "\n");
        sb.append("USER: "+ Build.USER + "\n");
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        sb.append("DeviceId: "+ tm.getDeviceId()+ "\n");
        return sb.toString();
    }
}
