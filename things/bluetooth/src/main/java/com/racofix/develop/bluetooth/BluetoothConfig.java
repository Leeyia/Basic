package com.racofix.develop.bluetooth;

public class BluetoothConfig {

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

    public boolean periodOpened() {
        return this.builder.periodOpened;
    }

    public boolean isRemoveDuplicated() {
        return this.builder.removeDuplicated;
    }

    private BluetoothConfig(Builder builder) {
        this.builder = builder;
    }

    public static class Builder {
        private long periodMills;
        private long betweenMills;
        private String[] filters;
        private boolean periodOpened;
        private boolean removeDuplicated;

        public Builder scanPeriodMills(long periodMills) {
            this.periodMills = periodMills;
            return this;
        }

        public Builder scanBetweenMills(long betweenMills) {
            this.betweenMills = betweenMills;
            return this;
        }

        /**
         * 需要过滤的条件
         *
         * @param filters [Bluetooth name or address]
         * @return
         */
        public Builder scanBLEFilters(String... filters) {
            this.filters = filters;
            return this;
        }

        public Builder periodOpen(boolean isOpenPeriod) {
            this.periodOpened = isOpenPeriod;
            return this;
        }

        /**
         * 返回蓝牙列表是否去重
         *
         * @param removeDuplicated
         * @return Builder
         */
        public Builder removeDuplicate(boolean removeDuplicated) {
            this.removeDuplicated = removeDuplicated;
            return this;
        }

        public BluetoothConfig build() {
            return new BluetoothConfig(this);
        }
    }
}
