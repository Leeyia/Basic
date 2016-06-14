package com.android.core.control.crash;

import android.content.Context;
import android.os.Process;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-14 09:53
 * @GitHub: https://github.com/meikoz
 */
public class AndroidCrash implements Thread.UncaughtExceptionHandler {

    private static AndroidCrash sInstance = new AndroidCrash();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;


    public AndroidCrash() {
    }

    public static AndroidCrash getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //捕捉异常信息到SD
        LogWriter.writeLog(mContext, ex);
        //上传到服务器
        uploadException2remote();
        ex.printStackTrace();
        //如果系统提供了默认的一场处理，则交给系统去结束异常，否则自己处理
        if (mDefaultCrashHandler != null)
            mDefaultCrashHandler.uncaughtException(thread, ex);
        else
            Process.killProcess(Process.myPid());
    }

    private void uploadException2remote() {
        //上传服务器

    }


}
