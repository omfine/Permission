package com.omfine.utils.permission.ext;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  实际在用户端，调用的类。
 * @author E
 */
public class PermissionHelper {

    public static void requestPermission(Context context , String permission , OnPermissionRequestResultListener onPermissionRequestResultListener){
        if (hasPermission(context, permission)){
            onPermissionRequestResultListener.onGranted();
            return;
        }
        PermissionConfig.onPermissionRequestResultListener = onPermissionRequestResultListener;
        Intent intent = new Intent(context , PermissionRequestActivity.class);
        ArrayList<String> list = new ArrayList<>();
        list.add(permission);
        intent.putStringArrayListExtra("permissions" , list);
        context.startActivity(intent);
    }

    public static void requestPermissions(Context context , ArrayList<String> permissions , OnPermissionRequestResultListener onPermissionRequestResultListener){
        if (getDeniedPermissions(context, permissions).isEmpty()){
            onPermissionRequestResultListener.onGranted();
            return;
        }
        PermissionConfig.onPermissionRequestResultListener = onPermissionRequestResultListener;
        Intent intent = new Intent(context , PermissionRequestActivity.class);
        intent.putStringArrayListExtra("permissions" , permissions);
        context.startActivity(intent);
    }

    public static void requestPermissions(Context context , String[] permissions , OnPermissionRequestResultListener onPermissionRequestResultListener){
        requestPermissions(context, permissions, onPermissionRequestResultListener);
    }

    public static void requestPermissions(Context context , OnPermissionRequestResultListener onPermissionRequestResultListener , String... permissions){
        if (getDeniedPermissions(context, Arrays.asList(permissions.clone())).isEmpty()){
            onPermissionRequestResultListener.onGranted();
            return;
        }
        PermissionConfig.onPermissionRequestResultListener = onPermissionRequestResultListener;
        Intent intent = new Intent(context , PermissionRequestActivity.class);
        ArrayList<String> permissionList = new ArrayList<>();
        for (int i = 0 ; i < permissions.length ; i ++){
            permissionList.add(permissions[i]);
        }
        intent.putStringArrayListExtra("permissions" , permissionList);
        context.startActivity(intent);
    }


    public static boolean hasPermission(Context context , String permission){
        if (null == permission || permission.isEmpty()){
            return true;
        }
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasPermissions(Context context , List<String> permissions){
        if (null == permissions || permissions.isEmpty()){
            return true;
        }
        boolean granted = true;
        for (String permission : permissions){
            if (!hasPermission(context ,permission)){
                granted =false;
                break;
            }
        }
        return granted;
    }

    public static List<String> getDeniedPermissions(Context context , List<String> permissions){
        ArrayList<String> list = new ArrayList<>();
        for (String permission : permissions){
            if (!hasPermission(context , permission)){
                list.add(permission);
            }
        }
        return list;
    }

    public static List<String> getDeniedPermission(Context context , String permission){
        if (hasPermission(context, permission)){
            return new ArrayList<>();
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(permission);
        return list;
    }


}
