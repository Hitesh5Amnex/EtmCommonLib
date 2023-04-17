package com.amnex.etmmethods.verifoneConnection;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.vfi.smartpos.system_service.aidl.settings.ISettingsManager;
import com.vfi.smartpos.system_service.aidl.settings.SettingsActions;
import com.vfi.smartpos.system_service.aidl.settings.SettingsType;


/*import com.amnex.kdmcticketing.utils.Logger;
import com.vfi.smartpos.system_service.aidl.settings.ISettingsManager;
import com.vfi.smartpos.system_service.aidl.settings.SettingsActions;
import com.vfi.smartpos.system_service.aidl.settings.SettingsType;*/

/**
 * By Bhavesh<br/>
 * <p/>
 *
 */
public class VerifoneUpdateSystemDateTime {

    /**
     * By Bhavesh<br/>
     * <p/>
     *
     * @param settingsManager
     * @param date_yyyyMMdd    yyyyMMdd -> E.g. 20200602
     * @param time_HHmmss      HHmmss   -> E.g. 150629
     */
    public VerifoneUpdateSystemDateTime(ISettingsManager settingsManager, String date_yyyyMMdd, String time_HHmmss) {
        if (settingsManager != null) {
            Bundle bundle = new Bundle();
            bundle.putString("SYSTEM_TIME_ACTIONS", SettingsActions.SystemTimeActions.SET_SYSTEM_TIME);
            bundle.putString("SYSTEM_DATE", date_yyyyMMdd);
            bundle.putString("SYSTEM_TIME", time_HHmmss);
            try {
                int result = settingsManager.settingsSetActions(SettingsType.DATE_TIME, bundle);
                System.out.println("New System Date:" + date_yyyyMMdd + " Time:" + time_HHmmss + " isSet->" + (result == 0 ? "success" : "fail"));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
