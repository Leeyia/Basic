## BasicApp功能介绍
>   欢迎Follow我的Github:https://github.com/meikoz

## Usage Gradle
```
repositories {
    maven { url "https://jitpack.io" }
}
```

```
dependencies {
    compile 'com.github.meikoz:basic:1.4'
}
```

Application 初始化IPresenter 
```
 LogicProxy.getInstance().init(
                 LoginLogic.class, MainLogic.class, CompeteLogic.class ...);
```

IPresenter 中Implemet关联自己的实现类 extends MvpLogic<T>
```
@Implement(LoginLogicImpl.class)
public interface LoginLogic extends BaseLogic<LoginView> {

    void login(String name, String passwrod);
}
```



