package com.mjsoftking.smstoemail.bean;

import android.text.TextUtils;

import androidx.annotation.StringRes;

import com.blankj.utilcode.util.Utils;
import com.mjsoftking.smstoemail.R;

import java.util.Locale;

/**
 * 用途：
 * <p>
 * 作者：MJSoftKing
 * 时间：2021/07/02
 */
public class LocalConfig implements IBean {

    private String smtpHost;
    private String smtpSSLPort;
    private String account;
    private String pwd;
    private String receiveEmail;

    private String messageTitle;

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpSSLPort() {
        return smtpSSLPort;
    }

    public void setSmtpSSLPort(String smtpSSLPort) {
        this.smtpSSLPort = smtpSSLPort;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }


    public void checkConfig() throws NullPointerException {
        if (TextUtils.isEmpty(smtpHost)) {
            throw new NullPointerException(accessContent(R.string.smtp_address));
        } else if (TextUtils.isEmpty(smtpSSLPort)) {
            throw new NullPointerException(accessContent(R.string.smtp_ssl_port));
        } else if (TextUtils.isEmpty(account)) {
            throw new NullPointerException(accessContent(R.string.account));
        } else if (TextUtils.isEmpty(pwd)) {
            throw new NullPointerException(accessContent(R.string.auth_code));
        } else if (TextUtils.isEmpty(receiveEmail)) {
            throw new NullPointerException(accessContent(R.string.recipient));
        } else if (TextUtils.isEmpty(messageTitle)) {
            throw new NullPointerException(accessContent(R.string.email_message_title));
        }
    }

    private String accessContent(@StringRes int sId){
        return String.format(Locale.getDefault(),
                Utils.getApp().getString(R.string.please_input_xx),
                Utils.getApp().getString(sId));
    }

}
