package com.bwei.jd.app;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Handler;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader() {
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }

    public static DisplayImageOptions getOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .displayer(new FadeInBitmapDisplayer(500))
                .handler(new Handler()) // default
                .build();
        return options;
    }
}
