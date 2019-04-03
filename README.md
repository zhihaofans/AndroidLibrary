# AndroidLibrary

目录下的[`android_library/`](https://github.com/zhihaofans/AndroidLibrary/tree/master/android_library/src/main/java/io/zhihao/library/android/)下的为本库源码,库包名`io.zhihao.library.android`

Demo: [zhihaofans/Android.Box](https://github.com/zhihaofans/Android.Box/)

### 使用方式:

在[Releases](https://github.com/zhihaofans/AndroidLibrary/releases/latest)下载最新的aar文件到项目的`/app/libs/`目录下

将 

`implementation fileTree(dir: 'libs', include: ['*.jar'])`

改为

`implementation fileTree(dir: 'libs', include: ['*.jar','*.aar'])`


### 需求

- Android SDK > 26