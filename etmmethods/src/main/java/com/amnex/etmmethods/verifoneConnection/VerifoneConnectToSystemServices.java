package com.amnex.etmmethods.verifoneConnection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.vfi.smartpos.system_service.aidl.ISystemManager;
import com.vfi.smartpos.system_service.aidl.settings.ISettingsManager;

/**
 * By Bhavesh<br/>
 * <p/>
 * Connect to all system services
 */
public class VerifoneConnectToSystemServices {
    private final String TAG = "SysService";
    private final String ACTION = "com.vfi.smartpos.system_service";
    private final String PACKAGE = "com.vfi.smartpos.system_service";
    private final String CLASSNAME = "com.vfi.smartpos.system_service.SystemService";
    public final String ACTION_X9SERVICE = "com.vfi.smartpos.device_service";

    private Context context;
    private ServiceConnectListener listener;

    private ServiceConnection mSystemServiceConnection;

    private ISystemManager systemManager = null;
    private ISettingsManager settingsManager = null;

    /**
     * By Bhavesh<br/>
     * <p/>
     *
     * @param context
     * @param listener
     */
    public VerifoneConnectToSystemServices(Context context, ServiceConnectListener listener) {
        this.context = context;
        this.listener = listener;

        connectToServices();
    }

    private void connectToServices() {
        Intent intent = new Intent();
        intent.setAction(ACTION_X9SERVICE);
        intent.setClassName(PACKAGE, CLASSNAME);

        mSystemServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG, "system service bind success");
                systemManager = ISystemManager.Stub.asInterface(iBinder);
                try {
                    settingsManager = systemManager.getSettingsManager();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                listener.onConnect(settingsManager);

                disconnectToServices();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG, "system service disconnected.");
                systemManager = null;
            }
        };
       try {  //try catch add by Hitesh due to cresh issue at print ticket
           boolean result = context.bindService(intent, mSystemServiceConnection, Context.BIND_AUTO_CREATE);
           Log.d(TAG, "bind system service " + result);
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private void disconnectToServices() {
        if (mSystemServiceConnection != null) {
            context.unbindService(mSystemServiceConnection);
            mSystemServiceConnection = null;
        }
    }

    public interface ServiceConnectListener {
        void onConnect(ISettingsManager settingsManager);
    }
}
