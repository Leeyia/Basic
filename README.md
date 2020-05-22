# Logic-x

> 大家好，我是小新

> [https://github.com/racofix](https://github.com/racofix/Basic)


<br />MVP，顾名思义：<br />View 对应于Activity，fragment，负责View的绘制以及与用户交互<br />Model 依然是业务逻辑和实体模型<br />Presenter 负责完成View于Model间的交互Talk is cheap. Show me the code.<br />
<br />优点我就不介绍了，缺点可能都我一样的困扰：

- M/P 层都需要定义接口和实现类，创建对象占用内存
- 每一个页面都需要创建P实例并绑定View（重复代码）
- P层逻辑比较多的时候，开发时不容易考虑其他页面调用，复用难
- 页面销毁，P层执行异步操作，持有的View 引用造成内存泄露，程序崩溃


<br />我们既然知道它的缺点了，那我们就想办法解决

- 减少接口和类的定义，数据模型定义为Model，数据复杂的情况引入 Repository
- 利用用注解和反射，自动实现 P层实例化和View绑定/解绑 
- P层逻辑范围减少，页面（Activity/Fragment）可以多次复用，低耦合高复用
- 生成代理View，页面销毁不需要 `getView()!=null` 并不会造成内存泄露




---

<a name="ohjEV"></a>
## Usage
Talk is cheap. Show me the code.
<a name="EsfUL"></a>
### P层（逻辑）
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
    		if(successfully) getView().sign_in_success();
    }
}
```


<a name="b7Sa6"></a>
### V层（Activity/Fragment）
获取P实例 `getLogic(Login.class)` <br />缩小逻辑层的方法范围，降低到最小力度，比如说：登录页需要登录和注册功能，注册页需要注册功能。<br />那么登录和注册就可以作为逻辑层来实现，那登录页实现 登录/注册逻辑，注册页实现注册逻辑。
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


@LogicArr(Register.class)
public class RegisterActivity extends BaseLogicActivity
        implements RegisterI.View {

    @Override
    public void sign_up_success() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }
}
```


> 
> 

这样，就实现一个使用简单，结构清晰的MVP结构。<br />解决这些缺点思考了很久，如果你觉得新颖、实用、可以帮到您的话，给一份关注和鼓励。<br />同样，欢迎各位大神相互指导改进。准备做一些常用的框架 [Things2](https://github.com/Things2)<br />

> [更多示例：](https://github.com/racofix/Basic)[https://github.com/racofix/Basic](https://github.com/racofix/Basic)
> [框架实现：](https://github.com/Things2/Logic-x)[https://github.com/Things2/Logic-x](https://github.com/Things2/Logic-x)


---

<a name="50d77e6a"></a>
## 设计思路
<a name="xyXAA"></a>
### 核心类 LogicProvider
利用注解 `@LogicArr` 返回逻辑数组类，利用反射初始化逻辑类，然后绑定/解绑View放入Map中<br />上层通过 `getLogic(xxx.class)` 从Map中获取逻辑实例，页面销毁逻辑实例会自动从Map中释放
```
class LogicProvider {

    private static volatile LogicProvider logicProvider;
    private Map<Object, HashSet<BaseLogic>> logicCaches = new LinkedHashMap<>();

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

    <V> void put(V view) {
        LogicArr logicArr = view.getClass().getAnnotation(LogicArr.class);
        if (logicArr == null) return;

        if (logicCaches.containsKey(view)) return;
        HashSet<BaseLogic> logics = new HashSet<>();

        Class[] classes = logicArr.value();
        for (Class clazz : classes) {
            try {
                BaseLogic<V> baseLogic = Util.castTo(clazz.newInstance());
                baseLogic.bindView(view);
                baseLogic.onLogicCreated();
                logics.add(baseLogic);

            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (logics.isEmpty()) return;
        logicCaches.put(view, logics);
    }

    <V, T extends BaseLogic> T get(V view, Class<T> clazz) {
        HashSet<BaseLogic> logics = logicCaches.get(view);
        if (logics == null || logics.isEmpty()) {
            throw new NullPointerException(view.getClass().getName() + " @LogicArr is empty.");
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
        HashSet<BaseLogic> logics = logicCaches.get(view);
        if (logics == null || logics.isEmpty()) {
            return;
        }
        for (BaseLogic logic : logics) {
            if (logic.isViewBind()) logic.unbindView();
            logic.onLogicDestroy();
        }
        logicCaches.remove(view);
    }
}
```


<a name="WrqDp"></a>
### 核心类 BaseLogicImpl
利用WeakReference存储View，便于View对象回收，使用动态代理 `bindView(view)` 生成代理View，<br />页面销毁时，代理对象还存在，调用方法时候发现View是空了, 代理对象就什么都不做了. 这样既不用判断 `getView()!=null` 并且不会内存泄露。
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
                                if (isViewBind()){
                                    return method.invoke(weakReference.get(), args);
                                }
                                return null;
                            }
                        }));
    }

    @Override
    public void unbindView() {
        if (isViewBind()) {
            weakReference.clear();
            weakReference = null;
        }
    }

    @Override
    public boolean isViewBind() {
        return weakReference != null && weakReference.get() != null;
    }

    @Override
    public V getView() {
        return viewProxy;
    }
}
```


<a name="uDSbq"></a>
## 最后

- [项目示例代码](https://github.com/racofix/Basic) （[https://github.com/racofix/Basic](https://github.com/racofix/Basic)）
- [开源框架Things2-Logic-x](https://github.com/Things2/Logic-x) （[https://github.com/Things2/Logic-x](https://github.com/Things2/Logic-x)）


<br />希望利用业余时间和工作的积累，贡献更多有意义、有价值的项目。<br />如果有待改进，请大神们多多指导。<br />感谢大家耐心的阅读，如果项目对你有所帮助，希望大家给个关注，大家一起进步。
