package com.amnex.etmmethods.mosambee;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void showResult(final TextView textView, final String text) {
        Log.d("BaseActivity", text);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.append(text + "\r\n");
            }
        });
    }

    protected void clearText(final TextView textView) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("");
            }
        });
    }

    SweetAlertDialog mSweetAlertDialog;

    protected void showDialog() {
        BaseActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSweetAlertDialog = new SweetAlertDialog(BaseActivity.this, SweetAlertDialog.PROGRESS_TYPE)
                        .setContentText("emvProcess...");
                mSweetAlertDialog.show();
            }
        });
    }

    protected void hidDiaglog() {
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
        }
    }

    protected void showConfirm(final String content, SweetAlertDialog.OnSweetClickListener ll, SweetAlertDialog.OnSweetClickListener l2) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSweetAlertDialog = new SweetAlertDialog(BaseActivity.this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("Card No")
                        .setContentText(content)
                        .setConfirmText("Yes")
                        .setCancelText("No")
                        .setCancelClickListener(l2)
                        .setConfirmClickListener(ll);
                mSweetAlertDialog.show();
            }
        });
    }


    protected abstract void setButtonName();

    protected boolean checkAppInstalled(String pkgName) {
        if (pkgName == null || pkgName.isEmpty()) {
            return false;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = getPackageManager().getPackageInfo(pkgName, 0);
            return packageInfo != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}