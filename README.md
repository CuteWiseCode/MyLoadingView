
# 介绍
 MyLoadingView 是一个自定义的loading 动画, 支持配置不同图片（配置的图片大小为258 * 258）,以及旋转速度
 
# 使用
 Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.CuteWiseCode:MyLoadingView:1.0.0'
	}
  
Step 3. Use in xml
 
 
     <com.cutewisecode.AnimationView
        android:layout_centerInParent="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:translationspeed="400"
        app:image1="@mipmap/im_cabbage"
        app:image2="@mipmap/im_chinesechives"
        app:image3="@mipmap/im_greenchineseonion"
        app:image4="@mipmap/im_hyacinthbean"
        app:image5="@mipmap/im_rutabaga"
        />
  
    

    
# LoadingView
android loading view
效果图如下：

![image](https://github.com/CuteWiseCode/MyLoadingView/blob/master/image/show.gif)

源码解析请前往博客：

https://blog.csdn.net/Cute_Code/article/details/106794059
