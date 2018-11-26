## 简介
showing the connection between Architecture components and MVP pattern,
help you design robust, testable and maintainable apps.
MVP 模式和 [Architecture components](https://developer.android.google.cn/topic/libraries/architecture/)
结合使用，帮助您方便管理生命周期、设计健壮、可测试和可维护的程序。


## 优点
- [x] 模型与视图完全分离
- [x] 屏幕发生改变时保持 Presenter 状态。
- [x] Lifecycle、ViewModel 和 Presenter 生命周期绑定，Presenter 自己管理生命周期逻辑。
- [x] 通过注解实例化 Presenter，Presenter不会被多次创建。


## 用法
Download [the latest JAR](https://github.com/meikoz/Basic/tree/master/basics/mvp) or configure this dependency:
```
implementation 'com.racofix.basic2:mvp:1.0.0'
```

#### Sample Login:
**1.design our Presenter Contract and View**
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

**2.create LoginLogicImpl extends LogicImpl<LoginLogic.Vo> that implements our LoginLogic.Vo**
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

**3.create LoginActivity extends BaseActivity<LoginLogicImpl> implements LoginLogic.Vo, @Implement Presenter class**
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



