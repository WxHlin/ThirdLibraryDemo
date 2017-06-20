package com.demo.thirdlibrarydemo.premission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demo.thirdlibrarydemo.R;

import static android.R.attr.id;

public class PremissionActivity extends BaseActivity {

    private Button call_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premission);

        call_phone= (Button) findViewById(R.id.call_phone);

        call_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
            }
        });
    }

    /**
     * 拨打电话
     */
    private void callPhone(){
        //未封装前写法
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
//                != PackageManager.PERMISSION_GRANTED){
//            //申请权限
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
//        } else{
//            doCallPhone();
//        }

        //封装后写法
        if (hasPermission(Manifest.permission.CALL_PHONE)){
            doCallPhone();
        } else{
            requestPermission(Constants.CALL_PHONE,Manifest.permission.CALL_PHONE);
        }
    }

    /**
     * 重写父类方法 ，具体实现打电话逻辑
     */
    @Override
    public void doCallPhone() {
        Intent in=new Intent(Intent.ACTION_CALL);
        Uri data=Uri.parse("tel:"+"10010");
        in.setData(data);
        this.startActivity(in);//此处标红不影响编译  App Link
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case 1://打电话权限回调处理
//                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    doCallPhone();
//                } else{
//                    Toast.makeText(PremissionActivity.this,"权限未授予",Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }
}
