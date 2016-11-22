
![576d518130720.png](http://upload-images.jianshu.io/upload_images/893513-1def4a8f5cf23400.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
## 搭建一个新的Android项目，你会怎么做？
```
每个人对应用框架的理解不相同，但是最终达到的效果应该是一样：
①降低项目的复杂性
②易扩展、易修改、可重用性强、可维护性强
③职责单一，功能清晰
```
在android开发项目中，我们首先要考虑每个项目的共同点，比如说：Mvp、网络请求层、Base存放View的基类、Log日志、App crash、刷新加载更多、Loading、广告图、支持ListView,RecyclerView的BaseAdater、通知栏沉浸式、图片加载缓存、底部导航功能．．．
那么这些功能是每个项目都必须需要的功能，那么可不可以以把这些通用的东西抽取呢？

## 项目工程搭建
[App工程结构搭建：几种常见Android代码架构分析](http://blog.csdn.net/hpb21/article/details/45399569)

![App工程包结构.png](http://upload-images.jianshu.io/upload_images/893513-24d5f6406fc91d8f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

包名的作用一目了然，在别人接手这个项目的时候就会相对简单
- adapter 适配器，如果业务复杂，根据不同的业务可以添加子包来进行分类
- api 网络请求层的封装 retrofit &okhttp3 &rxjava
- base 用来存放View的基类，例如BaseAcitivity、BaseFragment、BaseSwipeBackActivity、BasePresent
- manage 用来存放一些通用的管理类: Crash、ImageLoader、Logger
- model 封装Mvp Logic池，通过注解的方式contact和presenter进行实例化和bindView，使用时直接使用mPresenter
- ui 自定义View 滑动删除，底部导航

## common libraries 开源库的选型
- android-architecture: [谷歌Mvp示例code-Android Architecture MVP + Dagger2](https://github.com/googlesamples/android-architecture)
- retrofit: [retrofit Square出品的网络请求库，极大的减少了http请求的代码和步骤.](https://github.com/square/retrofit)
- okhttp: [据说Android4.4的源码中可以看到HttpURLConnection已经替换成OkHttp实现了](https://github.com/square/okhttp)
- rxjava: [rxjava提供优雅的响应式Api解决异步请求.](https://github.com/ReactiveX/RxJava)
- ButterKnife: [ButterKnife解放控件对象实例化,从此告别findviewbyId](http://www.cnblogs.com/flyme/p/4517560.html)
- gilde: [Google推荐:Glide功能强大图片加载缓存库](https://github.com/bumptech/glide)
- LeakCanary: [LeakCanarySquare出品的专门用来检测Android和Java的内存泄漏](https://github.com/square/leakcanary)
```
//squareup
compile 'com.squareup.retrofit2:retrofit:2.1.0'
compile 'com.squareup.retrofit2:converter-gson:2.1.0'
compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'
compile 'com.jakewharton:butterknife:7.0.1'
compile 'com.github.bumptech.glide:glide:3.7.0'
compile 'io.reactivex:rxjava:1.1.5'
compile 'io.reactivex:rxandroid:1.2.0'
compile 'com.jcodecraeer:xrecyclerview:1.2.7'
```

## 项目新建重构
对Android一些常用功能做一些整理封装成basic框架，方便Android初始项目快速开发
[Basic框架](https://github.com/meikoz/Basic)
[仿开眼Demo示例:Basic+Retrofit+Okhttp+Rxjava+Glide](https://github.com/meikoz/Eyepetizer)
请大家多多关注star和提意见给予支持，从自己的一些实践经验来总结这部分通用的东西作为一份善意的分享。
> @User:码农小阿新
> [@GitHub: https://github.com/meikoz](https://github.com/meikoz)

## Where is the use ?
- 新颖的Mvp模式
- 网络请求的封装
- 强大的漂亮日志
- 列表数据的统一业务处理
- 多种样式上拉加载、下拉刷新
- App crash 异常的处理
- 快速实现广告轮播图、引导页功能
- 快速实现底部导航功能
- 右滑页面关闭当前Activity
- 万能的Adapter
- 通知栏沉浸式
- Glide加载圆角图片 GlideCircleTransform

## How Usage
####Step 1:
Setup 'Basic' dependencies in build.gradle file:
```
dependencies {
    compile 'com.meikoz.basic:core:1.0.5'
}
```
####Step 2:
Create subclass of Application extends MainApplication，initialize basic BuildConfig.debug is true in super.onCreate() method before for debug print log.
```
public class BaseApp extends MainApplication {
    @Override
    public void onCreate() {
        RestApi.getInstance().deBug(true);
        super.onCreate();
    }
}
```
- RestApi.getInstance().deBug(true); 是为了Debug打印日志,请上线前删掉此方法#重要#

####Step 3:
app usage mvp pattern,  View and Presenter need things.
View extends BaseView. Presenter extends BasePresenter<View>.
```
@Implement(MainLogicImpl.class)
public interface MainLogicI {
    void onLoadData2Remote();

    interface MainView extends BaseView {
        void onLoadSuccessHandler(String responce);
    }
}

public class MainLogicImpl extends BasePresenter<MainLogicI.MainView>
    implements MainLogicI {
    @Override
    public void onLoadData2Remote() {
        getView().onLoadSuccessHandler("请求成功,返回的数据");
    }
}
```
How to initialize use the Activity.
```
class MainActivity extends Activity implements MainLogicI.MainView {

   @Override
    protected Class getLogicClazz() {
        return your interface MainLogicI class;// MainLogic是Presenter要实现的接口
    }

    @Override
    protected void onInitData2Remote() {
        super.onInitData2Remote();
       ((MainLogicImpl) mPresenter).onLoadData2Remote();
    }

    @Override
    public void onLoadSuccessHandler(String response) {
    //response是Presenter中返回的数据
   }
}
```
- super.onInitData2Remote(); 会初始化Presnter并bindView

####Step 4:
Network: Retrofit + okhttp
根据不同业务生成不同Service
```
public class ApiManager {
    public static APIService createApi() {
        return RestApi.getInstance().create(APIService.class);
    }
    public static UserService createUserApi() {
        return RestApi.getInstance().create(UserService.class);
    }
}

public interface APIService {
    String BASE_URL ="https://github.com/";
    @GET("api/v1/user")
    Call<Response> getUserInfo(@Query("uid") int uid);
}
```

##  cool feature
#### feature 1：实现 Tab +fragment 功能
![QQ图片20161121164914.png](http://upload-images.jianshu.io/upload_images/893513-1cdd1d8c8e4de636.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```
public class MainAty extends AppCompatActivity {
    private TabStripView navigateTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigateTabBar = (TabStripView) findViewById(R.id.navigateTabBar);
        navigateTabBar.onRestoreInstanceState(savedInstanceState);

        navigateTabBar.addTab(HomeFragment.class,
            new TabStripView.TabParam(R.drawable.ic_tab_strip_icon_feed,
                R.drawable.ic_tab_strip_icon_feed_selected,
                R.string.tab_bar_text_feed));

        navigateTabBar.addTab(DiscoveryFragment.class,
            new TabStripView.TabParam(R.drawable.ic_tab_strip_icon_category,
                R.drawable.ic_tab_strip_icon_category_selected,
                R.string.tab_bar_text_category));

        navigateTabBar.addTab(AutoFragment.class,
            new TabStripView.TabParam(R.drawable.ic_tab_strip_icon_pgc,
                R.drawable.ic_tab_strip_icon_pgc_selected,
                R.string.tab_bar_text_pgc));

        navigateTabBar.addTab(ProfileFragment.class,
            new TabStripView.TabParam(R.drawable.ic_tab_strip_icon_profile,
                R.drawable.ic_tab_strip_icon_profile_selected,
                R.string.tab_bar_text_profile));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        navigateTabBar.onSaveInstanceState(outState);
    }
}
```
#### feature 2：实现仿ios弹窗效果
![默认取消确定按钮.png](http://upload-images.jianshu.io/upload_images/893513-7f1ac79edc12065c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- setCancelable(true) 默认显示取消按钮,用法和原生AlertDialog写法一样
```
new SweetAlertDialog.Builder(MainActivity.this)
             .setTitle("标题")
             .setMessage("描述详细内容?")
             .setCancelable(true)
             .setPositiveButton("确认", new SweetAlertDialog.OnDialogClickListener() {
                  @Override
                   public void onClick(Dialog dialog, int which) {
                      Toast.makeText(MainActivity.this, "确定按钮", Toast.LENGTH_SHORT).show();
                   }
              })
             .show();
```
![只有一个确定按钮.png](http://upload-images.jianshu.io/upload_images/893513-4a519d42be5ad4f2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![Uploading QQ图片20161121171109_005140.png . . .]

- setCancelable(false) 这样就只显示一个按钮
```
new SweetAlertDialog.Builder(MainActivity.this)
             .setTitle("标题")
             .setMessage("描述详细内容?")
             .setCancelable(false)
             .setPositiveButton("确认", new SweetAlertDialog.OnDialogClickListener() {
                  @Override
                   public void onClick(Dialog dialog, int which) {
                      Toast.makeText(MainActivity.this, "确定按钮", Toast.LENGTH_SHORT).show();
                   }
              })
             .show();
```
![左右边都设置按钮.png](http://upload-images.jianshu.io/upload_images/893513-583cc4507caf1a6f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 左右按钮自定义
```
new SweetAlertDialog.Builder(MainActivity.this)
             .setTitle("标题")
             .setMessage("描述详细内容?")
             .setCancelable(false)
             .setNegativeButton("左边", new SweetAlertDialog.OnDialogClickListener() {    
              @Override    
              public void onClick(Dialog dialog, int which) {        
                      Toast.makeText(MainActivity.this, "左边" + which, Toast.LENGTH_SHORT).show();    
              }})
             .setPositiveButton("确认", new SweetAlertDialog.OnDialogClickListener() {
                  @Override
                   public void onClick(Dialog dialog, int which) {
                      Toast.makeText(MainActivity.this, "确定按钮", Toast.LENGTH_SHORT).show();
                   }
              })
             .show();
```
#### feature 3：CommonAdapter for ListView, RecyclerAdapter for RecyclerView 
CommonAdapter for ListView, RecyclerAdapter for RecyclerView 
实现 void convert(ViewHolder helper, T item); Replace getView（）;

#### feature 4：Logger 漂亮的日志系统
Logger.d(message);
Logger.i(message);
Logger.e(message);
Logger.v(message);
Logger.json(message);
![漂亮的日志.png](http://upload-images.jianshu.io/upload_images/893513-11a5574bd288548e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### feature 5：Android Crash异常处理
```
Application onreate方法中初始化 异常保存本地,可以进行服务上传
AndroidCrash.getInstance().init(this);
```

#### feature 6：快速实现广告轮播图CustomViewpager
```
CustomViewPageAdapter adapter = new CustomViewPageAdapter(getActivity(), getAdData());
mViewpage.updateIndicatorView(getAdData().size(), 0);
mViewpage.setAdapter(adapter);
mViewpage.startScorll();
```


#### feature 7：实现右滑关闭当前页面

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/893513-ebe288b8a20f38e0.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## To do something v2.0.0
- 网络请求前增加LoadingView，加完完成消失
- 加载失败统一失败页面，支持重新请求
- Activity销毁掉，关闭网络请求功能 避免报错
- EyepetizerApp: 使用Basic框架完成开眼App

## Email
zhoujinlongdev@163.com

##License
```
Copyright 2016 meikoz

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
