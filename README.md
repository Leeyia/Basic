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
    compile 'com.github.meikoz:basic:2.0.0'
}
```

##Basic框架支出的功能##
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

>	Mvp模式

```
Application onreate方法中初始化presenter
LogicProxy.getInstance().init(
                LoginLogic.class, MainLogic.class...);
				
在Activity中直接取出presenter实例绑定view
LoginLogic mLoginLogic = getLogicImpl(LoginLogic.class, this);
```
>	简单、漂亮、实用的Logcat

```
Application onreate方法中初始化
Logcat.init("com.android.logcat").hideThreadInfo().methodCount(3);

在需要的地方使用 "com.android.logcat" 是Logcat的TAG
Logcat.d(message);
Logcat.i(message);
```
App打开哪个页面直接能找到相应的类
![Paste_Image.png](http://upload-images.jianshu.io/upload_images/893513-eb0ff884da74a2de.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>   网络请求Retrofit2.0封装和使用

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/893513-285cc96ea6f72b95.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
定义生成自己相应的Service，BASE_URL是服务器地址,这样就简单实现的网络调用
```
 Factory.provideHttpService().getImageClassify(page).enqueue(new Callback<Classify>() {
            @Override
            public void onResponse(Call<Classify> call, Response<Classify> response) {
                onLoadListSuccessHandle(response, isMore);
            }

            @Override
            public void onFailure(Call<Classify> call, Throwable t) {
                onLoadFail("请求失败,原因..");
            }
        });
```
统一处理列表加载业务逻辑类

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/893513-49a61c1e17294436.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>XRecyclerView实现多种下拉刷新,上拉加载样式

支持addHeaderView、footerView
```
LinearLayoutManager layoutManager = new LinearLayoutManager(context); layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
mRecy.setLayoutManager(layoutManager);
//设置分隔线
mRecy.addItemDecoration(new SpacesItemDecoration(1));
mRecy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//设置加载更多样式
mRecy.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
mRecy.setArrowImageView(R.drawable.abc_icon_down_arrow);
```

>Android Crash异常处理

```
Application onreate方法中初始化 异常保存本地,可以进行服务上传
AndroidCrash.getInstance().init(this);
```
> 快速实现广告轮播图CustomViewpager

```
CustomViewPageAdapter adapter = new CustomViewPageAdapter(getActivity(), getAdData());
mViewpage.updateIndicatorView(getAdData().size(), 0);
mViewpage.setAdapter(adapter);
mViewpage.startScorll();
```

> 快速实现底部导航功能

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/893513-7e7c980787a3f2d2.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


>实现右滑关闭当前页面

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/893513-ebe288b8a20f38e0.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

￼
￼


