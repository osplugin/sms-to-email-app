package com.mjsoftking.smstoemail.utils;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.mjsoftking.smstoemail.bean.LocalConfig;

public class LocalConfigUtils {
    private final static String KEY_CONFIG = "KEY_CONFIG";

    /**
     * 读取配置
     */
    public static LocalConfig getLocalConfig() {
        String config = SPUtils.getInstance().getString(KEY_CONFIG);
        if (TextUtils.isEmpty(config)) return null;

        return JSON.parseObject(config, LocalConfig.class);
    }

    /**
     * 保存配置
     */
    public static void setLocalConfig(LocalConfig config) {
        if (!ObjectUtils.isEmpty(config)) {
            SPUtils.getInstance().put(KEY_CONFIG, JSON.toJSONString(config));
        } else {
            SPUtils.getInstance().remove(KEY_CONFIG);
        }
    }


}
