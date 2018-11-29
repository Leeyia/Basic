## 简介
Android Architecture components 和 MVP 模式结合使用，帮助您设计健壮、可测试和可维护的程序。
- MVP (Model view Presenter) 
- Lifecycle 可感知生命周期组件
- ViewModel 提供UI界面和数据进行通信


## 优点
- [x] 模型与视图完全分离
- [x] 注解实例化 Presenter
- [x] 继承 BaseActivity/BaseFragment 不需要关心Presenter/View绑定和释放
- [x] Lifecycle、ViewModel和Presenter生命周期绑定, Presenter可以观察Activity/Fragment接口
- [x] 屏幕方向发送改变, Presenter状态会保持


## 用法
Download [the latest JAR](https://github.com/meikoz/Basic/tree/master/basics/mvp) or configure this dependency:
```
implementation 'com.racofix.basic2:mvp:1.1'
```
define
```
implementation 'android.arch.lifecycle:extensions:1.1.1'
```

#### Login Sample:
**1. design our Presenter Contract and View**
```
public interface LoginLogic {

    interface Logic {
        void login(String username, String password);
    }

    interface Vo extends LogicI.Vo {
        void successful(String msg);

        void failed(String failMsg);
    }
}
```

**2. create LoginLogicImpl extends LogicImpl<LoginLogic.Vo> that implements our LoginLogic.Vo**
```
public class LoginLogicImpl extends BaseLogicImpl<LoginLogic.Vo> implements LoginLogic.Logic {

    @Override
    public void login(String username, String password) {
        //dosomething
    }

    @Override
    public void onLogicCreated() {
        super.onLogicCreated();
    }

    @Override
    public void onLogicDestroy() {
        super.onLogicDestroy();
    }
}
```

**3. create LoginActivity extends BaseActivity<LoginLogicImpl> implements LoginLogic.Vo, @Implement Presenter class**
```
@Implement(LoginLogicImpl.class)
public class LoginActivity extends BaseActivity<LoginLogicImpl> implements LoginLogic.Vo {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btn_login).setOnClickListener(v->{
            getLogicImpl().login("admin", "admin");
        });
    }

    @Override
    public void successful(String message) {}

    @Override
    public void failed(String failMsg) {}
}
```


## 源码解析
![](https://upload-images.jianshu.io/upload_images/893513-ac14879ef001d2a3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### [BaseActivity](./src/main/java/com/racofix/basic/mvp/BaseActivity.java)
(1). 通过providerLogic()方法解析注解Implement实例化Presenter.
```
    private T providerLogic() {
        return (T) LogicProviders.init(this.getClass());
    }
```

(2). 将实例化的Presenter绑定ViewModel, 将Activity的生命周期通过Lifecycle交给Presenter后并绑定View.
```
    LogicViewModel<T> viewModel = ViewModelProviders.of(this).get(LogicViewModel.class);
    boolean isLogicCreated = false;
    if (viewModel.getLogicImpl() == null) {
         viewModel.setLogicImpl(providerLogic());
         isLogicCreated = true;
    }

    this.mLogicWrf = new WeakReference<>(viewModel.getLogicImpl());
    if (checkLogicNonNull()) {
         getLogicImpl().bindLifecycle(this.getLifecycle());
         getLogicImpl().bindVo(this);
         if (isLogicCreated) getLogicImpl().onLogicCreated();
    }
```

(3). Activity页面销毁后onDestroy方法中移除生命周期方法并释放View资源
```
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (checkLogicNonNull()) {
            getLogicImpl().unbindLifecycle(this.getLifecycle());
            getLogicImpl().unbindVo();
        }
    }
```

### [LogicImpl](./src/main/java/com/racofix/basic/mvp/LogicImpl.java)
(1). Persenter绑定/解绑 Activity/Fragment 生命周期
```
   @Override
    final public void bindLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    final public void unbindLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }
```

(2). Persenter绑定/解绑View, 使用WeakReference保存View
```
    @Override
    final public void bindVo(V vo) {
        this.wrf = new WeakReference<>(vo);
    }

    @Override
    final public void unbindVo() {
        this.wrf.clear();
        this.wrf = null;
    }
```

(3). Persenter生命周期 Created/Destroy
```
    @Override
    public void onLogicCreated() {
    }

    @Override
    public void onLogicDestroy() {
        if (stateBundle != null && !stateBundle.isEmpty()) {
        stateBundle.clear();
     }
    }
```
### [LogicViewModel](./src/main/java/com/racofix/basic/mvp/LogicViewModel.java)
(1). ViewModel绑定 Persenter并调用onLogicCreated()方法.
```
    void setLogicImpl(T mLogic) {
        if (this.mLogicImpl == null && mLogic != null) {
            this.mLogicImpl = mLogic;
        }
    }
```
(2). 应用界面被销毁会调用ViewModel的onCleared()方法, 调用Persenter的onLogicDestroy, 释放资源
```
    @Override
    protected void onCleared() {
        super.onCleared();
        if (this.mLogicImpl != null) {
            this.mLogicImpl.onLogicDestroy();
            this.mLogicImpl = null;
        }
    }
```

### [LogicProviders](./src/main/java/com/racofix/basic/mvp/LogicProviders.java)
(1). 通过注解反射方法实例化 Persenter
```
   public static LogicI init(Class<?> clazz) {
        try {
            Implement annotation = clazz.getAnnotation(Implement.class);
            if (annotation != null)
                return (LogicI) annotation.value().newInstance();
            return null;
        } catch (InstantiationException e) {
            Log.e(LogicProviders.class.getSimpleName(), "Cannot create an instance of " + clazz, e);
            return null;
        } catch (IllegalAccessException e) {
            Log.e(LogicProviders.class.getSimpleName(), "Cannot create an instance of " + clazz, e);
            return null;
        }
    }
```
