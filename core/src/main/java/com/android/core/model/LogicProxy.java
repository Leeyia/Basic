package com.android.core.model;

import com.android.core.base.BasePresenter;
import com.android.core.base.BaseView;
import com.android.core.model.annotation.Implement;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @User: 蜡笔小新
 * @date: 16-9-30
 * @GitHub: https://github.com/meikoz
 */

public class LogicProxy {
    private static final LogicProxy m_instance = new LogicProxy();

    public static LogicProxy getInstance() {
        return m_instance;
    }

    private LogicProxy() {
        m_objects = new HashMap<>();
    }

    private Map<Class, Object> m_objects;

    public void init(Class... clss) {
        List<Class> list = new LinkedList<Class>();
        for (Class cls : clss) {
            if (cls.isAnnotationPresent(Implement.class)) {
                list.add(cls);
                for (Annotation ann : cls.getDeclaredAnnotations()) {
                    if (ann instanceof Implement) {
                        try {
                            m_objects.put(cls, ((Implement) ann).value().newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * @param cls  logic(presenter)'s interface
     * @param var1 view(activity  ||  fragment)
     * @param <T>  (logic)presenter's Impl
     * @return
     */
    public <T> T bind(Class cls, BaseView var1) {
        if (!m_objects.containsKey(cls)) {
            init(cls);
        }
        BasePresenter presenter = ((BasePresenter) m_objects.get(cls));
        if (var1 != presenter.getView()) {
            if (presenter.getView() == null)
                presenter.attachView(var1);
            else
                bind(cls,var1);
        }
        return (T) m_objects.get(cls);
    }
}
