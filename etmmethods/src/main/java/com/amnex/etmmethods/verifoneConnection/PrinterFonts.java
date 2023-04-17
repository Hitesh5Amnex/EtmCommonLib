package com.amnex.etmmethods.verifoneConnection;

import android.content.res.AssetManager;
import android.os.Environment;

/**
 * Created by Simon on 2018/6/1.
 */

public class PrinterFonts {
    public static final String FONT_HINDI_dev_new = "dev_new.TTF";
    public static final String FONT_HINDI_himalaya = "himalaya.TTF";
    public static final String FONT_HINDI_kruti_dev_regular = "kruti_dev_regular.TTF";
    public static final String FONT_HINDI_mangal_regular = "mangal_regular.TTF";

    public static String path = "";

    public static void initialize(AssetManager assets) {
        String fileName = PrinterFonts.FONT_HINDI_dev_new;
        path = Environment.getExternalStorageDirectory().getPath().concat("/fonts/");
        ExtraFiles.copy("fonts/" + fileName, path, fileName, assets, false);

        fileName = PrinterFonts.FONT_HINDI_himalaya;
        ExtraFiles.copy("fonts/" + fileName, path, fileName, assets, false);

        fileName = PrinterFonts.FONT_HINDI_kruti_dev_regular;
        ExtraFiles.copy("fonts/" + fileName, path, fileName, assets, false);

        fileName = PrinterFonts.FONT_HINDI_mangal_regular;
        ExtraFiles.copy("fonts/" + fileName, path, fileName, assets, false);
    }
}
