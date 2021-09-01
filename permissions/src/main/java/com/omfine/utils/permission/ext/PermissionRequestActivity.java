package com.omfine.utils.permission.ext;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Page for permission request。
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
                    //denied , but will remind again when request
                    deniedWithRemindList.add(permission);
                }else {
                    //denied and will not remind again when request , only way is to open app detail page.
                    deniedWithoutRemindList.add(permission);
                }
                log("============================onRequestPermissionsResult=======2=======: " + permission + "  -  " + shouldShowRequestPermissionRationale);
            }else {
                //granted permission
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
