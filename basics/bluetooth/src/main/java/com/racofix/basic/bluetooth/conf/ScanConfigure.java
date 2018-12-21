package com.racofix.basic.bluetooth.conf;

public class ScanConfigure {

    private Configure config;

    private ScanConfigure(Configure config) {
        this.config = config;
    }

    public long getPeriodMills() {
        return this.config.periodMills;
    }

    public long getWaitMills() {
        return this.config.waitMills;
    }

    public String[] getFilters() {
        return this.config.filters;
    }

    public static class Configure {
        private long periodMills;
        private long waitMills;
        private String[] filters;

        public Configure periodMills(long periodMills) {
            this.periodMills = periodMills;
            return this;
        }

        public Configure waitMills(long betweenMills) {
            this.waitMills = betweenMills;
            return this;
        }

        public Configure filters(String... filters) {
            this.filters = filters;
            return this;
        }

        public ScanConfigure conf() {
            return new ScanConfigure(this);
        }
    }
}
