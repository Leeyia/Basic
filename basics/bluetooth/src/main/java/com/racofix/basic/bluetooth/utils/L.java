package com.racofix.basic.bluetooth.utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class L {

    private static final String TAG = "Bluetooth";
    private static boolean ENABLE_DEBUG_LOGGING = true;
    private static boolean ENABLE_CRASHLYTICS_LOGGING = false;
    private static Method CRASHLYTICS_LOG_METHOD;

    public static void enableDebugLogging(boolean enableDebugLogging) {
        ENABLE_DEBUG_LOGGING = enableDebugLogging;
    }

    public static void v(String msg) {
        if (ENABLE_DEBUG_LOGGING ) {
            String logMsg = debugInfo() + msg;
            Log.v(TAG, logMsg);
            logCrashlytics(logMsg);
        }
    }

    public static void v(boolean enabledExternally, String msg) {
        if (ENABLE_DEBUG_LOGGING && enabledExternally) {
            String logMsg = debugInfo() + msg;
            Log.v(TAG, logMsg);
            logCrashlytics(logMsg);
        }
    }

    public static void d(String msg) {
        if (ENABLE_DEBUG_LOGGING) {
            String logMsg = debugInfo() + msg;
            Log.d(TAG, logMsg);
            logCrashlytics(logMsg);
        }
    }

    public static void d(boolean enabledExternally, String msg) {
        if (ENABLE_DEBUG_LOGGING && enabledExternally) {
            String logMsg = debugInfo() + msg;
            Log.d(TAG, logMsg);
            logCrashlytics(logMsg);
        }
    }


    public static void i(String msg) {
        String logMsg = debugInfo() + msg;
        Log.i(TAG, logMsg);
        logCrashlytics(logMsg);
    }

    public static void w(String msg) {
        String logMsg = debugInfo() + msg;
        Log.w(TAG, logMsg);
        logCrashlytics(logMsg);
    }

    public static void e(String msg) {
        String logMsg = debugInfo() + msg;
        Log.e(TAG, logMsg);
        logCrashlytics(msg);
    }

    public static void e(String msg, Throwable e) {
        String logMsg = debugInfo() + msg;
        Log.e(TAG, logMsg, e);
        logCrashlytics(msg + " " + throwableAsString(e));
    }

    public static void wtf(String msg) {
        String logMsg = debugInfo() + msg;
        Log.wtf(TAG, logMsg);
        logCrashlytics(logMsg);
    }

    public static void wtf(String msg, Exception exception) {
        String logMsg = debugInfo() + msg;
        Log.wtf(TAG, logMsg, exception);
        logCrashlytics(logMsg + " " + throwableAsString(exception));
    }

    /**
     * Returns the class name, method name, and line number from the currently
     * executing log call in the form <class_name>.<method_name>()-<line_number>
     *
     * @return String representing class name, method name and line number.
     */
    private static String debugInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[4].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
        int lineNumber = stackTrace[4].getLineNumber();
        return  getClassNameWithoutPackage(className) + "." + methodName + ":" + lineNumber + " ";
    }

    private static String getClassNameWithoutPackage(String fullName) {
        int lastDotIndex = fullName.lastIndexOf('.');
        if (lastDotIndex == -1){
            return fullName;
        } else {
            return fullName.substring(lastDotIndex + 1);
        }
    }

    /**
     * @param e Throwable.
     * @return Throwable's stacktrace as string.
     */
    private static String throwableAsString(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    /** Enables piping all logs to Crashlytics. */
    public static void enableCrashlyticsLogging(boolean enableCrashlytics) {
        if (enableCrashlytics) {
            try {
                Class<?> crashlytics = Class.forName("com.crashlytics.android.Crashlytics");
                CRASHLYTICS_LOG_METHOD = crashlytics.getMethod("log", String.class);
                ENABLE_CRASHLYTICS_LOGGING = true;
            } catch (ClassNotFoundException e) {
                // No Crashlytics. That's OK!
            } catch (NoSuchMethodException e) {
                // No logging methods from Crashlytics. That's OK!
            }
        } else {
            ENABLE_CRASHLYTICS_LOGGING = false;
        }
    }

    /**
     * Logs message to Crashlytics.
     *
     * @param msg Message.
     */
    public static void logCrashlytics(String msg) {
        if (ENABLE_CRASHLYTICS_LOGGING) {
            try {
                CRASHLYTICS_LOG_METHOD.invoke(null, msg);
            } catch (Exception e) {
                // Do nothing.
            }
        }
    }
}
