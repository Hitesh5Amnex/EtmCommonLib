package com.amnex.etmmethods.verifoneConnection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.vfi.smartpos.deviceservice.aidl.IDeviceInfo;
import com.vfi.smartpos.deviceservice.aidl.IDeviceService;
import com.vfi.smartpos.deviceservice.aidl.IPrinter;
import com.vfi.smartpos.deviceservice.aidl.IScanner;

public class VerifoneConnectToAllServices {
    private final String TAG = "==Verifone==";

    private Context context;
    private ServiceConnectListener listener;

    // Services
    private IDeviceService idevice;
    private IPrinter printer;
    // private IMagCardReader msr;
    private IScanner scanner;
    private IDeviceInfo iDeviceInfo;
    // private IRFCardReader irfCardReader;
    // private IBeeper mIBeeper;
    // private ISmartCardReader iSmartCardReader;

    public VerifoneConnectToAllServices(Context context, ServiceConnectListener listener) {
        this.context = context;
        this.listener = listener;

        idevice = null;
        printer = null;
        scanner = null;

        connectToServices();
    }

    private void connectToServices() {
        Intent intent = new Intent();
        intent.setAction("com.vfi.smartpos.device_service");
        intent.setPackage("com.vfi.smartpos.deviceservice");
        boolean isSucc = context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
        if (!isSucc) {
            Log.i(TAG, "deviceService connect fail!");
        } else {
            Log.i(TAG, "deviceService connect success");
        }

        // bindService(new Intent("com.VFI.smartpos.device_service"), conn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            idevice = IDeviceService.Stub.asInterface(service);
            try {
                // mIBeeper = idevice.getBeeper();
                printer = idevice.getPrinter();
                // msr = idevice.getMagCardReader();
                scanner = idevice.getScanner(0);    // 1 for the front, 0 for the rear
                // irfCardReader = idevice.getRFCardReader();
                // iSmartCardReader = idevice.getSmartCardReader(0);
                iDeviceInfo = idevice.getDeviceInfo();

                printer.cleanCache();

                listener.onConnect(printer, scanner, iDeviceInfo);

                disconnectToServices();

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            // Toast.makeText(context, "bind service success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, name.toString());
        }
    };

    private void disconnectToServices() {
        if (conn != null) {
            context.unbindService(conn);
            conn = null;
        }
    }


    public interface ServiceConnectListener {
        void onConnect(IPrinter printer, IScanner scanner, IDeviceInfo iDeviceInfo);
    }
}
