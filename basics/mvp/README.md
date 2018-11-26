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
implementation 'com.racofix.basic2:mvp:1.0.0'
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


## 解析
![](https://upload-images.jianshu.io/upload_images/893513-071dc47f4a67d508.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### [BaseActivity](./basics/mvp/src/main/java/com/racofix/basic/mvp/BaseActivity.java)
(1). 通过providerLogic()方法解析注解Implement实例化Presenter.
```
private T providerLogic() {
    return (T) LogicProviders.init(this.getClass());
}
```

(2). 将实例化的Presenter绑定ViewModel, 将Activity的生命周期通过Lifecycle交给Presenter后并绑定View.
```
LogicViewModel<T> viewModel = ViewModelProviders.of(this).get(LogicViewModel.class);
if (viewModel.getLogicImpl() == null) {
    viewModel.setLogicImpl(providerLogic());
}

this.mLogicWrf = new WeakReference<>(viewModel.getLogicImpl());
if (checkLogicNonNull()) {
    getLogicImpl().bindLifecycle(this.getLifecycle());
    getLogicImpl().bindVo(this);
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
TODO


## License
```
Copyright 2018 meikoz.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```



