package com.dintech.api.bleep;

import android.content.Context;

public final class Settings {

    private Context context;
    private Configurations configuration;

    public Settings context(Context context) {
        this.context = context;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public Settings configurations(Configurations configuration) {
        this.configuration = configuration;
        return this;
    }

    public Configurations getConfiguration() {
        return configuration;
    }
}
