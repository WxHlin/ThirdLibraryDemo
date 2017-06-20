package com.demo.thirdlibrarydemo.premission;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 为子类提供一个权限判断方法
     * @param permissions  请求的权限数组
     * @return
     */
    public boolean hasPermission(String... permissions){
        for (String permission:permissions
             ) {
            if (ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    /**
     * 为子类提供一个权限请求方法
     * @param code  请求吗
     * @param permissions   请求的权限数组
     */
    public void requestPermission(int code,String... permissions){
        ActivityCompat.requestPermissions(this,permissions,code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case Constants.CALL_PHONE:
                doCallPhone();
                break;
        }
    }

    /**
     * 默认的打电话权限处理
     */
    public void doCallPhone() {

    }
}
