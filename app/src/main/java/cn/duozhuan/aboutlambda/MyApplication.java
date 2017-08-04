package cn.duozhuan.aboutlambda;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        AndroidLogAdapter adapter = new AndroidLogAdapter();
        Logger.addLogAdapter(adapter);
    }

    public static Context getContext() {
        return context;
    }
}
