# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AndroidSdkLocationWindows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#压缩级别0-7，Android一般为5(对代码迭代优化的次数)
-optimizationpasses 5

#不使用大小写混合类名
-dontusemixedcaseclassnames

 #混淆时记录日志
-verbose

#保持Activity子类里面的参数类型为View的方法不被混淆，如被XML里面应用的onClick方法
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#不警告support包中不使用的引用
-dontwarn android.support.**

#注释不混淆
-keepattributes *Annotation*