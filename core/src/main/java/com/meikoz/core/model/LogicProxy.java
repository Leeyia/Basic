package com.meikoz.core.model;

import com.meikoz.core.base.BasePresenter;
import com.meikoz.core.base.BaseView;
import com.meikoz.core.model.annotation.Implement;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

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
//        List<Class> list = new LinkedList<Class>();
        for (Class cls : clss) {
            if (cls.isAnnotationPresent(Implement.class)) {
//                list.add(cls);
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

    // 初始化presenter add map
    public <T> T bind(Class clzz, BaseView var1) {
        if (!m_objects.containsKey(clzz)) {
            init(clzz);
        }
        BasePresenter presenter = ((BasePresenter) m_objects.get(clzz));
        if (var1 != presenter.getView()) {
            if (presenter.getView() != null) {
                presenter.detachView();
            }
            presenter.attachView(var1);
        }
        return (T) presenter;
    }

    // 解除绑定 移除map
    public void unbind(Class clzz, BaseView var1) {
        if (m_objects.containsKey(clzz)) {
            BasePresenter presenter = ((BasePresenter) m_objects.get(clzz));
            if (var1 != presenter.getView()) {
                if (presenter.getView() != null)
                    presenter.detachView();
                m_objects.remove(clzz);
            }

        }
    }
}