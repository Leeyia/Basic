package com.dintech.api.bleep;

import android.app.Application;

public final class Settings {

    private Application application;
    private Configurations configuration;

    public Settings application(Application application) {
        this.application = application;
        return this;
    }

    public Application getApplication() {
        return application;
    }

    public Settings configurations(Configurations configuration) {
        this.configuration = configuration;
        return this;
    }

    public Configurations getConfiguration() {
        return configuration;
    }
}
