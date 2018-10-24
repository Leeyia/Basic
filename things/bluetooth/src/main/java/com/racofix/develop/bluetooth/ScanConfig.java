package com.racofix.develop.bluetooth;

public class ScanConfig {

    private Builder builder;

    public long getScanPeriod() {
        return this.builder.periodMills;
    }

    public long getScanBetween() {
        return this.builder.betweenMills;
    }

    public String[] getScanFilters() {
        return this.builder.filters;
    }

    public boolean isOpenPeriod() {
        return this.builder.isOpenPeriod;
    }

    private ScanConfig(Builder builder) {
        this.builder = builder;
    }

    public static class Builder implements BaseBuilder<ScanConfig> {
        private long periodMills;
        private long betweenMills;
        private String[] filters;
        private boolean isOpenPeriod;

        public Builder scanPeriodMills(long periodMills) {
            this.periodMills = periodMills;
            return this;
        }

        public Builder scanBetweenMills(long betweenMills) {
            this.betweenMills = betweenMills;
            return this;
        }

        public Builder scanBLEFilters(String... filters) {
            this.filters = filters;
            return this;
        }

        public Builder openPeriod(boolean isOpenPeriod) {
            this.isOpenPeriod = isOpenPeriod;
            return this;
        }

        @Override
        public ScanConfig build() {
            return new ScanConfig(this);
        }
    }
}
