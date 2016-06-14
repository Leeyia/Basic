package com.android.core.control.logcat;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-02 17:25
 * @GitHub: https://github.com/meikoz
 */
public interface Printer {
    
    Printer t(String var1, int var2);

    Settings init(String var1);

    Settings getSettings();

    void d(String var1, Object... var2);

    void e(String var1, Object... var2);

    void e(Throwable var1, String var2, Object... var3);

    void w(String var1, Object... var2);

    void i(String var1, Object... var2);

    void v(String var1, Object... var2);

    void wtf(String var1, Object... var2);

    void json(String var1);

    void xml(String var1);

    void clear();
}
