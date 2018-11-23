![](https://cdn-images-1.medium.com/max/1600/1*TVxbF08RjwtZHlqEW-ijIg.png)
![](https://cdn-images-1.medium.com/max/1600/1*8I7cAZwfslXHrV0VMd87sA.png)


## Why
- [x] 简洁/方便/解耦
- [x] 屏幕方向发生改变时保持 Presenter 状态
- [x] 页面 Lifecycle 和 Presenter 生命周期绑定 自动释放资源
- [x] 使用 WeakReference 避免内存泄漏


## How
```
implementation 'com.racofix.basic2:mvp:1.0.0'
```


## What
design our BaseLogic (Presenter) and Vo (View)
```
public interface BaseLogic<V extends BaseLogic.Vo> {

    Bundle getStateBundle();

    void attachLifecycle(Lifecycle lifecycle);

    void detachLifecycle(Lifecycle lifecycle);

    void attachVo(V vo);

    void detachVo();

    V getVo();

    boolean isVoAttached();

    void onLogicCreated();

    void onLogicDestroy();

    /*Base View*/
    interface Vo {

    }
}
```

create BaseLogicImpl that implements our BaseLogic.
```
public class BaseLogicImpl<V extends BaseLogic.Vo> implements BaseLogic<V>, LifecycleObserver {

    private Bundle stateBundle;
    private WeakReference<V> wrf;

    @Override
    public Bundle getStateBundle() {
        return stateBundle == null
                ? stateBundle = new Bundle()
                : stateBundle;
    }

    @Override
    final public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    final public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    final public void attachVo(V vo) {
        this.wrf = new WeakReference<>(vo);
    }

    @Override
    final public void detachVo() {
        this.wrf.clear();
        this.wrf = null;
    }

    @Override
    final public boolean isVoAttached() {
        return this.wrf != null && this.wrf.get() != null;
    }

    @Override
    final public V getVo() {
        return isVoAttached() ? wrf.get() : null;
    }

    /*Logic Created */
    @Override
    public void onLogicCreated() {

    }

    @Override
    public void onLogicDestroy() {
        if (stateBundle != null && !stateBundle.isEmpty()) {
            stateBundle.clear();
        }
    }
}
```
So, we have a Presenter, which attaches and detaches the View and Lifecycle Observer.
With the next step, let’s create our Activity. Here we have a problem: the presenter will be recreating every time after the orientation change.



