package com.android.core.control.crash;

import java.io.File;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-17 16:32
 * @GitHub: https://github.com/meikoz
 */
public interface HttpReportCallback {

    void uploadException2remote(File file);
}
