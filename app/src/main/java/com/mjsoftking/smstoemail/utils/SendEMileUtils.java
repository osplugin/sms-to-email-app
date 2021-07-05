package com.mjsoftking.smstoemail.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.Utils;
import com.mjsoftking.smstoemail.R;
import com.mjsoftking.smstoemail.bean.LocalConfig;

import java.util.Locale;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 用途：
 * <p>
 * 作者：MJSoftKing
 * 时间：2021/07/02
 */
public class SendEMileUtils {

    private final static String TAG = SendEMileUtils.class.getSimpleName();
    private final static SendEMileUtils INSTANCE = new SendEMileUtils();

    /**
     * 重试次数
     */
    private final static int tryCount = 10;
    /**
     * 重试间隔, 秒
     */
    private final static int tryInterval = 10;

    public static SendEMileUtils getInstance() {
        return INSTANCE;
    }

    /**
     * 发送邮件
     *
     * @param msg 内容
     */
    public void sent(String msg) {
        LocalConfig config = LocalConfigUtils.getLocalConfig();
        try {
            if (ObjectUtils.isEmpty(config)) {
                throw new NullPointerException();
            }
            config.checkConfig();
        } catch (NullPointerException e) {
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(Utils.getApp(), R.string.config_incomplete, Toast.LENGTH_SHORT).show();
            });
            return;
        }

        new Thread(() -> {
            int i = 0;
            while (++i <= tryCount) {
                try {
                    //创建Properties
                    Properties properties = new Properties();
                    // 连接协议
                    properties.put("mail.transport.protocol", "smtp");
                    // SMTP 服务器名，可以从 QQ 邮箱的帮助文档查到
                    // 文档地址：https://service.mail.qq.com/cgi-bin/help?subtype=1&no=167&id=28
                    properties.put("mail.smtp.host", config.getSmtpHost());
                    // SMTP 服务器端口号，可以从 QQ 邮箱的帮助文档查到端口为 465 或 587
                    properties.put("mail.smtp.port", config.getSmtpSSLPort());
                    // 是否需要身份验证
                    properties.put("mail.smtp.auth", "true");
                    // 是否使用 ssl 安全连接
                    properties.put("mail.smtp.ssl.enable", "true");
                    // 是否输出控制台信息
                    properties.put("mail.debug", "true");
                    Session session = Session.getInstance(properties);
                    session.setDebug(true);
                    //创建message对象使用session配置
                    MimeMessage message = new MimeMessage(session);
                    //发件人
                    message.setFrom(config.getAccount());
                    //收件人
                    message.setRecipient(MimeMessage.RecipientType.TO,
                            new InternetAddress(config.getReceiveEmail()));
                    //内容
                    message.setText(msg);
                    //标题
                    message.setSubject(config.getMessageTitle());
                    //创建Transport对象
                    Transport transport = session.getTransport();
                    //链接服务器
                    transport.connect(config.getSmtpHost(), config.getAccount(), config.getPwd());
                    //发送信息
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();

                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(Utils.getApp(), R.string.email_sent, Toast.LENGTH_SHORT).show();
                    });
                    return;
                } catch (Exception e) {
                    LogUtils.e(TAG, e.getMessage(), e);

                    int finalI = i;
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(Utils.getApp(),
                                String.format(Locale.getDefault(),
                                        Utils.getApp().getString(R.string.email_sent_fail_tip),
                                        finalI, tryCount, tryInterval)
                                , Toast.LENGTH_SHORT).show();
                    });

                    try {
                        //延迟x秒后重试
                        Thread.sleep(tryInterval * 1000);
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        }).start();
    }
}

