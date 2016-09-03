
## Basic Describe
Basic 是一个Android基础开发框架，封装了retrofit + okhttp + logger 操作的框架，使其更简单易用且入口统一。
封装mvp设计模式让其更简单易懂，最终实现快速开发APP。我们提倡用最少的代码，完成最多的操作，用最高的效率，完成最复杂的功能。
BasePersenter统一处理所有P 逻辑，网络请求retrofit2.0 支持缓存功能，强大的日志框架快速锁定Class，多种样式刷新加载样式，快速实现轮播广告图，底部导航功能。

## Usage Gradle
build.gradle中添加：
```
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.meikoz:basic:2.0.6'
}
```

## Usage Help
很遗憾，没有帮助文档，但这几篇博客也许能帮到你:

[Mvp模块](https://github.com/meikoz/Basic/blob/master/Android%E4%B8%AD%E5%AE%9E%E7%8E%B0mvp%E6%A8%A1%E5%BC%8F%E7%9A%84%E6%96%B0%E6%80%9D%E8%B7%AF.md)

