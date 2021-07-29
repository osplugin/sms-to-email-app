package com.mjsoftking.smstoemail.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.blankj.utilcode.util.TimeUtils;
import com.mjsoftking.smstoemail.utils.LogUtils;
import com.mjsoftking.smstoemail.utils.SendEMileUtils;

import java.util.Date;
import java.util.Locale;


public class SmsReceiver extends BroadcastReceiver {

    private static final String SMS_EXTRA_NAME = "pdus";
    private final static String TAG = SmsReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder messagev = new StringBuilder();
        LogUtils.e(TAG, "free message ");

        Bundle extras = intent.getExtras();
        if (extras != null) {
            try {
                Object[] smsExtra = (Object[]) extras.get(SMS_EXTRA_NAME);
                LogUtils.e(TAG, "free message ");
                String format = "发信人: %s \n" +
                        "收信时间: %s \n" +
                        "内容: %s \n";
                for (Object o : smsExtra) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) o);

                    messagev.append(String.format(Locale.getDefault(), format,
                            sms.getDisplayOriginatingAddress(),
                            TimeUtils.date2String(new Date(sms.getTimestampMillis())),
                            sms.getMessageBody()));
                }
                LogUtils.e(TAG, "free message : " + messagev);

                SendEMileUtils.getInstance().sent(messagev.toString());

            } catch (Exception e) {
                LogUtils.e(TAG, e.getMessage(), e);
            }
        } else {
            messagev.append("获取短信失败，请检查是否开放短信权限或关闭验证码安全选项");
            SendEMileUtils.getInstance().sent(messagev.toString());
        }
    }
}