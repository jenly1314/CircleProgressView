# CircleProgressView

[![MavenCentral](https://img.shields.io/maven-central/v/com.github.jenly1314/circleprogressview?logo=sonatype)](https://repo1.maven.org/maven2/com/github/jenly1314/CircleProgressView)
[![JitPack](https://img.shields.io/jitpack/v/github/jenly1314/CircleProgressView?logo=jitpack)](https://jitpack.io/#jenly1314/CircleProgressView)
[![CI](https://img.shields.io/github/actions/workflow/status/jenly1314/CircleProgressView/gradle.yml?logo=github)](https://github.com/jenly1314/CircleProgressView/actions/workflows/gradle.yml)
[![Download](https://img.shields.io/badge/download-APK-brightgreen?logo=github)](https://raw.githubusercontent.com/jenly1314/CircleProgressView/master/app/release/app-release.apk)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen?logo=android)](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
[![License](https://img.shields.io/github/license/jenly1314/CircleProgressView?logo=open-source-initiative)](https://opensource.org/licenses/mit)


CircleProgressView for Android 是一个圆形的进度动画控件，动画效果纵享丝滑。

## 效果展示
![Image](GIF.gif)

> 你也可以直接下载 [演示App](https://raw.githubusercontent.com/jenly1314/CircleProgressView/master/app/release/app-release.apk) 体验效果

## 引入

### Gradle:

1. 在Project的 **build.gradle** 或 **setting.gradle** 中添加远程仓库

    ```gradle
    repositories {
        //...
        mavenCentral()
    }
    ```

2. 在Module的 **build.gradle** 中添加依赖项

    ```gradle
    implementation 'com.github.jenly1314:circleprogressview:1.1.3'
    ```

## 使用

### CircleProgressView自定义属性说明（进度默认渐变色）
| 属性 | 值类型 | 默认值 | 说明 |
| :------| :------ | :------ | :------ |
| cpvStrokeWidth | dimension |12dp| 笔画描边的宽度 |
| cpvNormalColor | color |<font color=#C8C8C8>#FFC8C8C8</font>| 圆正常颜色 |
| cpvProgressColor | color |<font color=#4FEAAC>#FF4FEAAC</font>| 圆进度颜色 |
| cpvStartAngle | integer | 270 | 开始角度，默认十二点钟方向 |
| cpvSweepAngle | integer | 360 | 扫描角度范围 |
| cpvMax | integer | 100 | 进度最大值 |
| cpvProgress | integer | 0 | 当前进度 |
| cpvDuration | integer | 500 | 动画时长 |
| cpvLabelText | string |  | 中间的标签文本，默认自动显示百分比 |
| cpvLabelTextColor | color |<font color=#333333>#FF333333</font>| 文本字体颜色 |
| cpvLabelTextSize | dimension |30sp| 文本字体大小 |
| cpvShowLabel | boolean | true | 是否显示文本 |
| cpvShowTick | boolean | true | 是否显示外环刻度 |
| cpvCirclePadding | dimension | 10dp | 外圆环刻度与内圆环间距 |
| cpvTickSplitAngle | integer | 5 | 刻度间隔的角度大小 |
| cpvBlockAngle | integer | 1 | 刻度的角度大小 |
| cpvTickOffsetAngle | integer | 0 | 刻度偏移的角度大小 |
| cpvTurn | boolean | false | 是否旋转 |
| cpvCapRound | boolean | true | 是否是圆形线冒（圆角弧度） |
| cpvLabelPaddingLeft | dimension |0dp| 文本居左边内间距 |
| cpvLabelPaddingTop | dimension |0dp| 文本居顶边内间距 |
| cpvLabelPaddingRight | dimension |0dp| 文本居右边内间距 |
| cpvLabelPaddingBottom | dimension |0dp| 文本居底边内间距 |

### 示例

布局示例
```Xml
    <com.king.view.circleprogressview.CircleProgressView
        android:id="@+id/cpv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

代码示例
```Java
    //显示进度动画，进度，动画时长
    circleProgressView.showAnimation(80,3000);
    //设置当前进度
    circleProgressView.setProgress(80);
    //设置进度改变监听
    circleProgressView.setOnChangeListener(onChangeListener);   

```
更多使用详情，请查看[app](app)中的源码使用示例或直接查看 [API帮助文档](https://jitpack.io/com/github/jenly1314/CircleProgressView/latest/javadoc/)

## 相关推荐

- [ArcSeekBar](https://github.com/jenly1314/ArcSeekBar) 一个弧形的拖动条进度控件，配置参数完全可定制化。
- [SpinCounterView](https://github.com/jenly1314/SpinCounterView) 一个类似码表变化的旋转计数器动画控件。
- [CounterView](https://github.com/jenly1314/CounterView) 一个数字变化效果的计数器视图控件。
- [RadarView](https://github.com/jenly1314/RadarView) 一个雷达扫描动画后，然后展示得分效果的控件。
- [SuperTextView](https://github.com/jenly1314/SuperTextView) 一个在TextView的基础上扩展了几种动画效果的控件。
- [LoadingView](https://github.com/jenly1314/LoadingView) 一个圆弧加载过渡动画，圆弧个数，大小，弧度，渐变颜色，完全可配。
- [WaveView](https://github.com/jenly1314/WaveView) 一个水波纹动画控件视图，支持波纹数，波纹振幅，波纹颜色，波纹速度，波纹方向等属性完全可配。
- [GiftSurfaceView](https://github.com/jenly1314/GiftSurfaceView) 一个适用于直播间送礼物拼图案的动画控件。
- [FlutteringLayout](https://github.com/jenly1314/FlutteringLayout) 一个适用于直播间点赞桃心飘动效果的控件。
- [DragPolygonView](https://github.com/jenly1314/DragPolygonView) 一个支持可拖动多边形，支持通过拖拽多边形的角改变其形状的任意多边形控件。
- [DrawBoard](https://github.com/jenly1314/DrawBoard) 一个自定义View实现的画板；方便对图片进行编辑和各种涂鸦相关操作。
- [compose-component](https://github.com/jenly1314/compose-component) 一个Jetpack Compose的组件库；主要提供了一些小组件，便于快速使用。


## 版本日志

#### v1.1.3：2021-9-2 (从v1.1.3开始发布至 MavenCentral)
*  对外暴露更多getter和setter方法
*  新增cpvTickOffsetAngle属性

#### v1.1.2：2020-12-2
*  优化设置渐变色过程处理

#### v1.1.1：2020-4-2
*  新增cpvLabelPadding..相关属性

#### v1.1.0：2019-8-19
*  移除appcompat依赖

#### v1.0.1：2019-5-23
*  新增cpvTurn属性（是否旋转）

#### v1.0.0：2019-2-17
*  CircleProgressView初始版本

---

![footer](https://jenly1314.github.io/page/footer.svg)

