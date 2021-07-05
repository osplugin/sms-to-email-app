package com.mjsoftking.smstoemail.utils;

import android.util.Log;

import com.mjsoftking.smstoemail.BuildConfig;


/**
 * 用途：log打印，debug版本才会存在打印
 * <p>
 * 作者：mjSoftKing
 * 时间：2021/02/02
 */
public class LogUtils {

    private final static boolean DEBUG = BuildConfig.DEBUG;

    public static void e(Object tag, String msg) {
        e(tag.getClass().getSimpleName(), msg);
    }

    public static void e(Object tag, String msg, Throwable tr) {
        e(tag.getClass().getSimpleName(), msg, tr);
    }

    public static void w(Object tag, String msg) {
        w(tag.getClass().getSimpleName(), msg);
    }

    public static void w(Object tag, String msg, Throwable tr) {
        w(tag.getClass().getSimpleName(), msg, tr);
    }

    public static void d(Object tag, String msg) {
        d(tag.getClass().getSimpleName(), msg);
    }

    public static void d(Object tag, String msg, Throwable tr) {
        d(tag.getClass().getSimpleName(), msg, tr);
    }

    public static void i(Object tag, String msg) {
        i(tag.getClass().getSimpleName(), msg);
    }

    public static void i(Object tag, String msg, Throwable tr) {
        i(tag.getClass().getSimpleName(), msg, tr);
    }


    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.e(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.w(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.i(tag, msg, tr);
        }
    }

}
