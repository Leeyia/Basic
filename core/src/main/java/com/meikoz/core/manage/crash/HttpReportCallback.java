package com.meikoz.core.manage.crash;

import java.io.File;

public interface HttpReportCallback {

    void uploadException2remote(File file);
}
