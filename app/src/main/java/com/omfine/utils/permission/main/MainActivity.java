package com.omfine.utils.permission.main;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.omfine.utils.permission.ext.OnPermissionRequestResultListener;
import com.omfine.utils.permission.ext.PermissionHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.permission).setOnClickListener(view -> {

            PermissionHelper.requestPermission(this , Manifest.permission.WRITE_EXTERNAL_STORAGE , new OnPermissionRequestResultListener(){

                @Override
                public void onDenied(ArrayList<String> grantedPermissions, ArrayList<String> deniedWithRemindPermissions, ArrayList<String> deniedWithoutRemindPermissions) {
                    Log.e("permission" , "=======onDenied====00=====" + deniedWithoutRemindPermissions.size());
                }
                @Override
                public void onGranted() {

                }
            });

        });

    }
}