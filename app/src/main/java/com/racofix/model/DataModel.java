package com.racofix.model;

import android.content.Context;
import android.util.Log;

/**
 * Created by zjl on 16-9-26.
 */

public class DataModel extends AbsModel {

    public static DataModel getInstance() {
        return getInstance(DataModel.class);
    }

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        Log.i("racofix", "onAppCreate");
    }
}
