package com.racofix.basic.bluetooth.model;

import java.io.Serializable;

public class CharacteristicEntity implements Serializable {

    public String uuid;
    public boolean readable;
    public boolean writeable;
    public boolean notify;
    public boolean indicative;

    public CharacteristicEntity(String uuid, boolean readable, boolean writeable, boolean notify, boolean indicative) {
        this.uuid = uuid;
        this.readable = readable;
        this.writeable = writeable;
        this.notify = notify;
        this.indicative = indicative;
    }

    @Override
    public String toString() {
        return "CharacteristicEntity{" +
                "uuid='" + uuid + '\'' +
                ", readable=" + readable +
                ", writeable=" + writeable +
                ", notify=" + notify +
                ", indicative=" + indicative +
                '}';
    }
}
