package com.omfine.utils.permission.ext;

import java.util.ArrayList;

/**
 * Permission request callback。
 * @author E
 */
public class OnPermissionRequestResultListener {
    /**
     * All requested permissions granted。
     */
    public void onGranted(){}

    /**
     * one or more permissions one or more permissions denied。。
     * @param grantedPermissions granted permissions among requested permissions
     * @param deniedWithRemindPermissions denied , but will remind again when request
     * @param deniedWithoutRemindPermissions denied and will not remind again when request , only way is to open app detail page.
     */
    public void onDenied(ArrayList<String> grantedPermissions ,ArrayList<String> deniedWithRemindPermissions , ArrayList<String> deniedWithoutRemindPermissions){}

}
