package com.mjsoftking.smstoemail.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.mjsoftking.smstoemail.F;
import com.mjsoftking.smstoemail.R;
import com.mjsoftking.smstoemail.bean.LocalConfig;
import com.mjsoftking.smstoemail.databinding.ActivityMainBinding;
import com.mjsoftking.smstoemail.utils.LocalConfigUtils;
import com.mjsoftking.smstoemail.utils.SendEMileUtils;
import com.osard.dialogfragmentutilslib.DialogLibCommon;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setClick(this);

        initPermissions();

        LocalConfig config = LocalConfigUtils.getLocalConfig();
        if (null == config) {
            config = new LocalConfig();
        }
        binding.setConfig(config);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断按下的是不是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            DialogLibCommon.create(this)
                    .setMessage(R.string.quit_soft_tip)
                    .setOnBtnOk(this::finish)
                    .show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        //保存
        if (v.equals(binding.save)) {
            try {
                binding.getConfig().checkConfig();

                LocalConfigUtils.setLocalConfig(binding.getConfig());
                Toast.makeText(this, R.string.config_save, Toast.LENGTH_SHORT).show();
            } catch (NullPointerException e) {
                DialogLibCommon.create(this)
                        .setMessage(e.getMessage())
                        .noShowCancel()
                        .show();
            }
        }
        //还原
        else if (v.equals(binding.reduction)) {
            LocalConfig config = LocalConfigUtils.getLocalConfig();
            if (null == config) {
                config = new LocalConfig();
            }
            binding.setConfig(config);
        }
        //发送测试邮件
        else if (v.equals(binding.emailTest)) {
            try {
                binding.getConfig().checkConfig();

                SendEMileUtils.getInstance().sent(getString(R.string.test_email_content));
            } catch (NullPointerException e) {
                DialogLibCommon.create(this)
                        .setMessage(R.string.config_incomplete)
                        .noShowCancel()
                        .show();
            }
        }
    }

    private void initPermissions() {
        CompositeDisposable disposable = new CompositeDisposable();
        new RxPermissions(MainActivity.this).request(
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_SMS)
                .compose(F.ioToMain())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NotNull Boolean aBoolean) {

                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        disposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }
}