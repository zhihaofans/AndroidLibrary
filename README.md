# AndroidLibrary

目录下的`android_library/`下的为本库源码


### 使用方式:

在[Releases](https://github.com/zhihaofans/AndroidLibrary/releases/latest)下载最新的aar文件到项目的`/app/libs/`目录下

将 

`implementation fileTree(dir: 'libs', include: ['*.jar'])`

改为

`implementation fileTree(dir: 'libs', include: ['*.jar','*.aar'])`


