package com.omfine.utils.permission.ext;

import android.app.Activity;
import androidx.core.app.ActivityCompat;
import java.util.ArrayList;

public class PermissionRequestHelper {

    public static void requestPermission(Activity activity , String permission , int requestCode){
        String[] permissions = new String[]{permission};
        ActivityCompat.requestPermissions(activity , permissions , requestCode);
    }

    public static void requestPermissions(Activity activity , ArrayList<String> permissions , int requestCode){
        int size = permissions.size();
        String[] permissionArr = new String[size];
        for (int i = 0; i < size ; i++){
            permissionArr[i] = permissions.get(i);
        }
        ActivityCompat.requestPermissions(activity , permissionArr , requestCode);
    }

    public static void requestPermissions(Activity activity , String[] permissions , int requestCode){
        ActivityCompat.requestPermissions(activity , permissions , requestCode);
    }

    public static void requestPermissions(Activity activity , int requestCode , String... permissions){
        ActivityCompat.requestPermissions(activity , permissions , requestCode);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity , String permission){
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }


}
