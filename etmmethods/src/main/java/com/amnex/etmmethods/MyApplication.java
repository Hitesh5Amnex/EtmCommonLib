package com.amnex.etmmethods;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.amnex.etmmethods.mosambee.device.DeviceHelper;
import com.amnex.etmmethods.mosambee.receiver.PackageInstallReceiver;
import com.morefun.yapi.engine.DeviceServiceEngine;


public class MyApplication extends Application {

    private final String TAG = MyApplication.class.getName();
    private final String SERVICE_ACTION = "com.morefun.ysdk.service";
    private final String SERVICE_PACKAGE = "com.morefun.ysdk";

    private DeviceServiceEngine deviceServiceEngine = null;

    @Override
    public void onCreate() {
        super.onCreate();
        bindDeviceService();
        registerBroadcast();
    }

    public DeviceServiceEngine getDeviceService() {
        return deviceServiceEngine;
    }

    public void bindDeviceService() {
        if (null != deviceServiceEngine) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction(SERVICE_ACTION);
        intent.setPackage(SERVICE_PACKAGE);

        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            deviceServiceEngine = null;
            Log.e(TAG, "======onServiceDisconnected======");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            deviceServiceEngine = DeviceServiceEngine.Stub.asInterface(service);
            Log.d(TAG, "======onServiceConnected======");

            try {
                DeviceHelper.reset();
                DeviceHelper.initDevices(MyApplication.this);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            linkToDeath(service);
        }

        private void linkToDeath(IBinder service) {
            try {
                service.linkToDeath(new IBinder.DeathRecipient() {
                    @Override
                    public void binderDied() {
                        Log.d(TAG, "======binderDied======");
                        deviceServiceEngine = null;
                        bindDeviceService();
                    }
                }, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };



    private void registerBroadcast() {
        Log.i("MyApplication", "registerBroadcast");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        intentFilter.addDataScheme("package");

        registerReceiver(new PackageInstallReceiver(), intentFilter);
    }
}
