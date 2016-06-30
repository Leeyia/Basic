通过之前发表的一篇 [你拥有它，让你Android开发更简单](http://www.jianshu.com/p/1fb7c5e1fee4)只是简单介绍了一下[Basic](https://github.com/meikoz/Basic)框架支持的功能，没有具体介绍使用方法和具体实现，有的人问我中实现思想，有的人问我他们项目重构这个框架适不适合？也有的人问我支持的功能太多了如果分开的话会好一点吧？
>   其实我最开始初衷是开发一套适合自己的 Android 框架，因为只要每一个新项目立项，就要搭建一套基础框架，可能是你之前代码的搬运，可能是重新设计。
>   开始没有想到开源，也没有想到会有这多人关注，后来想可以通过我个人的理解去帮助更多人，也是一件兴奋的事!
>   功能多是因为我想开发一套基础模板，所以他功能多，所以也不适合代码重构使用，适合新项目并且mvp模式项目使用。

## 项目框架支持的功能##
-   Mvp模式
-   网络请求的封装
-   强大的漂亮日志
-   列表数据的统一业务处理
-   多种样式上拉加载、下拉刷新
-   App crash 异常的处理
-   Loading页面的统一处理
-   快速实现广告轮播图、引导页功能
-   快速实现底部导航功能
-   右滑页面关闭当前Activity

## Mvp详解##
>   其中Mvp的好处，为什么要用mvp，mvp简单的例子是什么样的？这些网上有好多例子，我就不瞎bb了.

### 最常见mvp初始化方式:####
```
  LoginPresenter mPresenter = new LoginPresenterImpl(this);
  mPresenter.onLogin(username, password);
```
### Basic框架中mvp的实现思路:###
每次用到Presenter的时候每次都要重新New PresenterImpl.那可以把这些类放在一个大池子里面,类似于线程池一样,我用到的时候就把他取出来.
>LogicProxy详解:

```
    将所有的实现类放入到HashMap中保存,key是接口,value是实现类
    public void init(Class... clss) {
        List<Class> list = new LinkedList<Class>();
        for (Class cls : clss) {
            if (cls.isAnnotationPresent(Implement.class)) {
                //把Presenter接口放在LinkedList中维护
                list.add(cls);
                for (Annotation ann : cls.getDeclaredAnnotations()) {
                    if (ann instanceof Implement) {
                        try {
                        //把Presenter实现类放在HashMap中维护
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

   //提供一个方法获取Presenter实例并绑定View方法
  public <T> T bind(Class cls, BaseView o) {
        Object ret = m_objects.get(cls);
        ((BasePresenter) ret).attachView(o);
        return (T) ret;
    }
```

>   在BaseActivity中提供一个公共方法,子Activity中可直接调用

```
    //获得该页面的实例<BaseActivity>
    public <T> T getLogicImpl(Class cls, BaseView o) {
        return LogicProxy.getInstance().bind(cls, o);
    }
    
    //获取当前页面的实例<子LoginActivity> 这样是不是代码就简洁了好多
    ILoginImpl  mPresenter = getLogicImpl(ILogin.class, this);
    mPresenter.onLogin(username,password);
```

>  所有Presenter中肯定是有公共的部分,比如说绑定view,销毁view,获得view 的实例,那BasePresenter就是这些方法的实现?

```
public class BasePresenter<T extends BaseView> implements Presenter<T> {
    private T mView;

    @Override
    public void attachView(T mvpView) {
        this.mView = mvpView;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public boolean isViewBind() {
        return mView != null;
    }

    public T getView() {
        return mView;
    }
```

## 利用此框架你只需要实现简单的步骤:##

##### 1.Application中 init 需要初始化Presenter的接口#####

```
LogicProxy.getInstance().init(ILogin.class, IMain.class);
```

##### 2.Presenter的接口中加入注解@Implement(实现类.class)实现类实现绑定关系#####
```
@Implement(LoginLogic.class)
public interface ILogin {
    void login(String name, String passwrod);
}
```

##### 3.Presenter实现类中继承BasePresenter 实现presenter接口#####
```
public class LoginLogic extends BasePresenter implements ILogin {
    @Override
    public void login(String name, String passwrod) {
    }
}
```

##### 4.Activity中调用getLogicImpl方法获取Presenter实例#####
```
 mPresenter = getLogicImpl(ILogin.class, this);
((LoginLogic) mPresenter).login(username, password);
```

> 这样有没有让你的mvp代码简单易懂呢?代码也非常简洁,so easy 实现mvp.
> 更多详情请查看项目:https://github.com/meikoz/Basic
> 如果感觉对你有帮助请start 给予支持!