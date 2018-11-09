package com.racofix.basic.bluetooth.model;

import java.io.Serializable;


public class ServiceEntity implements Serializable {
    public String uuid;

    public ServiceEntity(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}
