package com.racofix.develop.bluetooth;

public class ScanConfig {
    private Builder builder;

    private ScanConfig(Builder builder) {
        this.builder = builder;
    }

    public static class Builder implements BaseBuilder<ScanConfig> {

        /**
         * 扫描周期
         *
         * @param scanPeriod 扫描周期时间
         */
        public Builder scanPeriod(long scanPeriod) {
            return this;
        }

        public Builder scanBetween(long scanBetween) {
            return this;
        }

        public Builder startsWithFilter(String filter) {
            return this;
        }

        @Override
        public ScanConfig build() {
            return new ScanConfig(this);
        }
    }
}
