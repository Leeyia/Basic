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
design our LoginLogic(Presenter Contract) and LoginLogic.Vo (View)

```
public interface LoginLogic {

    interface Logic {
        void login(String username, String password);
    }

    interface Vo extends BaseLogic.Vo {
        void successful(String msg);

        void failed(String failMsg);
    }
}
```

create LoginLogicImpl that implements our LoginLogic.
```
public class LoginLogicImpl extends BaseLogicImpl<LoginLogic.Vo> implements LoginLogic.Logic {

    @Override
    public void onLogicCreated() {
        super.onLogicCreated();
        // LoginLogicImpl Created
    }

    @Override
    public void onLogicDestroy() {
        super.onLogicDestroy();
        // LoginLogicImpl Destroy
    }

    @Override
    public void login(String username, String password) {

    }
}
```

Activity usage LoginLogicImpl method, LoginLogic.Vo call results
```
<!--@Implement for init LoginLogicImpl instance-->
@Implement(LoginLogicImpl.class)
public class LoginActivity extends BaseAct<LoginLogicImpl> implements LoginLogic.Vo {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getLogic().login("admin", "admin");
    }

    @Override
    public void successful(String message) {

    }

    @Override
    public void failed(String failMsg) {

    }
}
```

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



