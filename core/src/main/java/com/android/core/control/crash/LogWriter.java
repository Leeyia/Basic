package com.android.core.control.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.android.core.control.Toast;
import com.android.core.control.logcat.Logcat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-14 10:19
 * @GitHub: https://github.com/meikoz
 */
public class LogWriter {

    private static final boolean DEBUG = true;
    private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/androidcrash/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".txt";

    public static synchronized void writeLog(Context context, Throwable ex) {
        Logcat.d(PATH);
        Toast.show(PATH);
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Logcat.w("sdcard unmounted,skip dump exception");
                return;
            }
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            writeMobileInfo(context, pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        } catch (Exception e) {
            Logcat.e("write crash exception failed");
        }
    }

    private static void writeMobileInfo(Context context, PrintWriter pw) throws PackageManager.NameNotFoundException {

        PackageManager pm = context.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App Version：");
        pw.print(pi.versionName);
        pw.print("_");
        pw.println(pi.versionCode);

        //Android版本
        pw.print("OS Version：");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        //手机制造商
        pw.print("Vendor：");
        pw.println(Build.MANUFACTURER);

        pw.print("Model：");
        pw.println(Build.MODEL);
    }
}
