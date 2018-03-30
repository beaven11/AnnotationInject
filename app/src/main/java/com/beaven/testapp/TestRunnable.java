package com.beaven.testapp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * 创建时间: 2018/03/28 17:51<br>
 * 创建人: 王培峰<br>
 * 修改人: 王培峰<br>
 * 修改时间: 2018/03/28 17:51<br>
 * 描述:
 */
public class TestRunnable implements Runnable {

    private static final String TAG = "TestRunnable";
    private Handler handler;
    private Looper looper;

    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        handler = new Handler(looper);
        Log.d(TAG, "run: 中间---");
        Looper.loop();
        Log.d(TAG, "run: 最后");
    }

    public Handler getHandler() {
        return handler;
    }

    public boolean quit() {
        if (looper != null) {
            looper.quit();
            return true;
        }
        return false;
    }
}
