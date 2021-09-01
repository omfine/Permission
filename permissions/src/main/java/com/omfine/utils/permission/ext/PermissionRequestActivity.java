package com.omfine.utils.permission.ext;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限请求页面。
 * @author E
 */
public class PermissionRequestActivity extends Activity {

    private ArrayList<String> permissions = new ArrayList<>();
    private int permissionRequestCode = 10010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIntentData();

        requestPermissions();
    }

    private void getIntentData(){
        List<String> permissions = getIntent().getStringArrayListExtra("permissions");
        this.permissions.addAll(permissions);
    }

    private void requestPermissions(){
        PermissionRequestHelper.requestPermissions(this , this.permissions , permissionRequestCode);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != permissionRequestCode){
            finish();
            return;
        }

        boolean granted = true;

        ArrayList<String> grantedPermissions = new ArrayList<>();
        ArrayList<String> deniedWithRemindList = new ArrayList<>();
        ArrayList<String> deniedWithoutRemindList = new ArrayList<>();

        for (int i = 0 ; i < permissions.length ; i ++){
            String permission = permissions[i];

            boolean hasPermission = PermissionHelper.hasPermission(this , permission);
            if (!hasPermission){
                granted = false;

                boolean shouldShowRequestPermissionRationale = PermissionRequestHelper.shouldShowRequestPermissionRationale(this , permission);
                if (shouldShowRequestPermissionRationale){
                    //拒绝了，但是下次请求，系统还会再次提示
                    deniedWithRemindList.add(permission);
                }else {
                    //拒绝，并选择了，下次不再提醒 , 这种情况，只能跳转到应用详情页面，手动去开启权限
                    deniedWithoutRemindList.add(permission);
                }
                log("============================onRequestPermissionsResult=======2=======: " + permission + "  -  " + shouldShowRequestPermissionRationale);
            }else {
                //已允许的权限
                grantedPermissions.add(permission);
            }
        }

        finish();

        if (null != PermissionConfig.onPermissionRequestResultListener){
            if (granted){
                PermissionConfig.onPermissionRequestResultListener.onGranted();
            }else {
                PermissionConfig.onPermissionRequestResultListener.onDenied(grantedPermissions, deniedWithRemindList , deniedWithoutRemindList);
            }
        }
    }

    private void log(String msg){
        Log.e("permission" , msg);
    }

}
