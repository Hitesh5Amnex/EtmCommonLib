package com.amnex.etmcommonlib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.amnex.etmmethods.mosambee.BaseActivity;
import com.vfi.smartpos.deviceservice.aidl.FontFamily;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    Button btnPrint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spEtmSelection);
        Button btnPrint = findViewById(R.id.btnPrint);

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // print();
            }
        });

    }

    @Override
    protected void setButtonName() {
        btnPrint.setText("Print");
    }

}