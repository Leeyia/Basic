package com.racofix.model;

import android.content.Context;

abstract class AbsModel {

    public static final <T extends AbsModel> T getInstance(Class<T> clzz) {
        return ModelFactory.getInstance(clzz);
    }

    protected void onAppCreate(Context ctx) {}

    protected void onAppCreateOnBackThread(Context ctx) {}

    protected final void runOnBackThread(Runnable runnable) {
        ModelFactory.runOnBackThread(runnable);
    }
}
