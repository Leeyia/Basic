package com.dintech.api.scorpius.v7;

import java.util.List;

public abstract class GroupItem<T> {

    private boolean expanded;

    @GroupType
    public abstract List<T> childItems();
}
