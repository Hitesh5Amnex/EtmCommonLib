package com.amnex.etmmethods.mosambee.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PackageInstallReceiver extends BroadcastReceiver {
    private String TAG = PackageInstallReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, String.format("receive action : %s", action));
        if ("android.intent.action.BOOT_COMPLETED".equals(action) ||
                "android.intent.action.PACKAGE_REPLACED".equals(action) ||
                "android.intent.action.PACKAGE_ADDED".equals(action)) {
            //启动 App

//            Intent it = new Intent(context, LedActivity.class);
//            context.startActivity(it);

        }
    }
}
