## Welcome!
Architecture is like teenage sex，everybody talks about it，nobody
really knows what is it。
> u feel useful please star，continuous update this basic!
万水千山总是情，给个star行不行?

## Where is the use ?
- 新颖Mvp模式
- 网络请求的封装 retrofit + rxjava +okhttp
- 强大的漂亮日志 
- 加载/刷新 多样
- App crash 异常的处理
- 快速实现广告轮播图、引导页功能
- 快速实现底部导航功能
- 右滑页面关闭当前Activity
- 万能的Adapter

## How Usage
####Step 1:
Setup 'Basic' dependencies in build.gradle file:
```
dependencies {
    compile 'com.meikoz.basic:core:1.0.6'
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
- RestApi.getInstance().deBug(true); 是为了Debug打印日志,请上线前删掉此方法(重要)

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
## See Details
> [详细链接请点击](https://github.com/meikoz/Basic/wiki/Usage)

> [Eyepetizer示例Demo](https://github.com/meikoz/Eyepetizer)

## To do something v2.0.0
- 网络请求前增加LoadingView，加完完成消失
- 加载失败统一失败页面，支持重新请求
- Activity销毁掉，关闭网络请求功能 避免报错
- EyepetizerApp: 使用Basic框架完成开眼App

## email
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
