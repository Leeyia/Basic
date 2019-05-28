package com.dintech.api.scorpius.v7;

import java.util.List;

public class Metting extends GroupItem<Object> {

    List<Object> strings;

    @Override
    @GroupType(String.class)
    public List<Object> childItems() {
        return strings;
    }
}
