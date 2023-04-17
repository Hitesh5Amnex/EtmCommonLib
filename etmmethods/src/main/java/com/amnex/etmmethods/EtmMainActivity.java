package com.amnex.etmmethods;

import static com.amnex.etmmethods.mosambee.utils.Util.makeLineText;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Gravity;

import com.amnex.etmmethods.mosambee.BaseActivity;
import com.amnex.etmmethods.mosambee.device.DeviceHelper;
import com.amnex.etmmethods.mosambee.utils.Util;
import com.morefun.yapi.ServiceResult;
import com.morefun.yapi.device.printer.MulPrintStrEntity;
import com.morefun.yapi.device.printer.OnPrintListener;
import com.morefun.yapi.device.printer.PrinterConfig;
import com.vfi.smartpos.deviceservice.aidl.FontFamily;

import java.util.ArrayList;
import java.util.List;

public class EtmMainActivity extends BaseActivity {
    public static String fontPath = "/storage/emulated/0/Android/data/com.morefun.ysdk.sample/cache/wawa.ttf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etm_main);
    }

    @Override
    protected void setButtonName() {

    }

    public  void print() {
        try {
            int fontSize = FontFamily.MIDDLE;
            Bundle config = new Bundle();
            config.putString(PrinterConfig.COMMON_TYPEFACE_PATH, fontPath);
            config.putInt(PrinterConfig.COMMON_GRAYLEVEL, 15);
            List<MulPrintStrEntity> list = new ArrayList<>();

            MulPrintStrEntity entity = new MulPrintStrEntity("POS purchase order", fontSize);
            //  Bitmap imageFromAssetsFile = getImageFromAssetsFile(this, "china_union_pay.bmp");
            // entity.setBitmap(imageFromAssetsFile);
            //  entity.setMarginX(50);
            //  entity.setGravity(Gravity.CENTER);
            //entity.setUnderline(true);
            //   entity.setYspace(30);
            //    list.add(entity);


            list.add(new MulPrintStrEntity(makeLineText(new Util.TextItem("1").setFont(FontFamily.MIDDLE).setPxSize(20).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("Chicken Achari").setFont(FontFamily.MIDDLE).setPxSize(180).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("1.00").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("35.71").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL)),
                    FontFamily.MIDDLE));

            list.add(new MulPrintStrEntity(makeLineText(new Util.TextItem("2").setFont(FontFamily.MIDDLE).setPxSize(20).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("Alu Puff").setFont(FontFamily.MIDDLE).setPxSize(180).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("1.00").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("50.00").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL)), FontFamily.MIDDLE));

            list.add(new MulPrintStrEntity(makeLineText(new Util.TextItem("3").setFont(FontFamily.MIDDLE).setPxSize(20).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("Pizza Burger").setFont(FontFamily.MIDDLE).setPxSize(180).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("1.00").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("100.00").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL)), FontFamily.MIDDLE));
            list.add(new MulPrintStrEntity(makeLineText(new Util.TextItem("4").setFont(FontFamily.MIDDLE).setPxSize(20).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("Chicken Cheese").setFont(FontFamily.MIDDLE).setPxSize(180).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("3.00").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("90.00").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL)), FontFamily.MIDDLE));

            list.add(new MulPrintStrEntity(makeLineText(new Util.TextItem("5").setFont(FontFamily.MIDDLE).setPxSize(20).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("Chicken Burger").setFont(FontFamily.MIDDLE).setPxSize(180).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("2.00").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL),
                    new Util.TextItem("76.19").setFont(FontFamily.MIDDLE).setPxSize(80).setPaddingAlign(Gravity.FILL_HORIZONTAL)), FontFamily.MIDDLE));


            list.add(new MulPrintStrEntity("=====================", fontSize));
            list.add(new MulPrintStrEntity(makeLineText(new Util.TextItem("MERCHANT NAME").setFont(FontFamily.MIDDLE),
                    new Util.TextItem("Shop name").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.RIGHT)), FontFamily.MIDDLE));
            list.add(new MulPrintStrEntity(makeLineText(new Util.TextItem("MERCHANT NO").setFont(FontFamily.MIDDLE),
                    new Util.TextItem("20321545656687").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.RIGHT)), FontFamily.MIDDLE));
            list.add(new MulPrintStrEntity(makeLineText(new Util.TextItem("TERMINAL NO").setFont(FontFamily.MIDDLE),
                    new Util.TextItem("25689753").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.RIGHT)), FontFamily.MIDDLE));
            list.add(new MulPrintStrEntity("CARD NUMBER", fontSize).setGravity(Gravity.LEFT));
            list.add(new MulPrintStrEntity("62179390*****3426", fontSize).setGravity(Gravity.END));

            list.add(new MulPrintStrEntity("CENTER", fontSize).setGravity(Gravity.CENTER));
            list.add(new MulPrintStrEntity("CENTER", fontSize).setGravity(Gravity.CENTER));
            list.add(new MulPrintStrEntity("CENTER", fontSize).setGravity(Gravity.CENTER));


            list.add(new MulPrintStrEntity(makeLineText(new  Util.TextItem("TRANS TYPE:").setFont(FontFamily.MIDDLE),
                    new  Util.TextItem("SALE").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.RIGHT)), FontFamily.MIDDLE));
            list.add(new MulPrintStrEntity(makeLineText(new  Util.TextItem("EXP DATE:").setFont(FontFamily.MIDDLE),
                    new  Util.TextItem("2029").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.RIGHT)), FontFamily.MIDDLE));
            list.add(new MulPrintStrEntity(makeLineText(new  Util.TextItem("BATCH NO:").setFont(FontFamily.MIDDLE),
                    new  Util.TextItem("000012").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.RIGHT)), FontFamily.MIDDLE));
            list.add(new MulPrintStrEntity(makeLineText(new  Util.TextItem("VOUCHER NO:").setFont(FontFamily.MIDDLE),
                    new  Util.TextItem("000001").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.RIGHT)), FontFamily.MIDDLE));


            list.add(new MulPrintStrEntity("==========================", fontSize));

           /* list.add(new MulPrintStrEntity(makeLineText(
                    new TextItem("Test1").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.LEFT),  // LEFT
                    new TextItem("10 x 200").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.CENTER), // CENTER
                    new TextItem("2000").setFont(FontFamily.MIDDLE).setPaddingAlign(Gravity.RIGHT)), // RIGHT
                    FontFamily.MIDDLE));*/


            list.add(new MulPrintStrEntity(makeLineText(
                    new  Util.TextItem("TEST12345678910").setFont(FontFamily.MIDDLE).setPxSize(125).setPaddingAlign(Gravity.FILL_HORIZONTAL), // 125
                    new  Util.TextItem("110 * 200").setFont(FontFamily.MIDDLE).setPxSize(160).setPaddingAlign(Gravity.FILL_HORIZONTAL), // 160
                    new  Util.TextItem("2000").setFont(FontFamily.MIDDLE).setPxSize(90).setPaddingAlign(Gravity.FILL_HORIZONTAL)), FontFamily.MIDDLE)); // 90

            list.add(new MulPrintStrEntity("==========================", fontSize));

            list.add(new MulPrintStrEntity("DATE/TIME：2016-05-23 16:50:32", fontSize));
            list.add(new MulPrintStrEntity("==========================", fontSize));

            list.add(new MulPrintStrEntity("", fontSize));
            list.add(new MulPrintStrEntity("CARD HOLDER SIGNATURE", fontSize));
            list.add(new MulPrintStrEntity("", fontSize));

            list.add(new MulPrintStrEntity("--------------------------------------", fontSize));
            list.add(new MulPrintStrEntity(" I ACKNOWLEDGE	SATISFACTORY RECEIPT OF RELATIVE GOODS/SERVICES", FontFamily.SMALL));
            list.add(new MulPrintStrEntity(" MERCHANT COPY ", FontFamily.SMALL));
            list.add(new MulPrintStrEntity("---X---X---X---X---X--X--X--X--X--X--\n", fontSize));
            list.add(new MulPrintStrEntity("\n", fontSize));

            DeviceHelper.getPrinter().printStr(list, new OnPrintListener.Stub() {
                @Override
                public void onPrintResult(int result) throws RemoteException {
                    EtmMainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           // btn.setEnabled(true);
                        }
                    });
                    //showResult(textView, result == ServiceResult.Success ? getString(R.string.msg_succ) : getString(R.string.msg_fail));
                }
            }, config);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}