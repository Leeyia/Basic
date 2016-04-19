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
    compile 'com.github.meikoz:basic:1.0'
}
```

#### v1.0功能简介
-   Retrofit            网络请求封装
-   ServiceFactory      管理所有根据业务区分的Service
-   ToastTip            Toast提示的统一处理
-   SplashActivity      启动页面增加ScaleAnimation动画
-   BasicAdapter        ListView 的公共Adapter
-   RecyclerAdapter     RecyclerView的公共Adapter
-   BaseActivity        BaseFragment UI的公共基类


#### v1.1增加mvp架构
-   LogicProxy          mvp模式P层 Impl的初始化和View层进行绑定
-   LogicControl        PresenterImpl 和View的绑定
-   LoginImpl           mvp示例代码
-   GlideManager        Glide图片动态加载统一管理
-   LocationManager     百度地图定位管理类

#### v1.2通知栏沉浸式实现
-   StatusBarUtil       通知栏自定义样式
-   SwipBackActivity    仿简书实现右滑finsh页面 
-   WebViewActivity     WebView页面统一管理 实现仿微信进度条加载
-   EasyToolBar         自定义导航TitleBar
-   OnClickEvent        自定义按钮点击事件 可以控制Button的频繁点击
