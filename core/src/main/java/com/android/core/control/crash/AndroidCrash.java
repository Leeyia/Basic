package com.android.core.control.crash;

import android.content.Context;
import android.os.Looper;
import android.os.Process;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-14 09:53
 * @GitHub: https://github.com/meikoz
 */
public class AndroidCrash implements Thread.UncaughtExceptionHandler {

    private static AndroidCrash sInstance = new AndroidCrash();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;
    private HttpReportCallback mCallback;

    public AndroidCrash() {
    }

    public static AndroidCrash getInstance() {
        return sInstance;
    }

    //初始化
    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    //应用异常系统会调用此方法
    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        final File file = new File(LogWriter.PATH + LogWriter.FILE_NAME + time + LogWriter.FILE_NAME_SUFFIX);

        //为了防止上传时文件没有写入完成
        LogWriter.writeLog(mContext, ex, file, time, new LogWriter.WriteCallback() {
            @Override
            public void writeSuccess() {
                if (mCallback != null) {
                    //上传到服务器
                    uploadException2remote(file);
                }
            }
        });

        ex.printStackTrace();

        //如果系统提供了默认的一场处理，则交给系统去结束异常，否则自己处理
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    //处理错误
                    if (mDefaultCrashHandler != null)
                        mDefaultCrashHandler.uncaughtException(thread, ex);
                    else
                        Process.killProcess(Process.myPid());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Looper.loop();
            }
        }).start();

    }

    //不调用这个方法 callback 是null 所以就会上传服务器的方法
    public AndroidCrash setCrashReporter(HttpReportCallback callback) {
        this.mCallback = callback;
        return this;
    }

    private void uploadException2remote(File file) {
        //上传服务器
        mCallback.uploadException2remote(file);
    }
}
