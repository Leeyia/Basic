MVP，顾名思义：
View 对应于Activity，fragment，负责View的绘制以及与用户交互
Model 依然是业务逻辑和实体模型
Presenter 负责完成View于Model间的交互
![1090080-4635fc0a530a058c.webp](https://cdn.nlark.com/yuque/0/2020/webp/644743/1589972143902-0110cdaa-d2c2-4e81-a45c-c2c85806f4e6.webp)

优点
- 低耦合高复用
- 低耦合 视图/模型/逻辑 相互分离
- 高复用 多个视图复用相同逻辑
- 可单独进行数据和逻辑测试（单元测试）

缺点
- 定义大量接口和实现类，创建对象浪费内存
- 视图销毁，逻辑层异步请求会导致持有视图引用内存泄漏
- 多视图复用会导致实现一些用不到的方法
- 视图层实例化逻辑对象的方法相似


## 示例
那么，怎样可以又扩展它的优点又避免它的缺点呢?
这个问题困扰了很久，也想了很久，想到了一些解决办法并且已经实践很长时间了，并未发现严重问题。
今天分享出来，只是个人想法，如有错误欢迎大神指导改进。


### 使用（Usage）
```
repositories {
	maven { url 'https://dl.bintray.com/meikoz/Things2' }
}

dependencies {
    // androidx
    implementation 'com.racofix.things2:logic-x:1.1.4'
}
```

### 逻辑层（Logic )
```
public interface LoginI {
    interface Logic {
        void sign_in(String username, String password);
    }

    interface View {
        void sign_in_success();
    }
}

public class Login extends BaseLogicImpl<LoginI.View> implements LoginI.Logic {

    @Override
    public void sign_in(String username, String password) {
        /*do some things*/
        getView().sign_in_success();
    }
}
```

### 视图层（View）
```
@LogicArr({Login.class, Register.class})
public class LoginActivity extends BaseLogicActivity implements
        LoginI.View, RegisterI.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLogic(Login.class).sign_in("用户名", "密码");
    }

    @Override
    public void sign_in_success() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sign_up_success() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }
}
```


## 原理解析
> 减少接口和类的数量
ILogin 作为 LoginContract 定义方法和View，数据实体对象作为Model，数据复杂的情况可以创建一个DataRepository 用于本地数据和远程数据切换。


> 低耦合高复用
缩小逻辑层的方法范围，降低到最小力度，比如说：登录页需要登录和注册功能，注册页需要注册功能。
那么登录和注册就可以作为逻辑层来实现，那登录页实现 登录/注册逻辑，注册页实现注册逻辑。
```
@LogicArr(Register.class)
public class RegisterActivity extends BaseLogicActivity
        implements RegisterI.View {

    @Override
    public void sign_up_success() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }
}
```


> 视图销毁，逻辑层异步请求会导致持有视图引用内存泄漏
页面销毁之后，逻辑层的View会变成空，在绑定的时候使用 `WeakReference` 存储 View 这样利于Gc 回收对象，
为了避免在逻辑层调用 `getView()` 做非空判断，在绑定View 时创建 View 代理 使用  `getView()`返回代理对象，
如果View 是空的时候，代理对象就什么都不做.
```
public class BaseLogicImpl<V> implements BaseLogic<V> {

    private V viewProxy;
    private WeakReference<V> weakReference;

    @Override
    public void bindView(final V view) {
        weakReference = new WeakReference<>(view);
        viewProxy = Util.castTo(
                Proxy.newProxyInstance(
                        view.getClass().getClassLoader(),
                        view.getClass().getInterfaces(),
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                if (isViewBind())
                                    return method.invoke(weakReference.get(), args);
                                return null;
                            }
                        }));
    }

    @Override
    public boolean isViewBind() {
        return weakReference != null && weakReference.get() != null;
    }

    @Override
    public V getView() {
        return viewProxy;
    }

    @Override
    public void unbindView() {
        if (isViewBind()) {
            weakReference.clear();
            weakReference = null;
        }
    }
}
```


> 视图层实例化逻辑对象
利用注解和反射实现自动实例化逻辑对象，自动绑定/解绑 View
那么视图层想要实例化时只需要添加 [@LogicArr](https://github.com/Things2/Logic/blob/master/logic-x/src/main/java/com/racofix/things2/mvp/LogicArr.java) 注解， 获取逻辑对象 `getLogic(xxx.class)`
使用非常简单方便！！!
```
class LogicProvider {

    private static volatile LogicProvider logicProvider;
    private Map<String, HashSet<BaseLogic>> logicCaches = new LinkedHashMap<>();

    static LogicProvider getInstance() {
        if (logicProvider == null) {
            synchronized (LogicProvider.class) {
                if (logicProvider == null) {
                    logicProvider = new LogicProvider();
                }
            }
        }
        return logicProvider;
    }

	/*解析注解-> 反射实例化-> 绑定View*/
    <V> void put(V view) {
        LogicArr logicArr = view.getClass().getAnnotation(LogicArr.class);
        if (logicArr == null) return;

        String viewKey = view.getClass().getName();
        if (logicCaches.containsKey(viewKey)) return;

        HashSet<BaseLogic> logics = new HashSet<>();

        Class[] classes = logicArr.value();
        for (Class clazz : classes) {
            try {
                BaseLogic<V> baseLogic = Util.castTo(clazz.newInstance());
                baseLogic.attach(view);
                baseLogic.onLogicCreated();
                logics.add(baseLogic);

            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        if (logics.isEmpty()) return;
        logicCaches.put(viewKey, logics);
    }

    /*缓存获取逻辑对象*/
    <V, T extends BaseLogic> T get(V view, Class<T> clazz) {
        String viewKey = view.getClass().getName();
        HashSet<BaseLogic> logics = logicCaches.get(viewKey);
        if (logics == null || logics.isEmpty()) {
            throw new NullPointerException(viewKey + " @LogicArr is empty.");
        }

        T baseLogic = null;
        for (BaseLogic logic : logics) {
            String logicName = logic.getClass().getName();
            if (logicName.equals(clazz.getName())) {
                baseLogic = Util.castTo(logic);
                break;
            }
        }

        return baseLogic;
    }

    <V> void remove(V view) {
        String viewKey = view.getClass().getName();
        HashSet<BaseLogic> logics = logicCaches.get(viewKey);
        if (logics == null || logics.isEmpty()) {
            return;
        }
        for (BaseLogic logic : logics) {
            logic.detach();
            logic.onLogicDestroy();
        }
        logicCaches.remove(viewKey);
    }
}
```


## 最后
- [项目示例代码](https://github.com/racofix/Basic) （https://github.com/racofix/Basic）
- [开源框架Things2-Logic] (https://github.com/Things2/Logic)

希望利用业余时间和工作的积累，贡献更多有意义、有价值的项目。
如果有待改进，请大神们多多指导。
感谢大家耐心的阅读，如果项目对你有所帮助，希望大家给个关注，大家一起进步。