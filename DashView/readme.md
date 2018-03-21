接到一个需求，从一个view到另一个view之间用虚线连接，终点画一个圆点。自定义一个DashView，适应两个View在任何相对位置。
实现效果如https://github.com/AceSui/MyAndroidSources/raw/master/DashView/screenshoots/device-2018-03-21-233315.png
注意点：在Activity中，要在onWindowFocusChanged（）事件中绘制，在Fragment中，要在根View的getView().getViewTreeObserver().addOnWindowFocusChangeListener（）中绘制，否则桌面View没有加载完毕，绘制无效。
自定义DashView有两种初始化方式，1，传入两个View，自适应布局；2，传入两个点绘制虚线。
