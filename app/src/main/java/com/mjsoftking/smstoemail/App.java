package com.mjsoftking.smstoemail;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.mjsoftking.dialogutilslib.init.DialogLibInitSetting;

/**
 * 用途：
 * <p>
 * 作者：MJSoftKing
 * 时间：2021/07/02
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);

        DialogLibInitSetting.getInstance()
                .setDebug(BuildConfig.DEBUG)
                .registerActivityLifecycleCallbacks(this);
    }
}
