package cn.duozhuan.aboutlambda;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidLogAdapter adapter = new AndroidLogAdapter();
        Logger.addLogAdapter(adapter);
    }
}
