package com.racofix.basic.things.ui;

import com.racofix.basic.mvp.LogicVo;

public interface LoginVo<T> extends LogicVo {
    void success(T s);
}
